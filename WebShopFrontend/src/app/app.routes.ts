import { Routes } from '@angular/router';
import { TestComponent } from './test/test.component';
import {LoginComponent} from './auth/login/login.component';
import {RegistrationComponent} from './auth/registration/registration.component';

export const routes: Routes = [
  //{ path: '', component: TestComponent },
  { path: 'login', component: LoginComponent },
  { path: '', component: RegistrationComponent },
];
