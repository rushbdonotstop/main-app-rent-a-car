import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router'; 
import { HomeComponent } from './home/home/home.component';
import { UserCartComponent } from './components/user-cart/user-cart.component';

const routes: Routes = [
  {path: "cart", component:UserCartComponent},
  {path : "" , component: HomeComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }