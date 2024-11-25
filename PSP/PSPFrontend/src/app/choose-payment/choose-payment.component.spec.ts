import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ChoosePaymentComponent } from './choose-payment.component';

describe('ChoosePaymentComponent', () => {
  let component: ChoosePaymentComponent;
  let fixture: ComponentFixture<ChoosePaymentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ChoosePaymentComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ChoosePaymentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
