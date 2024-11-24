import { Component } from '@angular/core';
import { User } from '../shared/model/user.model';
import { Router } from '@angular/router';
import { AuthService } from '../auth/auth.service';
import { PaymentType } from '../shared/model/payment-type.model';
import { CheckboxModule } from 'primeng/checkbox';
import {
  AbstractControl,
  FormBuilder,
  FormControl,
  FormGroup,
  FormsModule,
  ReactiveFormsModule,
} from '@angular/forms';
import { NgClass, NgForOf, NgIf } from '@angular/common';
import { PaymentsService } from './payments.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-payment-types',
  standalone: true,
  imports: [
    CheckboxModule,
    ReactiveFormsModule,
    NgForOf,
    FormsModule,
    NgIf,
    NgClass,
  ],
  templateUrl: './payment-types.component.html',
  styleUrl: './payment-types.component.css',
})
export class PaymentTypesComponent {
  user: User = { userId: '', username: '' };

  types: PaymentType[] = [];
  selectedTypes: PaymentType[] = [];
  userTypes: PaymentType[] = [];

  isForm: boolean = true;

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
      console.log(user);
      this.user = user;
    });
    this.findAllTypes();
  }

  getFormControl(controlName: string): FormControl {
    return this.group.get(controlName) as FormControl;
  }

  findAllTypes() {
    this.paymentsService.findAll().subscribe({
      next: (result) => {
        if (result) {
          this.types = result;
          this.findTypesByUser();
        }
      },
      error: (error) => {
        if (error.status === 409) {
          this.toast.error(error.message, 'Error!');
        }
      },
    });
  }

  findTypesByUser() {
    this.paymentsService.findTypesByUser(this.user.userId).subscribe({
      next: (result) => {
        if (result) {
          this.userTypes = result;
          this.selectedTypes = [];
          this.userTypes.forEach((ut) => {
            const type = this.types.find((t) => t.name === ut.name);
            if (type) {
              this.selectedTypes.push(type);
            }
          });
          this.group.get('selectedTypes')?.setValue(this.selectedTypes);
          this.isForm = false;
          this.toggleForm();
        }
      },
      error: (error) => {
        if (error.status === 409) {
          this.toast.error(error.message, 'Error!');
        }
      },
    });
  }

  toggleForm(): void {
    if (this.isForm) {
      this.group.enable();
    } else {
      this.group.disable();
    }
  }

  isSelected(paymentType: any): boolean {
    return this.group.get('selectedTypes')?.value.includes(paymentType);
  }

  edit() {
    this.isForm = true;
    this.toggleForm();
  }

  cancel() {
    this.isForm = false;
    this.toggleForm();
  }

  submit() {
    if (
      !this.group.get('selectedTypes')?.value ||
      this.group.get('selectedTypes')?.value.length === 0
    ) {
      this.toast.error(
        'You must choose at least one payment method!',
        'Error!'
      );
      return;
    }
    this.paymentsService
      .editUserChoice(this.user.userId, this.group.get('selectedTypes')?.value)
      .subscribe({
        next: (result) => {
          if (result) {
            this.findTypesByUser();
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
