import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MerchantPasswordComponent } from './merchant-password.component';

describe('MerchantPasswordComponent', () => {
  let component: MerchantPasswordComponent;
  let fixture: ComponentFixture<MerchantPasswordComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MerchantPasswordComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MerchantPasswordComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
