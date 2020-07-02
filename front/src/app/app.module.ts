import { BrowserModule, HAMMER_GESTURE_CONFIG } from '@angular/platform-browser';
import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { AppComponent } from './app.component';
import { CoreModule } from './core/core.module';
import { HomeModule } from './home/home.module';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AppRoutingModule } from './app-routing.module';
import { HttpClientModule } from '@angular/common/http';
import { GestureConfig, MatTabsModule, MatTable, MatTableModule } from '@angular/material';
import { AutocompleteComponent } from './components/create-vehicle/autocomplete/autocomplete.component';
import { RentDialogComponent } from './components/rent-dialog/rent-dialog.component';
import { MatButtonModule } from '@angular/material/button';
import { OwlDateTimeModule, OwlNativeDateTimeModule } from 'ng-pick-datetime';
import { FormsModule } from '@angular/forms';
import { CartDialogComponent } from './components/cart-dialog/cart-dialog.component';
import { MatListModule, MatListItem } from '@angular/material/list';
import { RequestDetailsComponent } from './components/request-history/request-details/request-details.component';
import { ReportDialogComponent } from './components/report-dialog/report-dialog.component';
import { ConversationContentComponent } from './components/user-inbox/conversation-content/conversation-content.component';
import { AgmCoreModule } from '@agm/core';

@NgModule({
  declarations: [
    AppComponent,
    RentDialogComponent,
    CartDialogComponent,
    ReportDialogComponent,
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    CoreModule,
    HomeModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    MatButtonModule,
    OwlDateTimeModule,
    OwlNativeDateTimeModule,
    FormsModule,
    MatListModule,
    MatTabsModule,
    MatTableModule,
    AgmCoreModule
  ],
  providers: [{ provide: HAMMER_GESTURE_CONFIG, useClass: GestureConfig }],
  bootstrap: [AppComponent],
  exports: [],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
  entryComponents: [RentDialogComponent, CartDialogComponent, ReportDialogComponent]
})
export class AppModule { }
