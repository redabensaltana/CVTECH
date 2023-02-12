import { Component, OnInit } from '@angular/core';
import { GetService } from 'src/app/services/request/get.service';
import { ActivatedRoute } from '@angular/router';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-apprenant-profile',
  templateUrl: './apprenant-profile.component.html',
  styleUrls: ['./apprenant-profile.component.css'],
})
export class ApprenantProfileComponent implements OnInit {
  ids: string = '';
  constructor(private route: ActivatedRoute, private getService: GetService) {
    this.route.params.subscribe((params) => {
      this.ids = params['id'];
      console.log(this.ids);
    });
  }
  ngOnInit(): void {
    this.getResumeById();
    this.getUserById();
  }
  resume: any = [{}];
  experiences: any = [{}];
  projects: any = [{}];
  skill : any = [{}];
  user: any = [{}];
  educations: any = [{}];
  async getResumeById() {
    this.resume = await this.getService
      .getResumeById(parseInt(this.ids))
      .toPromise();
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
}
