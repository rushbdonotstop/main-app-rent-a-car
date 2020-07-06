import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/core/services/auth.service';
import { Router } from '@angular/router';
import { MatSnackBar } from '@angular/material';

@Component({
  selector: 'pm-register-verification',
  templateUrl: './register-verification.component.html',
  styleUrls: ['./register-verification.component.css']
})
export class RegisterVerificationComponent implements OnInit {

  constructor(private _snackBar: MatSnackBar, private router: Router, private authService : AuthService) { }

  ngOnInit() {
    var url = this.router.url;
    var splitted = url.split('=')

    this.authService.registerVerification(splitted[1]).subscribe(result => {
      this._snackBar.open("Succesful verification!", "", {
        duration: 2000,
        verticalPosition: 'bottom'
      });
    })
  }

}