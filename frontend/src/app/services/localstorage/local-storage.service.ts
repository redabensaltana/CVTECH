import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class LocalStorageService {

  constructor() { }
  set(key: string, value: string) {
    localStorage.setItem(key, value);
  }

  get(key: string) {
    return localStorage.getItem(key);
  }
  isLogin(){
    return localStorage.getItem("token")? true:false;
  }

  remove(key: string) {
    localStorage.removeItem(key);
  }
  getToken() {
    return localStorage.getItem('token');
  }
  decode(payload:any) {
    console.log('payload : ', payload);
    return JSON.parse(atob(payload));
  }
  payload(token:any) {
    const payload = token.split('.')[1];
    const data = this.decode(payload);
    return data;
  }
  getInfos() {

    const token = this.getToken();

    if (token) {
      const payload = this.payload(token);
      return payload ? payload : null;
    }

    return null
  }
}
