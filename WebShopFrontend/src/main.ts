import { bootstrapApplication } from '@angular/platform-browser';
import { AppComponent } from './app/app.component';
import {provideHttpClient} from '@angular/common/http';
import {routes} from './app/app.routes';
import {provideRouter} from '@angular/router';
import {importProvidersFrom} from '@angular/core';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {ToastrModule} from 'ngx-toastr';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';

bootstrapApplication(AppComponent, {
  providers: [
    provideHttpClient(),
    provideRouter(routes),
    importProvidersFrom(FormsModule, ReactiveFormsModule, ToastrModule.forRoot(), BrowserAnimationsModule),
  ]
});
