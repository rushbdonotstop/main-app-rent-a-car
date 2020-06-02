import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from '../home/login/login.component';
import { RegisterComponent } from '../home/register/register.component';
import { SearchVehicleComponent } from '../components/search-vehicle/search-vehicle.component';
import { HomeComponent } from '../home/home/home.component';

const routes: Routes = [{ path: 'home', component: HomeComponent},
{ path: 'login', component: LoginComponent},
{ path: 'register', component: RegisterComponent},
{ path: 'search', component: SearchVehicleComponent}];
@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CoreRoutingModule { }
