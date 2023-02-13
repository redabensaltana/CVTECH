import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HomeComponent } from './home.component';



@NgModule({
  declarations: [HomeComponent],
  imports: [BrowserModule],
  exports: [ HomeComponent],
  providers: [],
  bootstrap: [HomeComponent],
})
export class HomeModule {}
