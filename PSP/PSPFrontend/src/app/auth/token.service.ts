import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ACCESS_TOKEN } from '../shared/model/constant.model';

@Injectable({
  providedIn: 'root',
})
export class TokenService {
  constructor(private http: HttpClient) {}

  saveAccessToken(token: string): void {
    localStorage.removeItem(ACCESS_TOKEN);
    localStorage.setItem(ACCESS_TOKEN, token);
  }

  getAccessToken() {
    return localStorage.getItem(ACCESS_TOKEN);
  }

  clear() {
    localStorage.removeItem(ACCESS_TOKEN);
  }
}
