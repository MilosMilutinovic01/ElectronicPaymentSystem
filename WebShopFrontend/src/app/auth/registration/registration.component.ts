import { Component } from '@angular/core';
import {FormControl, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import { AuthService } from '../auth.service';
import { Router } from '@angular/router';
import { Registration } from '../../shared/model/registration.model';
import { ToastrService } from 'ngx-toastr';
import {NgIf} from '@angular/common';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrl: './registration.component.css',
  imports: [ReactiveFormsModule, NgIf],
  standalone: true
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
            this.router.navigate(['/login']);
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
}
