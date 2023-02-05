import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {RegisterComponent} from "./components/register/register.component";
import {GenerateCvComponent} from "./components/generate-cv/generate-cv.component";
import {AuthComponent} from "./components/auth/auth.component";
import {AfterAuthGuard} from "./guards/after-auth.guard";
import {AuthGuard} from "./guards/auth.guard";

const routes: Routes = [
  {path:"",redirectTo:"/login",pathMatch:'full'},
  {path:"reigster", component:RegisterComponent},
  {path:"login",  component: AuthComponent , canActivate: [AfterAuthGuard]},
  {path:"generatecv",  component: GenerateCvComponent ,canActivate: [AuthGuard] },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
