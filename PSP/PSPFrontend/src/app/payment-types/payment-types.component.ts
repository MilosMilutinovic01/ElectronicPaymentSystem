import { Component } from '@angular/core';
import { User } from '../shared/model/user.model';
import { Router } from '@angular/router';
import { AuthService } from '../auth/auth.service';
import { PaymentType } from '../shared/model/payment-type.model';
import { CheckboxModule } from 'primeng/checkbox';
import {
  FormBuilder,
  FormGroup,
  FormsModule,
  ReactiveFormsModule,
} from '@angular/forms';
import { NgForOf } from '@angular/common';

@Component({
  selector: 'app-payment-types',
  standalone: true,
  imports: [CheckboxModule, ReactiveFormsModule, NgForOf, FormsModule],
  templateUrl: './payment-types.component.html',
  styleUrl: './payment-types.component.css',
})
export class PaymentTypesComponent {
  user: User = { username: '' };

  types: PaymentType[] = [
    {
      name: 'Kartica',
    },
    {
      name: 'PayPal',
    },
  ];
  selectedTypes: PaymentType[] = [];

  group: FormGroup;

  constructor(
    public router: Router,
    public authService: AuthService,
    public fb: FormBuilder
  ) {
    this.group = fb.group({
      selectedTypes: [[this.types[0]]],
    });
  }

  ngOnInit(): void {
    this.authService.user$.subscribe((user) => {
      this.user = user;
    });

    this.selectedTypes = this.types.filter((t) => t.name === 'Kartica');
  }
}
