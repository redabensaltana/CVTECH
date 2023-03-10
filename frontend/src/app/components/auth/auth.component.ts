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
      email: ["you@gmail.com",[Validators.required,Validators.email]],
      password: ["123",[Validators.required]]
    });
  }
  login(){
    const values = this.loginForm.value;

    if(values.email && values.password){
      this.authService.login(values.email,values.password).subscribe(
        (res) => {
          console.log(res.role);
          if(res.status == 200 && res.role == "DEV" && res.token){
            this.accountService.changeStatus(true)
            this.localStorageService.set("token",res.token);
            this.localStorageService.set("id",res.id);
            this.localStorageService.set("resume_id",res.resume_id);
            this.localStorageService.set("role",res.role);
            this.router.navigateByUrl('/generatecv');
            console.log(res)
          }else if(res.status == 200 && res.role == "CME" && res.token != null){
            this.accountService.changeStatus(true)
            this.localStorageService.set("token",res.token);
            this.localStorageService.set("id",res.id);
            this.localStorageService.set("resume_id",res.resume_id);
            this.localStorageService.set("role",res.role);
            this.router.navigateByUrl('/apprenants');
            console.log(res)
          }
          else{
            this.router.navigateByUrl('/login');
          }
        }
      )
    }

  }

}
