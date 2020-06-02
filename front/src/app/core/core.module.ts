import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CoreRoutingModule } from './core-routing.module';
import { FooterComponent } from './footer/footer.component';
import { HeaderComponent } from './header/header.component';
import { ContentComponent } from './content/content.component';
import { HomeModule } from '../home/home.module';


@NgModule({
  declarations: [FooterComponent, HeaderComponent, ContentComponent],
  imports: [
    CommonModule,
    CoreRoutingModule,
    HomeModule
  ],
  exports: [FooterComponent, HeaderComponent, ContentComponent],
  providers: []
})
export class CoreModule { }
