import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material';
import { AuthService } from 'src/app/core/services/auth.service';

@Component({
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  form: FormGroup;
  public loginInvalid: boolean;

  constructor(private fb: FormBuilder, private _snackBar: MatSnackBar,private formBuilder: FormBuilder, private authService: AuthService) { 
  }

  ngOnInit() {
    this.form = this.fb.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
    });
  }

  onSubmit() {
    if (this.form.valid) {
      try {
        alert(JSON.stringify(this.form.value))
        this.authService.login(this.form.value)
        .subscribe(user => {
          alert(user)
          if (user != null){
            localStorage.setItem("userObject", JSON.stringify(user));
            this._snackBar.open("Succesful login!", "",  {
              duration: 2000,
              verticalPosition : 'top'
            });
          }
          else{
            this.loginInvalid = true;
          }
        });
      } catch (err) {
        this.loginInvalid = true;
      }
    } else {
      this.loginInvalid = true;
    }
  }

}
