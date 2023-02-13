import { Component, OnInit } from '@angular/core';
import { GetService } from 'src/app/services/request/get.service';
import { ActivatedRoute } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { FormGroup, FormControl } from '@angular/forms';
import { Validators } from '@angular/forms';
import { SaveService } from 'src/app/services/request/save.service';

@Component({
  selector: 'app-apprenant-profile',
  templateUrl: './apprenant-profile.component.html',
  styleUrls: ['./apprenant-profile.component.css'],
})
export class ApprenantProfileComponent implements OnInit {
  ids: string = '';
  constructor(private route: ActivatedRoute, private getService: GetService, private postService : SaveService) {
    this.route.params.subscribe((params) => {
      this.ids = params['id'];
      console.log(this.ids);
    });
  }
  ngOnInit(): void {
    this.getResumeById();
    this.getUserById();
    // console.log(this.comment)
  }
  formGroupComment: FormGroup = new FormGroup({
    message : new FormControl('', [Validators.required]),
  });
  formGroupResumeStatus: FormGroup = new FormGroup({
    status : new FormControl('', [Validators.required]),
  });
  role : any = localStorage.getItem("role");
  resume: any = [{}];
  experiences: any = [{}];
  projects: any = [{}];
  skill : any = [{}];
  user: any = [{}];
  educations: any = [{}];
  comment : any = [{}];
  async getResumeById() {
    this.resume = await this.getService
      .getResumeById(parseInt(this.ids))
      .toPromise();
      console.log(this.resume);
    this.educations = await this.getService
      .getEducationsById(this.resume.resumeId)
      .toPromise();
    this.experiences = await this.getService
      .getExperiencesById(this.resume.resumeId)
      .toPromise();
    this.projects = await this.getService
      .getProjectsById(this.resume.resumeId)
      .toPromise();
    this.skill = await this.getService
      .getSkillById(this.resume.resumeId)
      .toPromise();
    this.comment = await this.getService
    .getCommentById(this.resume.resumeId)
    .toPromise();
    console.log(this.comment);
  }
  getUserById() {
    this.getService.getUserDetails(parseInt(this.ids)).subscribe((data) => {
      this.user = data;
      console.log(this.user);
    });
  }
  splitString(str: string) {
    if (str === undefined) {
      console.error('wait for data to come 😒😒😒😒.');
      return [];
    }
    return str.split(',');
  }

  sendComment( id : number)
  {
    // console.log(id);
    this.comment.commentBody = this.formGroupComment.value.message;
    this.postService.sendComment(id,this.formGroupComment.value.message).subscribe((data) =>{
      console.log(data);
      this.formGroupComment.reset();
    })
  }

  sendResumeStatus( id : number)
  {
    // console.log(id);
    this.resume.status = this.formGroupResumeStatus.value.status;
    if (this.resume.status == "") {
      this.postService.sendResumeStatus(id,"null").subscribe((data) =>{
        console.log(data);
      this.formGroupResumeStatus.reset();

      })
    }else{
      this.postService.sendResumeStatus(id,this.formGroupResumeStatus.value.status).subscribe((data) =>{
        console.log(data);
      this.formGroupResumeStatus.reset();

      })
    }
    
  }
}
