import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
@Injectable({
  providedIn: 'root'
})
export class SaveService {

  constructor(private http : HttpClient ) { }


  saveEducation(education : any){
    return this.http.post('http://localhost:8080/api/educations', education);
  }
  saveExperience(experience : any){
    return this.http.post('http://localhost:8080/api/experiences', experience);
  }
  saveProject(project : any){
    return this.http.post('http://localhost:8080/api/projects', project);
  }
  saveSkills(skills : any){
    return this.http.post('http://localhost:8080/api/skills', skills);
  }
  sendComment(id : number, body : string)
  {
    return this.http.put('http://localhost:8080/api/comments/' + id, body);
  }
  sendResumeStatus(id : number, body : string)
  {
    return this.http.put('http://localhost:8080/api/resumes/status/' + id, body);
  }

}
