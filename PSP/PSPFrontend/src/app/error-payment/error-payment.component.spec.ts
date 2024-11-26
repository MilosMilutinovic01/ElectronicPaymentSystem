import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ErrorPaymentComponent } from './error-payment.component';

describe('ErrorPaymentComponent', () => {
  let component: ErrorPaymentComponent;
  let fixture: ComponentFixture<ErrorPaymentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ErrorPaymentComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ErrorPaymentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
