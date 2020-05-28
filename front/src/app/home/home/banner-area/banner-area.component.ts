import { Component, OnInit, ChangeDetectionStrategy } from '@angular/core';

@Component({
  selector: 'pm-banner-area',
  templateUrl: './banner-area.component.html',
  styleUrls: ['./banner-area.component.css'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class BannerAreaComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }

}
