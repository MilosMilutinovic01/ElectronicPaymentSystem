import { Component } from '@angular/core';
import {FormControl, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import { Login } from '../../shared/model/login.model';
import { AuthService } from '../auth.service';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css',
  imports: [ReactiveFormsModule],
  standalone: true,
})
export class LoginComponent {
  showPassword: boolean = false;
  constructor(
    private authService: AuthService,
    private router: Router,
    private toast: ToastrService
  ) {}

  userForm = new FormGroup({
    username: new FormControl(''),
    password: new FormControl(''),
  });

  togglePasswordVisibility() {
    this.showPassword = !this.showPassword;
  }

  login(): void {
    const login: Login = {
      username: this.userForm.value.username || '',
      password: this.userForm.value.password || '',
    };

    this.authService.login(login).subscribe({
      next: (result) => {
        this.toast.success(result.message, 'Success!');
        if (result.token) {
          this.router.navigate(['/']);
        }
      },
      error: (error) => {
        this.toast.error(error, 'Error!');
      },
    });
  }
}
