import { Component, OnInit } from '@angular/core';
import { faHome } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'pm-admin-header',
  templateUrl: './admin-header.component.html',
  styleUrls: ['./admin-header.component.css']
})
export class AdminHeaderComponent implements OnInit {

  faHome = faHome

  loggedUser = localStorage.getItem("userObject")

  constructor() { }

  ngOnInit() {
  }

  logout(){
    localStorage.clear();
  }

}
