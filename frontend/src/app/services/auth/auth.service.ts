import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Router} from "@angular/router";
import {env} from "../../../../config/env";
import {AuthResponse} from "../../models/auth-response";
const url = env.url;
@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(
    private http:HttpClient,
    private router:Router,

  ) { }
  login(email:string,password : string){
    const headers = new HttpHeaders(
      {
        'Content-Type':'application/json'
      }
    );
    return this.http.post<AuthResponse>(`${url}/api/token`,{email, password},{headers});
  }
}
