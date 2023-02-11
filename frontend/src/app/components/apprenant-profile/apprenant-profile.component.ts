import { Component, OnInit } from '@angular/core';
import { GetService } from 'src/app/services/request/get.service';
import { ActivatedRoute } from '@angular/router';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-apprenant-profile',
  templateUrl: './apprenant-profile.component.html',
  styleUrls: ['./apprenant-profile.component.css']
})
export class ApprenantProfileComponent implements OnInit {
  ids : string = '';
   constructor(private route: ActivatedRoute, private getService: GetService) {
    this.route.params.subscribe((params) => {
      this.ids = params['id'];
      console.log(this.ids);
    });
  }
  ngOnInit(): void {
    this.getResumeById();
    this.getUserById();
    console.log(this.educations);
    console.log(this.experiences);
  }
  resume: any = [{}];
  experiences: any = [{}];
  user : any = [{}];
  educations: any = [{}];
  getResumeById() {
    this.getService.getResumeById(parseInt(this.ids)).subscribe((data) => {
      this.resume = data;
      this.getService.getEducationsById(this.resume.resumeId).subscribe((data) => {
        this.educations = data;
        console.log(this.educations);
      });
      this.getService.getExperiencesById(this.resume.resumeId).subscribe((data) => {
        this.experiences = data;
        console.log(this.experiences);

    });

  }
  );
}
getUserById(){
  this.getService.getUserDetails(parseInt(this.ids)).subscribe((data) => {
    this.user = data;
    console.log(this.user);
  });
}



}
