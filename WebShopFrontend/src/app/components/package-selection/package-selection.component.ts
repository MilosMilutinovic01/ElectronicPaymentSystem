import { Component, OnInit } from '@angular/core';
import { PackageComponent } from '../package/package.component';
import { CommonModule } from '@angular/common';
import { Package } from '../../shared/model/package.model';
import { PackageService } from '../../services/package.service';
import {AuthService} from '../../auth/auth.service';
import {FormsModule} from '@angular/forms';
import {ApiKeyService} from '../../services/api-key.service';
import {ApiKey} from '../../shared/model/apikey.model';

@Component({
  selector: 'app-package-selection',
  standalone: true,
  imports: [PackageComponent, CommonModule, FormsModule],
  templateUrl: './package-selection.component.html',
  styleUrls: ['./package-selection.component.css'],
})
export class PackageSelectionComponent implements OnInit {
  maxOptionsMap: Map<string, number> = new Map();
  public packages!: Package[];
  public userRole: string = '';
  public apiKey: ApiKey = { apiKey: ''};
  public showPasswordInput: boolean = true;

  constructor(private packageService: PackageService, private authService: AuthService, private apiKeyService: ApiKeyService) { }

  ngOnInit() {
    this.authService.user$.subscribe(user => {
      this.userRole = user.role;
    })
    if(this.userRole==="CUSTOMER")
      this.getPackages();
    else
      this.getApiKey();
  }

  private getApiKey(){
    this.apiKeyService.getApiKey().subscribe({
      next: (data) => {
        this.apiKey.apiKey = data.apiKey;
      }
    })
  }

  private getPackages() {
    this.packageService.getPackages().subscribe({
      next: (data) => {
        this.packages = data;
        this.calculateMaxHeights();
      },
      error: (error) => {
        console.error('There was an error fetching the packages:', error);
      },
    });
  }

  private calculateMaxHeights() {
    this.packages.forEach((pkg) => {
      pkg.sections.forEach((section) => {
        const sectionName = section.name;
        const sectionHeight = section.options.length;

        if (!this.maxOptionsMap.has(sectionName)) {
          this.maxOptionsMap.set(sectionName, sectionHeight);
        } else {
          const currentMax = this.maxOptionsMap.get(sectionName) || 0;
          this.maxOptionsMap.set(
            sectionName,
            Math.max(currentMax, sectionHeight)
          );
        }
      });
    });
  }

  submitMerchantPassword(): void {
    if (this.apiKey.apiKey !== '') {
      this.showPasswordInput = false;
      this.apiKeyService.setApiKey(this.apiKey.apiKey).subscribe({
        next: (data) => {
          console.log(data);
        },
        error: (error) => {
          console.error('Error submitting API Key:', error);
        }
      });
    } else {
      console.log('Merchant password is required.');
    }
  }

}
