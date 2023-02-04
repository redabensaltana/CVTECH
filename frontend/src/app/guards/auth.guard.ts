import { Injectable } from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree} from '@angular/router';
import { Observable } from 'rxjs';
import {LocalStorageService} from "../services/localstorage/local-storage.service";
import {AccountService} from "../services/acount/account.service";


@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {
  constructor(private router: Router,private localStorage : LocalStorageService, private accountService : AccountService ) {
  }
  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): boolean {

    if (!this.localStorage.isLogin()){
      this.router.navigateByUrl("/login")
      this.accountService.changeStatus(false)
      this.localStorage.remove("token")
      return false;
    }
    return true;
  }

}
