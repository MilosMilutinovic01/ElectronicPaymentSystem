import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {environment} from '../shared/env/environment';
import {ApiKey} from '../shared/model/apikey.model';

@Injectable({
  providedIn: 'root'
})
export class ApiKeyService {
  constructor(private http: HttpClient) {}

  getApiKey(): Observable<ApiKey> {
    return this.http.get<ApiKey>(environment.apiUrl + 'api-key');
  }

  setApiKey(apiKey: string): Observable<any> {
    return this.http.post<any>(`${environment.apiUrl}api-key`, apiKey);
  }
}
