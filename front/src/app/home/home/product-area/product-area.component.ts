import { Component, OnInit, ChangeDetectionStrategy } from '@angular/core';

@Component({
  selector: 'pm-product-area',
  templateUrl: './product-area.component.html',
  styleUrls: ['./product-area.component.css'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class ProductAreaComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }

}
