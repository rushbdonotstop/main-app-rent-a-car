import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material';
import { AuthService } from 'src/app/core/services/auth.service';
import { Router } from '@angular/router';
import { UserType } from 'src/app/shared/models/user/UserType';

@Component({
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  form: FormGroup;
  public loginInvalid: boolean;

  constructor(private router: Router, private fb: FormBuilder, private _snackBar: MatSnackBar, private authService: AuthService) {
  }

  ngOnInit() {
    this.form = this.fb.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
    });
  }

  onSubmit() {
    if (this.form.valid) {
      this.authService.login(this.form.value)
        .subscribe(user => {
          if (user != null) {
            localStorage.setItem("userObject", JSON.stringify(user));
            this._snackBar.open("Succesful login!", "", {
              duration: 2000,
              verticalPosition: 'bottom'
            });
            if(user.userDetails.userType.toString() === 'ADMINISTRATOR'){
              this.router.navigate(['dashboard']);
            }
            else{
              this.router.navigate(['home']);
            }
          }
          else {
            this.loginInvalid = true;
          }
        },
          error => {
            this._snackBar.open("Server error!", "", {
              duration: 2000,
              verticalPosition: 'bottom'
            });
          })
    } else {
      this.loginInvalid = true;
    }
  }

}
