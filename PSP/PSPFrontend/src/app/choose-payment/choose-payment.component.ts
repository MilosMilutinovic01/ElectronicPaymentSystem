import { Component } from '@angular/core';
import { PaymentType } from '../shared/model/payment-type.model';
import { FormBuilder, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { PaymentsService } from '../payment-types/payments.service';
import { ToastrService } from 'ngx-toastr';
import { RadioButtonModule } from 'primeng/radiobutton';
import { NgFor } from '@angular/common';
import { OrderData } from '../shared/model/order-data.model';

@Component({
  selector: 'app-choose-payment',
  standalone: true,
  imports: [RadioButtonModule, ReactiveFormsModule, NgFor],
  templateUrl: './choose-payment.component.html',
  styleUrl: './choose-payment.component.css',
})
export class ChoosePaymentComponent {
  types: PaymentType[] = [];
  paymentGroup: FormGroup;

  password: string = '';
  redisId: string = '';

  constructor(
    public router: Router,
    private route: ActivatedRoute,
    public fb: FormBuilder,
    public paymentsService: PaymentsService,
    private toast: ToastrService
  ) {
    this.paymentGroup = fb.group({
      selectedType: null,
    });
  }

  ngOnInit(): void {
    this.route.paramMap.subscribe((params) => {
      this.password = params.get('password') || '';
      this.redisId = params.get('redisId') || '';
    });
    this.findAllTypesByClient();
  }

  findAllTypesByClient() {
    this.paymentsService.findTypesByMerchantPassword(this.password).subscribe({
      next: (result) => {
        if (result) {
          this.types = result;
        }
      },
      error: (error) => {
        if (error.status === 409) {
          this.toast.error(error.message, 'Error!');
        }
      },
    });
  }

  select() {
    const orderData: OrderData = {
      paymentMethod: this.paymentGroup.get('selectedType')?.value || '',
      redisId: this.redisId || '',
      merchantPassword: this.password || '',
      successUrl: 'http://localhost:4200/success',
      failedUrl: 'http://localhost:4200/failure',
      errorUrl: 'http://localhost:4200/error',
    };

    if (!orderData.paymentMethod) {
      this.toast.error(
        'You must choose at least one payment method!',
        'Error!'
      );
      return;
    } else {
      this.paymentsService.createPayment(orderData).subscribe({
        next: (result) => {
          if (result) {
            console.log(result);
            window.location.href = result.paymentUrl;
            console.log(result);
          }
        },
        error: (error) => {
          if (error.status === 409) {
            this.toast.error(error.message, 'Error!');
          }
        },
      });
    }
  }
}
