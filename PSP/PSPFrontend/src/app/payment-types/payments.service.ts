import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { PaymentType } from '../shared/model/payment-type.model';
import { Observable } from 'rxjs';
import { environment } from '../shared/env/environment';

@Injectable({
  providedIn: 'root',
})
export class PaymentsService {
  constructor(private http: HttpClient) {}

  findAll(): Observable<PaymentType[]> {
    return this.http.get<PaymentType[]>(environment.apiUrl + 'payment/');
  }

  findTypesByUser(userId: string): Observable<PaymentType[]> {
    return this.http.get<PaymentType[]>(
      environment.apiUrl + 'client/find-methods/' + userId
    );
  }

  editUserChoice(
    userId: string,
    selectedTypes: PaymentType[]
  ): Observable<PaymentType[]> {
    return this.http.put<PaymentType[]>(
      environment.apiUrl + 'client/add-methods/' + userId,
      selectedTypes
    );
  }
}
