import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CoreRoutingModule } from './core-routing.module';
import { FooterComponent } from './footer/footer.component';
import { HeaderComponent } from './header/header.component';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { MenuComponent } from './header/menu/menu.component';


@NgModule({
  declarations: [FooterComponent, HeaderComponent, MenuComponent],
  imports: [
    CommonModule,
    CoreRoutingModule
  ],
  exports: [FooterComponent, HeaderComponent, MenuComponent],
  providers: []
})
export class CoreModule { }
