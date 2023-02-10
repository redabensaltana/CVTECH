import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {RegisterComponent} from "./components/register/register.component";
import {GenerateCvComponent} from "./components/generate-cv/generate-cv.component";
import {AuthComponent} from "./components/auth/auth.component";
import {AfterAuthGuard} from "./guards/after-auth.guard";
import {AuthGuard} from "./guards/auth.guard";
import {CvEditComponent} from "./components/cv-edit/cv-edit.component";
import { PostcardComponent } from './components/postcard/postcard.component';
import { ApprenantProfileComponent } from './components/apprenant-profile/apprenant-profile.component';
const routes: Routes = [
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: 'reigster', component: RegisterComponent },
  { path: 'login', component: AuthComponent, canActivate: [AfterAuthGuard] },
  { path: 'generatecv', component: CvEditComponent, canActivate: [AuthGuard] },
  { path: 'apprenants', component: PostcardComponent, canActivate: [AuthGuard]},
  { path: 'apprenant/:id', component: ApprenantProfileComponent, canActivate: [AuthGuard] },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
