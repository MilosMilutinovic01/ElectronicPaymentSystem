import { PaymentType } from './payment-type.model';

export interface Registration {
  name: string;
  username: string;
  password: string;
  bank: string;
  bankAccount: string;
  paymentMethods: PaymentType[];
}
