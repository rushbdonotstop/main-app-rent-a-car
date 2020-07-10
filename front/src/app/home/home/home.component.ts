import { Component, OnInit, NgZone, ViewChild, ElementRef } from '@angular/core';
import { faCar, faDollarSign, faAward, faShieldAlt } from '@fortawesome/free-solid-svg-icons';
import { MapsAPILoader, MouseEvent, GoogleMapsAPIWrapper } from '@agm/core';

@Component({
  selector: 'pm-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})


export class HomeComponent implements OnInit {

  faCar = faCar;
  faDollarSign = faDollarSign;
  faAward = faAward;
  faShieldAlt = faShieldAlt;

  constructor(private mapsAPILoader: MapsAPILoader,
    private ngZone: NgZone) {
  }


  ngOnInit() {
  }



}
