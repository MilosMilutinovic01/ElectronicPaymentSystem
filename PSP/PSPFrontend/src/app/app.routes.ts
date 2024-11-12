import { Routes } from '@angular/router';
import { RegistrationComponent } from './auth/registration/registration.component';
import { LoginComponent } from './auth/login/login.component';
import { PaymentTypesComponent } from './payment-types/payment-types.component';

export const routes: Routes = [
  { path: '', component: LoginComponent },
  { path: 'register', component: RegistrationComponent },
  { path: 'payment-types', component: PaymentTypesComponent },
];
