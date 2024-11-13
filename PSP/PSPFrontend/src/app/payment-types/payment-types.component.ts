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
import { PaymentsService } from './payments.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-payment-types',
  standalone: true,
  imports: [CheckboxModule, ReactiveFormsModule, NgForOf, FormsModule],
  templateUrl: './payment-types.component.html',
  styleUrl: './payment-types.component.css',
})
export class PaymentTypesComponent {
  user: User = { username: '' };

  types: PaymentType[] = [];
  selectedTypes: PaymentType[] = [];

  group: FormGroup;

  constructor(
    public router: Router,
    public authService: AuthService,
    public paymentsService: PaymentsService,
    public fb: FormBuilder,
    private toast: ToastrService
  ) {
    this.group = fb.group({
      selectedTypes: [[]],
    });
  }

  ngOnInit(): void {
    this.authService.user$.subscribe((user) => {
      this.user = user;
    });
    this.findAllTypes();

    //this.selectedTypes = this.types.filter((t) => t.name === 'Kartica');
  }

  findAllTypes() {
    this.paymentsService.findAll().subscribe({
      next: (result) => {
        console.log(result);
        this.types = result;
      },
      error: (error) => {
        if (error.status === 409) {
          this.toast.error(error.message, 'Error!');
        }
      },
    });
  }
}
