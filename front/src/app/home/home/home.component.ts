import { Component, OnInit } from '@angular/core';
import { faCar, faDollarSign, faAward, faShieldAlt } from '@fortawesome/free-solid-svg-icons';

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

  constructor() { }

  ngOnInit() {
    
  }

}
