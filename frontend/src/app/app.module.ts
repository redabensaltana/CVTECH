import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { EducationInputComponent } from './education-input/education-input.component';
import { RegisterComponent } from './components/register/register.component';
import { GenerateCvComponent } from './components/generate-cv/generate-cv.component';
import {AuthComponent} from "./components/auth/auth.component";
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {AppInterceptor} from "./interceptors/app-interceptor.interceptor";

@NgModule({
  declarations: [
    AppComponent,
    EducationInputComponent,
    RegisterComponent,
    GenerateCvComponent,
    AuthComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: AppInterceptor, multi: true },
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
