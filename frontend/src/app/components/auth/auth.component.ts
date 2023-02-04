import { Component } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {AuthService} from "../../services/auth/auth.service";
import {AccountService} from "../../services/acount/account.service";
import {LocalStorageService} from "../../services/localstorage/local-storage.service";



@Component({
  selector: 'app-auth',
  templateUrl: './auth.component.html',
  styleUrls: ['./auth.component.css']
})
export class AuthComponent {
  loginForm!:FormGroup;
  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private localStorageService: LocalStorageService,
    private router: Router,
    private accountService : AccountService
  ) {
    this.loginForm = this.fb.group({
      email: ["youssef@gmail.com",[Validators.required,Validators.email]],
      password: ["youssef1999",[Validators.required]]
    });
  }
  login(){
    const values = this.loginForm.value;

    if(values.email && values.password){
      this.authService.login(values.email,values.password).subscribe(
        (res) => {
          if(res.status == 200){
            this.accountService.changeStatus(true)
            this.localStorageService.set("token",res.token);
            this.router.navigateByUrl('/generatecv');
            console.log(res)
          }else{
            this.router.navigateByUrl('/login');
          }
        }
      )
    }

  }

}
