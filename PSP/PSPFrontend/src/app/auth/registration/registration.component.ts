import { NgFor, NgIf } from '@angular/common';
import { Component } from '@angular/core';
import {
  FormBuilder,
  FormControl,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { AuthService } from '../auth.service';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Registration } from '../../shared/model/registration.model';
import { PaymentType } from '../../shared/model/payment-type.model';
import { CheckboxModule } from 'primeng/checkbox';
import { PaymentsService } from '../../payment-types/payments.service';

@Component({
  selector: 'app-registration',
  standalone: true,
  imports: [ReactiveFormsModule, NgIf, CheckboxModule, NgFor],
  templateUrl: './registration.component.html',
  styleUrl: './registration.component.css',
})
export class RegistrationComponent {
  isFormValid: boolean = true;
  isSecondStep: boolean = false;

  types: PaymentType[] = [];
  //selectedTypes: PaymentType[] = [];
  paymentGroup: FormGroup;

  constructor(
    private authService: AuthService,
    private router: Router,
    public fb: FormBuilder,
    public paymentsService: PaymentsService,
    private toast: ToastrService
  ) {
    this.paymentGroup = fb.group({
      selectedTypes: [[]],
    });
  }

  userForm = new FormGroup({
    name: new FormControl('', [Validators.required]),
    username: new FormControl('', [Validators.required]),
    password: new FormControl('', [Validators.required]),
    rePassword: new FormControl('', [Validators.required]),
    bank: new FormControl('', [Validators.required]),
    bankAccount: new FormControl('', [Validators.required]),
  });

  next(): void {
    if (this.userForm.value.password !== this.userForm.value.rePassword) {
      this.toast.error(
        'Passwords do not match! Please make sure both passwords are identical',
        'Error!'
      );
      return;
    }
    if (this.userForm.valid) {
      this.isSecondStep = true;
      this.findAllTypes();
    } else {
      this.isFormValid = false;
    }
  }

  findAllTypes() {
    this.paymentsService.findAll().subscribe({
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

  getFormControl(controlName: string): FormControl {
    return this.paymentGroup.get(controlName) as FormControl;
  }

  back() {
    this.isSecondStep = false;
  }

  register(): void {
    const registration: Registration = {
      name: this.userForm.value.name || '',
      username: this.userForm.value.username || '',
      password: this.userForm.value.password || '',
      bank: this.userForm.value.bank || '',
      bankAccount: this.userForm.value.bankAccount || '',
      paymentMethods: this.paymentGroup.value.selectedTypes || '',
    };

    console.log(registration);

    if (
      !registration.paymentMethods ||
      registration.paymentMethods.length === 0
    ) {
      this.toast.error(
        'You must choose at least one payment method!',
        'Error!'
      );
      return;
    }

    this.authService.register(registration).subscribe({
      next: (result) => {
        this.toast.success(result.message, 'Success!');
        if (result.userId) {
          this.router.navigate(['one-time/' + result.merchantPassword]);
        }
        console.log(result);
      },
      error: (error) => {
        if (error.status === 409) {
          this.toast.error('Username already exist!', 'Error!');
        }
      },
    });
  }

  login() {
    this.router.navigate(['']);
  }
}
