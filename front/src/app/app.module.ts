import { BrowserModule,  HAMMER_GESTURE_CONFIG  } from '@angular/platform-browser';
import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { AppComponent } from './app.component';
import { CoreModule } from './core/core.module';
import { HomeModule } from './home/home.module';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AppRoutingModule } from './app-routing.module';
import { HttpClientModule } from '@angular/common/http';
import { GestureConfig } from '@angular/material';
import { AutocompleteComponent } from './components/create-vehicle/autocomplete/autocomplete.component';
import { RentDialogComponent } from './components/rent-dialog/rent-dialog.component';
import {MatButtonModule} from '@angular/material/button';


@NgModule({
  declarations: [
    AppComponent,
    RentDialogComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    CoreModule,
    HomeModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    MatButtonModule
    
  ],
  providers : [ { provide: HAMMER_GESTURE_CONFIG, useClass: GestureConfig }],
  bootstrap: [AppComponent],
  exports: [],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
  entryComponents:[RentDialogComponent]
})
export class AppModule { }
