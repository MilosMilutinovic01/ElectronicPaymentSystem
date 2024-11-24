import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-merchant-password',
  standalone: true,
  imports: [],
  templateUrl: './merchant-password.component.html',
  styleUrl: './merchant-password.component.css',
})
export class MerchantPasswordComponent {
  password: string = '';

  constructor(
    public router: Router,
    private route: ActivatedRoute,
    private toast: ToastrService
  ) {}

  ngOnInit(): void {
    this.route.paramMap.subscribe((params) => {
      this.password = params.get('password') || '';
    });
  }

  openLogin() {
    this.router.navigate(['']);
  }
}
