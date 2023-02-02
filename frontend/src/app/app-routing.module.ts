import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { AuthComponent } from './components/auth/auth.component';

const routes: Routes = [];
routes.push({ path: '/home', component: HomeComponent });

@NgModule({
  imports: [
    RouterModule.forRoot(
      [
        {
          path: '',
          component: HomeComponent,
          children: [{ path: 'home', component: HomeComponent }],
        },
        {
          path: 'login',
          component: AuthComponent,
          children: [{ path: 'login', component: AuthComponent }]
        }
      ],
      { scrollPositionRestoration: 'enabled' }
    ),
  ],
  exports: [RouterModule],
})
export class AppRoutingModule {}
