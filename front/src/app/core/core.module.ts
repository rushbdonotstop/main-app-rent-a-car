import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { FooterComponent } from './footer/footer.component';
import { HeaderComponent } from './header/header.component';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { AuthService } from './services/auth.service';
import { RouterModule } from '@angular/router';
import { VehicleService } from './services/vehicle.service';
import { MatToolbarModule, MatInputModule, MatCardModule, MatMenuModule, MatIconModule, MatButtonModule, MatTableModule, MatDividerModule, MatSlideToggleModule, MatSelectModule, MatOptionModule, MatProgressSpinnerModule, MatSnackBarModule, MatDialogModule, MatFormFieldModule, MatSliderModule } from '@angular/material';


@NgModule({
  declarations: [FooterComponent, HeaderComponent],
  imports: [
    CommonModule,
    FontAwesomeModule,
    RouterModule,
    MatToolbarModule,
    MatInputModule,
    MatCardModule,
    MatMenuModule,
    MatIconModule,
    MatButtonModule,
    MatTableModule,
    MatDividerModule,
    MatSlideToggleModule,
    MatSelectModule,
    MatOptionModule,
    MatProgressSpinnerModule,
    MatSnackBarModule,
    MatDialogModule,
    MatFormFieldModule,
    MatSliderModule
  ],
  exports: [FooterComponent, HeaderComponent],
  providers: [AuthService, VehicleService]
})
export class CoreModule { }
