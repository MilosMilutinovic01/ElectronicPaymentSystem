import { Routes } from '@angular/router';
import { TestComponent } from './test/test.component';
import { LoginComponent } from './auth/login/login.component';
import { RegistrationComponent } from './auth/registration/registration.component';
import { PackageSelectionComponent } from './components/package-selection/package-selection.component';

export const routes: Routes = [
  //{ path: '', component: TestComponent },
  { path: '', component: LoginComponent },
  { path: 'register', component: RegistrationComponent },
  { path: 'package-selection', component: PackageSelectionComponent },
];
