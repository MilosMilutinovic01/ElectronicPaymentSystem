import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, tap } from 'rxjs';

import { HttpClient } from '@angular/common/http';
import {environment} from '../shared/env/environment';
import {User} from '../shared/model/user.model';
import {Registration} from '../shared/model/registration.model';
import {Login} from '../shared/model/login.model';
import {LoginResponseDto, RegistrationResponseDto} from '../shared/model/response.model';
import {TokenService} from './token.service';
import { JwtHelperService } from '@auth0/angular-jwt';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  user$ = new BehaviorSubject<User>({
    username: '',
  });

  jwtHelperService = new JwtHelperService();

  constructor(private http: HttpClient, private tokenService: TokenService) {}

  register(registration: Registration): Observable<RegistrationResponseDto> {
    return this.http.post<RegistrationResponseDto>(
      environment.apiUrl + 'auth/register',
      registration
    );
  }

  login(login: Login): Observable<LoginResponseDto> {
    return this.http
      .post<LoginResponseDto>(environment.apiUrl + 'auth/login', login)
      .pipe(
        tap((loginResponse) => {
          this.tokenService.saveAccessToken(loginResponse.token);
          this.setUser();
        })
      );
  }

  setUser(): void {
    const accessToken = this.tokenService.getAccessToken() || '';
    const user: User = {
      username: this.jwtHelperService.decodeToken(accessToken).sub,
    };

    this.user$.next(user);
  }

  logout(): void {
    this.tokenService.clear();
    this.user$.next({ username: '' });
  }
}
