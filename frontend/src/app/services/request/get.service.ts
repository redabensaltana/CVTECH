import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { UserResponse } from 'src/app/models/user-response';

@Injectable({
  providedIn: 'root'
})
export class GetService {

  constructor(private http : HttpClient) { }
  // get all educations
  getEducations(id : number){
    return this.http.get('http://localhost:8080/api/educations/' + id);
  }
  // get all experiences
  getExperiences(id : number){
    return this.http.get('http://localhost:8080/api/experiences/'+ id);
  }
  // get all projects
  getProjects(){
    return this.http.get('http://localhost:8080/api/projects/');
  }
  // get all skills
  getSkills(){
    return this.http.get('http://localhost:8080/api/skills');
  }
  // get all users
  getUsers(){
    return this.http.get('http://localhost:8080/api/users');
  }
  //get user by id
  getUserById(){
    return this.http.get<UserResponse>('http://localhost:8080/api/users/' + localStorage.getItem('id'));
  }


}
