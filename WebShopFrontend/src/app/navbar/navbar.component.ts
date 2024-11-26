import { Component } from '@angular/core';
import { User } from '../shared/model/user.model';
import { Router } from '@angular/router';
import { AuthService } from '../auth/auth.service';
import { NgIf } from '@angular/common';

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [NgIf],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css',
})
export class NavbarComponent {
  user: User = { username: '', role: '' };

  constructor(public router: Router, public authService: AuthService) {}

  ngOnInit(): void {
    //this.authService.setUser();
    this.authService.user$.subscribe((user) => {
      this.user = user;
    });
  }

  logout(): void {
    this.authService.logout();
    this.router.navigate(['']);
  }
}
