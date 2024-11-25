import { Routes } from '@angular/router';
import { RegistrationComponent } from './auth/registration/registration.component';
import { LoginComponent } from './auth/login/login.component';
import { PaymentTypesComponent } from './payment-types/payment-types.component';
import { MerchantPasswordComponent } from './merchant-password/merchant-password.component';
import { ChoosePaymentComponent } from './choose-payment/choose-payment.component';
import { SuccessPaymentComponent } from './success-payment/success-payment.component';
import { FailedPaymentComponent } from './failed-payment/failed-payment.component';
import { ErrorPaymentComponent } from './error-payment/error-payment.component';

export const routes: Routes = [
  { path: '', component: LoginComponent },
  { path: 'register', component: RegistrationComponent },
  { path: 'payment-types', component: PaymentTypesComponent },
  { path: 'one-time/:password', component: MerchantPasswordComponent },
  {
    path: 'choose-payment/:password/:redisId',
    component: ChoosePaymentComponent,
  },
  { path: 'success', component: SuccessPaymentComponent },
  { path: 'failure', component: FailedPaymentComponent },
  { path: 'error', component: ErrorPaymentComponent },
];
