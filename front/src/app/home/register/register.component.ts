import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators, FormControl } from '@angular/forms';
import { MatSnackBar } from '@angular/material';
import { AuthService } from 'src/app/core/services/auth.service';
import { User } from 'src/app/shared/models/user/User';
import { UserDetails } from 'src/app/shared/models/user/UserDetails';
import { UserType } from 'src/app/shared/models/user/UserType';
import { Router } from '@angular/router';

@Component({
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  form: FormGroup;
  public usernameInvalid: boolean;
  public passwordInvalid: boolean;
  public passwordsDontMatch: boolean;
  public emailInvalid: boolean;
  public agentRequest = false;


  constructor(private fb: FormBuilder, private _snackBar: MatSnackBar,private authService : AuthService, private router: Router) { 
    this.usernameInvalid = false;
    this.passwordInvalid = false;
    this.passwordsDontMatch = false;
    this.emailInvalid = false;
  }

  ngOnInit() {
    this.form = this.fb.group({
      username: ['', Validators.required],
      password: ['', Validators.required],
      repeatedPassword : ['', Validators.required],
      email : ['', Validators.required],
      businessNum: [''],
      fullName: ['', Validators.required],
      address:['', Validators.required],
      agentRequest: new FormControl(false)
    });
  }

  onSubmit() {

    this.usernameInvalid = false;
    this.passwordInvalid = false;
    this.passwordsDontMatch = false;
    this.emailInvalid = false;

    if (this.form.valid) {
      if(this.invalidUsername(this.form.get('username').value)){
        this.usernameInvalid = true;
        return;
      }
      if(this.invalidPassword(this.form.get('password').value)){
        this.passwordInvalid = true;
        return;
      }
      if(this.passwordsNoMatch(this.form.get('password').value, this.form.get('repeatedPassword').value)){
        this.passwordsDontMatch = true;
        return;
      }
      if(this.invalidEmail(this.form.get('email').value)){
        this.emailInvalid = true;
        return;
      }

      var user = new User();
      user.password = this.form.get('password').value
      user.username = this.form.get('username').value
      var userDetails = new UserDetails()
      userDetails.address = this.form.get('address').value
      userDetails.businessNum = this.form.get('businessNum').value
      userDetails.email = this.form.get('email').value
      userDetails.fullName = this.form.get('fullName').value
      if(this.agentRequest) {
        userDetails.userType = UserType.AGENT;
      } else {
        userDetails.userType = UserType.END_USER;
      }
      user.userDetails = userDetails

      this.authService.register(user).subscribe(notification => {
        this._snackBar.open(notification.text, "", {
          duration: 2000,
          verticalPosition: 'bottom'
        });
      },
      error => {
        this._snackBar.open("Error occured", "", {
          duration: 2000,
          verticalPosition: 'bottom'
        });
      })
    }
    this.router.navigate(['home']);
  }

  invalidUsername(username : String) : boolean{
    var nameRegex = /^[a-zA-Z0-9]+$/;
    
    if(username.match(nameRegex) != null && username.length >= 8){
      return false;
    }

    return true;
  }

  invalidPassword(password : String) : boolean{
    var passwordRegex = /^(?=.*[A-Z].*[A-Z])(?=.*[!@#$&*])(?=.*[0-9].*[0-9])(?=.*[a-z].*[a-z].*[a-z]).{8}$/;

    if(password.match(passwordRegex) != null && password.length >= 8){
        return false;
    }

    return true;
  }

  passwordsNoMatch(password : String, repeatedPassword : String) : boolean{

    if(password === repeatedPassword){
      return false;
    }

    return true;
  }

  invalidEmail(email : String) : boolean{
    var emailRegex = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    
    if (email.match(emailRegex) != null){
      return false;
    }

    return true;
  }

}