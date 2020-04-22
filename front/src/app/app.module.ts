import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header/header.component';
import { BannerAreaComponent } from './banner-area/banner-area/banner-area.component';
import { FeaturesAreaComponent } from './features-area/features-area/features-area.component';
import { ProductAreaComponent } from './product-area/product-area/product-area.component';
import { FooterComponent } from './footer/footer/footer.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    BannerAreaComponent,
    FeaturesAreaComponent,
    ProductAreaComponent,
    FooterComponent
  ],
  imports: [
    BrowserModule
  ],
  bootstrap: [AppComponent],
  exports: [HeaderComponent, BannerAreaComponent, FeaturesAreaComponent, ProductAreaComponent, FooterComponent]
})
export class AppModule { }
