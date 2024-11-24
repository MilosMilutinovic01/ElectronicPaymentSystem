import { NgIf } from '@angular/common';
import { Component } from '@angular/core';
import {
  FormControl,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { AuthService } from '../auth.service';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Registration } from '../../shared/model/registration.model';

@Component({
  selector: 'app-registration',
  standalone: true,
  imports: [ReactiveFormsModule, NgIf],
  templateUrl: './registration.component.html',
  styleUrl: './registration.component.css',
})
export class RegistrationComponent {
  isFormValid: boolean = true;

  constructor(
    private authService: AuthService,
    private router: Router,
    private toast: ToastrService
  ) {}

  userForm = new FormGroup({
    name: new FormControl('', [Validators.required]),
    username: new FormControl('', [Validators.required]),
    password: new FormControl('', [Validators.required]),
    rePassword: new FormControl('', [Validators.required]),
  });

  register(): void {
    if (this.userForm.value.password !== this.userForm.value.rePassword) {
      this.toast.error(
        'Passwords do not match! Please make sure both passwords are identical',
        'Error!'
      );
      return;
    }

    const registration: Registration = {
      name: this.userForm.value.name || '',
      username: this.userForm.value.username || '',
      password: this.userForm.value.password || '',
    };

    if (this.userForm.valid) {
      this.authService.register(registration).subscribe({
        next: (result) => {
          this.toast.success(result.message, 'Success!');
          if (result.userId) {
            this.router.navigate(['']);
          }
        },
        error: (error) => {
          if (error.status === 409) {
            this.toast.error('Username already exist!', 'Error!');
          }
        },
      });
    } else {
      this.isFormValid = false;
    }
  }

  login() {
    this.router.navigate(['']);
  }
}
