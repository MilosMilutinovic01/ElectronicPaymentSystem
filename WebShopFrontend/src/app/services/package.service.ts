import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../shared/env/environment';
import { Package } from '../shared/model/package.model';

@Injectable({
  providedIn: 'root',
})
export class PackageService {
  constructor(private http: HttpClient) {}

  getPackages(): Observable<Package[]> {
    return this.http.get<any[]>(environment.apiUrl + 'packages/get-all');
  }

  buyPackage(packageId: number, username: string): Observable<any> {
    return this.http.post<any>(`${environment.apiUrl}packages/buy`, {
      packageId,
      username,
    });
  }
}
