import { Component, Input, OnInit } from '@angular/core';
import { Package } from '../../shared/model/package.model';
import { CommonModule } from '@angular/common';
import { PackageService } from '../../services/package.service';
import { AuthService } from '../../auth/auth.service';

@Component({
  selector: 'app-package',
  templateUrl: './package.component.html',
  styleUrls: ['./package.component.css'],
  standalone: true,
  imports: [CommonModule],
})
export class PackageComponent implements OnInit {
  @Input() package!: Package;
  @Input() maxOptionsMap!: Map<string, number>;

  constructor(
    private packageService: PackageService,
    private authService: AuthService
  ) {}

  ngOnInit() {
    this.adjustOptionsToMax();
  }

  private adjustOptionsToMax() {
    this.package.sections.forEach((section) => {
      const sectionMaxOptions =
        this.maxOptionsMap.get(section.name) || section.options.length;

      const missingOptions = sectionMaxOptions - section.options.length;
      if (missingOptions > 0) {
        const emptyOptions = new Array(missingOptions).fill('');
        section.options.push(...emptyOptions);
      }
    });
  }

  buyPackage(packageId: number) {
    this.packageService
      .buyPackage(packageId, this.authService.getUsername())
      .subscribe(
        (response) => {
          const redisId = response.redisId;
          const apiKey = response.apiKey;
          window.location.href = `http://localhost:4200/choose-payment/${apiKey}/${redisId}`;
          // You can show a success message or redirect the user
        },
        (error) => {
          console.error('Purchase failed', error);
          // Show an error message to the user
        }
      );
  }
}
