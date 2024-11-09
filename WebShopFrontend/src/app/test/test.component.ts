import { Component, OnInit } from '@angular/core';
import { ApiService } from '../api.service';
import { JsonPipe, NgIf } from '@angular/common';

@Component({
  selector: 'app-test',
  standalone: true,
  imports: [
    NgIf
  ],
  templateUrl: './test.component.html',
  styleUrls: ['./test.component.css']
})
export class TestComponent implements OnInit {
  data: string = '';

  constructor(private apiService: ApiService) { }

  ngOnInit(): void {
    this.apiService.getData().subscribe(
      (response) => {
        this.data = response;
      }
    );
  }
}
