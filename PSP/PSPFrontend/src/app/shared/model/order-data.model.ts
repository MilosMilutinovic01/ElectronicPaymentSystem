import { PaymentType } from './payment-type.model';

export interface OrderData {
  paymentMethod: PaymentType;
  redisId: string;

  merchantPassword: string;

  successUrl: string;
  failedUrl: string;
  errorUrl: string;
}

export interface PaymentResponseDTO {
  paymentId: string;
  paymentUrl: string;
  merchantId: string;
  merchantOrderId: string;
  amount: number;
}
