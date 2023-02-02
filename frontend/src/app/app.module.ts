// import { NgModule } from '@angular/core';
// import { BrowserModule } from '@angular/platform-browser';
// import { AppRoutingModule } from './app-routing.module';
// import { AppComponent } from './app.component';
// import { HomeComponent } from './components/home/home.component';
// import { SidebarComponent } from './components/sidebar/sidebar.component';
// import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
// import { BannerComponent } from './components/banner/banner.component';
// import { PostcardComponent } from './components/postcard/postcard.component';
// import { NavbarComponent } from './components/navbar/navbar.component';
// import { AuthComponent } from './components/auth/auth.component';
// import { FooterComponent } from './components/footer/footer.component';
// import { FileUploadComponent } from './components/file-upload/file-upload.component';




// @NgModule({
//   declarations: [AppComponent, HomeComponent, SidebarComponent, BannerComponent, PostcardComponent, NavbarComponent, AuthComponent, FooterComponent, FileUploadComponent],
//   imports: [BrowserModule, AppRoutingModule, FontAwesomeModule],
//   exports: [SidebarComponent, HomeComponent, BannerComponent, PostcardComponent, NavbarComponent],
//   providers: [],
//   bootstrap: [AppComponent],
// })
// export class AppModule {}
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { FileUploadComponent } from './components/file-upload/file-upload.component';

import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';
@NgModule({
  declarations: [AppComponent, FileUploadComponent],
  imports: [BrowserModule, HttpClientModule],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {
  
}
