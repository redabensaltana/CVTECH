import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent {
  constructor(private router : Router)
  {}
  id : any = localStorage.getItem("id");
  role : any = localStorage.getItem("role");
  logout()
  {
    localStorage.clear();
    console.log("log out");
    this.router.navigateByUrl('/login');
  }

}
