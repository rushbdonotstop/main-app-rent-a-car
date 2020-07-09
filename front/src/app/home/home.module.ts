import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HomeComponent } from './home/home.component';
import { RegisterComponent } from './register/register.component';
import { LoginComponent } from './login/login.component';
import { SearchVehicleComponent } from '../components/search-vehicle/search-vehicle.component';
import { ViewVehiclesComponent } from '../components/view-vehicles/view-vehicles.component';
import { VehicleDetailsComponent } from '../components/vehicle-details/vehicle-details.component';
import { UserCartComponent } from '../components/user-cart/user-cart.component';
import { RequestHistoryComponent } from '../components/request-history/request-history.component';
import { UserInboxComponent } from '../components/user-inbox/user-inbox.component';
import { CreateVehicleComponent } from '../components/create-vehicle/create-vehicle.component';
import { CreateReviewComponent } from '../components/vehicle-review/create-review/create-review.component';
import { ViewVehicleReviewsComponent } from '../components/vehicle-review/view-vehicle-reviews/view-vehicle-reviews.component';
import { CreateReportComponent } from '../components/create-report/create-report.component';
import { CreatePriceListComponent } from '../components/price-list/create-price-list/create-price-list.component';
import { ViewPriceListComponent } from '../components/price-list/view-price-list/view-price-list.component';
import { VehicleMapComponent } from '../components/vehicle-map/vehicle-map.component';
import { AdminDashboardComponent } from '../components/admin-dashboard/admin-dashboard.component';
import { EditCodebookComponent } from '../components/admin-dashboard/vehicles-codebook/edit-codebook/edit-codebook.component';
import { ViewCodebookComponent, EditFuelTypeDialog, EditMakeDialog, EditModelDialog, EditStyleDialog, EditTransmissionlDialog } from '../components/admin-dashboard/vehicles-codebook/view-codebook/view-codebook.component';
import { CommentRequestsComponent } from '../components/admin-dashboard/comment-requests/comment-requests.component';
import { ViewUsersComponent, UserDetailsDialog } from '../components/admin-dashboard/system-users/view-users/view-users.component';
import { RegisterAgentComponent } from '../components/admin-dashboard/system-users/register-agent/register-agent.component';
import { EditPermissionsComponent } from '../components/admin-dashboard/system-users/edit-permissions/edit-permissions.component';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { CoreModule } from '../core/core.module';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { MatTable, MatToolbarModule, MatInputModule, MatCardModule, MatMenuModule, MatIconModule, MatButtonModule, MatTableModule, MatDividerModule, MatSlideToggleModule, MatSelectModule, MatOptionModule, MatProgressSpinnerModule, MatSnackBarModule, MatDialogModule, MatFormFieldModule, MatSliderModule, MatDatepickerModule, MatNativeDateModule, MatCheckboxModule, MatSortModule, MatPaginatorModule, MatTabsModule } from '@angular/material';
import { NgbTimepickerModule } from '@ng-bootstrap/ng-bootstrap';
import { AutocompleteComponent } from '../components/create-vehicle/autocomplete/autocomplete.component';
import { MatListModule } from '@angular/material/list';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatTooltipModule} from '@angular/material/tooltip';
import { TextFieldModule } from '@angular/cdk/text-field';
import { AdminHeaderComponent } from '../components/admin-dashboard/admin-header/admin-header.component';
import { RequestDetailsComponent } from '../components/request-history/request-details/request-details.component';
import { ConversationContentComponent } from '../components/user-inbox/conversation-content/conversation-content.component';
import { VehicleStatisticComponent } from '../components/request-history/vehicle-statistic/vehicle-statistic.component';
import { AgmCoreModule } from '@agm/core';
import { NewMessageDialogComponent } from '../components/user-inbox/new-message-dialog/new-message-dialog.component';
import { RegisterVerificationComponent } from './register/register-verification/register-verification.component';

@NgModule({
  declarations: [
    HomeComponent,
    RegisterComponent,
    LoginComponent,
    SearchVehicleComponent,
    ViewVehiclesComponent,
    VehicleDetailsComponent,
    UserCartComponent,
    RequestHistoryComponent,
    UserInboxComponent,
    CreateVehicleComponent,
    CreateReviewComponent,
    ViewVehicleReviewsComponent,
    CreateReportComponent,
    VehicleStatisticComponent,
    CreatePriceListComponent,
    ViewPriceListComponent,
    VehicleMapComponent,
    AdminDashboardComponent,
    EditCodebookComponent,
    ViewCodebookComponent,
    CommentRequestsComponent,
    ViewUsersComponent,
    RegisterAgentComponent,
    EditPermissionsComponent,
    AutocompleteComponent,
    AdminHeaderComponent,
    RequestDetailsComponent,
    ConversationContentComponent,
    EditFuelTypeDialog,
    EditMakeDialog,
    EditModelDialog,
    EditStyleDialog,
    EditTransmissionlDialog,
    UserDetailsDialog,
    EditPermissionsComponent,
    NewMessageDialogComponent,
    RegisterVerificationComponent,
  ],
  entryComponents: [
    VehicleDetailsComponent, ViewPriceListComponent, RequestDetailsComponent, ConversationContentComponent, NewMessageDialogComponent,
    EditFuelTypeDialog,
    EditMakeDialog,
    EditModelDialog,
    EditStyleDialog,
    EditTransmissionlDialog,
    UserDetailsDialog,
    RequestDetailsComponent,
    EditPermissionsComponent,
    VehicleStatisticComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    FormsModule,
    CommonModule,
    ReactiveFormsModule,
    FontAwesomeModule,
    CoreModule,
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
    MatSliderModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatCheckboxModule,
    NgbTimepickerModule,
    MatIconModule,
    MatListModule,
    MatSortModule,
    MatPaginatorModule,
    MatTooltipModule,
    TextFieldModule,
    MatTabsModule,
    MatTableModule,
    AgmCoreModule.forRoot({
      apiKey: 'AIzaSyBcBUQxfS6JldNG0Ltoju5YxE_0-CKJsu4',
      libraries: ['places']
    })
  ],
  exports: [HomeComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class HomeModule { }
