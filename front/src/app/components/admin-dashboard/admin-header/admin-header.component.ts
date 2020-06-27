import { Component, OnInit } from '@angular/core';
import { faHome } from '@fortawesome/free-solid-svg-icons';
import { User } from 'src/app/shared/models/user/User';
import { AuthService } from 'src/app/core/services/auth.service';

@Component({
  selector: 'pm-admin-header',
  templateUrl: './admin-header.component.html',
  styleUrls: ['./admin-header.component.css']
})
export class AdminHeaderComponent implements OnInit {

  faHome = faHome

  loggedUser: User;

  constructor(private authService: AuthService) { 
    if(this.authService.isLoggedIn) {
      this.loggedUser = new User();
    }
  }

  ngOnInit() {
  }

  logout(){
    localStorage.clear();
  }

}
