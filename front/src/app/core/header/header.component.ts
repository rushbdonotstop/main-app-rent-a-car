import { Component, OnInit } from '@angular/core';
import { faHome } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'pm-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  faHome = faHome

  loggedUser = localStorage.getItem("userObject")

  constructor() { }

  ngOnInit() {
  }

  logout(){
    localStorage.clear();
  }

}
