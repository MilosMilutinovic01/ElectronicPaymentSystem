import { Component, OnInit } from '@angular/core';
import { PackageComponent } from '../package/package.component';
import { CommonModule } from '@angular/common';
import { Package } from '../../shared/model/package.model';
import { PackageService } from '../../services/package.service';

@Component({
  selector: 'app-package-selection',
  standalone: true,
  imports: [PackageComponent, CommonModule],
  templateUrl: './package-selection.component.html',
  styleUrls: ['./package-selection.component.css'],
})
export class PackageSelectionComponent implements OnInit {
  maxOptionsMap: Map<string, number> = new Map();
  public packages!: Package[];

  constructor(private packageService: PackageService) {}

  ngOnInit() {
    this.getPackages();
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
}
