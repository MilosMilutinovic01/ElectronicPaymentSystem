import { Component } from '@angular/core';
import { AuthService } from '../auth.service';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { Login } from '../../shared/model/login.model';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css',
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
          this.router.navigate(['package-selection']);
        }
      },
      error: (error) => {
        this.toast.error(error, 'Error!');
      },
    });
  }

  register() {
    this.router.navigate(['register']);
  }
}
