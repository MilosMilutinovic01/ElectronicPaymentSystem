import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { PaymentType } from '../shared/model/payment-type.model';
import { Observable } from 'rxjs';
import { environment } from '../shared/env/environment';
import {
  OrderData,
  PaymentResponseDTO,
} from '../shared/model/order-data.model';

@Injectable({
  providedIn: 'root',
})
export class PaymentsService {
  constructor(private http: HttpClient) {}

  findAll(): Observable<PaymentType[]> {
    return this.http.get<PaymentType[]>(
      environment.apiUrl + 'payment/find-all'
    );
  }

  findTypesByUser(userId: string): Observable<PaymentType[]> {
    return this.http.get<PaymentType[]>(
      environment.apiUrl + 'client/find-methods/' + userId
    );
  }

  findTypesByMerchantPassword(password: string): Observable<PaymentType[]> {
    return this.http.get<PaymentType[]>(
      environment.apiUrl + 'client/find-by-password/' + password
    );
  }

  createPayment(orderData: OrderData): Observable<PaymentResponseDTO> {
    return this.http.post<PaymentResponseDTO>(
      environment.apiUrl + 'payment/create-payment',
      orderData
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
