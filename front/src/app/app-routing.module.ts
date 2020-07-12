import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { RegisterComponent } from './home/register/register.component';
import { LoginComponent } from './home/login/login.component';
import { HomeComponent } from './home/home/home.component';
import { SearchVehicleComponent } from './components/search-vehicle/search-vehicle.component';
import { ViewVehiclesComponent } from './components/view-vehicles/view-vehicles.component';
import { CreateVehicleComponent } from './components/create-vehicle/create-vehicle.component';
import { RequestHistoryComponent } from './components/request-history/request-history.component';
import { UserCartComponent } from './components/user-cart/user-cart.component';
import { AdminDashboardComponent } from './components/admin-dashboard/admin-dashboard.component';
import { CommentRequestsComponent } from './components/admin-dashboard/comment-requests/comment-requests.component';
import { UserInboxComponent } from './components/user-inbox/user-inbox.component';
import { ViewCodebookComponent } from './components/admin-dashboard/vehicles-codebook/view-codebook/view-codebook.component';
import { ViewUsersComponent } from './components/admin-dashboard/system-users/view-users/view-users.component';
import { RegisterAgentComponent } from './components/admin-dashboard/system-users/register-agent/register-agent.component';
import { MyVehiclesComponent } from './components/my-vehicles/my-vehicles.component';
import { RegisterVerificationComponent } from './home/register/register-verification/register-verification.component';


const routes: Routes = [{ path: 'login', component: LoginComponent},
{ path: 'registrationConfirm', component: RegisterVerificationComponent},
{ path: 'register', component: RegisterComponent},
{ path: 'search', component: ViewVehiclesComponent},
{ path: 'add', component: CreateVehicleComponent },
{ path: 'history', component: RequestHistoryComponent },
{ path: 'inbox', component: UserInboxComponent},
{ path: 'cart', component: UserCartComponent },
{ path: 'home', component: HomeComponent },
{ path: 'dashboard', component : AdminDashboardComponent},
{ path: 'reviews', component : CommentRequestsComponent},
{ path: 'catalogue', component: ViewCodebookComponent},
{ path: 'users', component: ViewUsersComponent},
{ path: 'agentRequests', component: RegisterAgentComponent},
{ path: 'myVehicles', component: MyVehiclesComponent},
{ path: '', redirectTo: 'home', pathMatch : 'full'},
{ path: '**', component: HomeComponent }];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }