import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { CvEditComponent } from './components/cv-edit/cv-edit.component';

const routes: Routes = [];
routes.push({ path: '', component: HomeComponent });


@NgModule({
  imports: [RouterModule.forRoot(
    [
      { path: '', component: HomeComponent },
      { path: 'edit', component: CvEditComponent },
      

    ]
  )],
  exports: [RouterModule]
})
export class AppRoutingModule { }
