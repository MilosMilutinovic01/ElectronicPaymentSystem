import { Component } from '@angular/core';
import { PaymentType } from '../shared/model/payment-type.model';
import { FormBuilder, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { PaymentsService } from '../payment-types/payments.service';
import { ToastrService } from 'ngx-toastr';
import { RadioButtonModule } from 'primeng/radiobutton';
import { NgFor } from '@angular/common';

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
    console.log(this.paymentGroup.get('selectedType')?.value);
  }
}
