import { Component, OnInit } from '@angular/core';
import { faHome } from '@fortawesome/free-solid-svg-icons';
import { AuthService } from '../services/auth.service';
import { User } from 'src/app/shared/models/user/User';
import { OVERLAY_KEYBOARD_DISPATCHER_PROVIDER } from '@angular/cdk/overlay/typings/keyboard/overlay-keyboard-dispatcher';
import { UserType } from 'src/app/shared/models/user/UserType';
import { UserDetails } from 'src/app/shared/models/user/UserDetails';

@Component({
  selector: 'pm-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  faHome = faHome

  loggedUser: User;

  constructor(private authService: AuthService) { 
    if(this.authService.isLoggedIn()) {
      this.loggedUser = new User();
      // this.loggedUser.userDetails = new UserDetails*
      // let role =  JSON.stringify(this.authService.getRole());
      // alert(role);
      // this.loggedUser.userDetails.userType = UserType[role];
      //alert(this.loggedUser.userDetails.userType);
    }
  }

  ngOnInit() {
  }

  logout(){
    localStorage.clear();
  }

}
