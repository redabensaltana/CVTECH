import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { SaveService } from 'src/app/services/request/save.service';
import { GetService } from 'src/app/services/request/get.service';
@Component({
  selector: 'app-cv-edit',
  templateUrl: './cv-edit.component.html',
  styleUrls: ['./cv-edit.component.css'],
  providers: [SaveService],
})
export class CvEditComponent {
  formGroupEducation = new FormGroup({});
   userInformation = {
    firstName : '',
    lastName : '',
    email : '',
    userTitle : '',
    address : '',
    tel : '',
    linkedin : '',
    github : ''
  }
  createEducationForm() {
    for (let i = 0; i < this.items.length; i++) {
      this.formGroupEducation.addControl(
        'name' + i,
        new FormControl(this.items[i].eduName)
      );
      this.formGroupEducation.addControl(
        'description' + i,
        new FormControl(this.items[i].startDate)
      );
      this.formGroupEducation.addControl(
        'date' + i,
        new FormControl(this.items[i].andDate)
      );
    }
  }
  formGroupExperience = new FormGroup({});
  createExperienceForm() {
    for (let i = 0; i < this.experience.length; i++) {
      this.formGroupExperience.addControl(
        'expName' + i,
        new FormControl(this.experience[i].expName)
      );
      this.formGroupExperience.addControl(
        'tech' + i,
        new FormControl(this.experience[i].tech)
      );
      this.formGroupExperience.addControl(
        'startDate' + i,
        new FormControl(this.experience[i].startDate)
      );
      this.formGroupExperience.addControl(
        'position' + i,
        new FormControl(this.experience[i].position)
      );
      this.formGroupExperience.addControl(
        'endDate' + i,
        new FormControl(this.experience[i].endDate)
      );
    }
  }
  formGroupProject = new FormGroup({});
  createProjectForm() {
    for (let i = 0; i < this.project.length; i++) {
      this.formGroupProject.addControl(
        'name' + i,
        new FormControl(this.project[i].proTitle)
      );
      this.formGroupProject.addControl(
        'description' + i,
        new FormControl(this.project[i].discription)
      );
      this.formGroupProject.addControl(
        'languages' + i,
        new FormControl(this.project[i].techs)
      );
    }
  }
  skill: string[] = [''];
  formGroupSkill: FormGroup = new FormGroup({
    skill: new FormControl('Skills', [Validators.required]),
  });

  constructor(private saveService: SaveService, private getService: GetService) {
    this.createEducationForm();
    this.createExperienceForm();
    this.createProjectForm();
    // get user information
    this.getService.getUserById().subscribe((data) => {
      this.userInformation = data;
      console.log(this.userInformation);

    });
}

  formGroupInfromation: FormGroup = new FormGroup({
    fullname: new FormControl('Full Name', [Validators.required]),
    email: new FormControl('Your Email', [Validators.required]),
    github: new FormControl('Your Github', [Validators.required]),
    phone: new FormControl('xxx-xxx-xxxx', [Validators.required]),
    location: new FormControl('Your Location', [Validators.required]),
  });

  informationStatus: boolean = false;
  closeInformation: boolean = false;
  openInformation: boolean = true;
  educationStatus: boolean = false;
  experienceStatus: boolean = false;
  closeEducation: boolean = false;
  openEducation: boolean = true;
  closeExperience: boolean = false;
  openExperience: boolean = true;
  projectStatus: boolean = false;
  closeProject: boolean = false;
  openProject: boolean = true;
  skillsStatus: boolean = false;
  closeSkills: boolean = false;
  openSkills: boolean = true;

  showInformation() {
    this.closeInformation = true;
    this.openInformation = false;
    this.informationStatus = true;
  }
  hideInformation() {
    this.closeInformation = false;
    this.openInformation = true;
    this.informationStatus = false;
  }
  showEducation() {
    this.closeEducation = true;
    this.openEducation = false;
    this.educationStatus = true;
  }
  hideEducation() {
    this.closeEducation = false;
    this.openEducation = true;
    this.educationStatus = false;
  }
  showExperience() {
    this.closeExperience = true;
    this.openExperience = false;
    this.experienceStatus = true;
  }
  hideExperience() {
    this.closeExperience = false;
    this.openExperience = true;
    this.experienceStatus = false;
  }
  showProject() {
    this.closeProject = true;
    this.openProject = false;
    this.projectStatus = true;
  }
  hideProject() {
    this.closeProject = false;
    this.openProject = true;
    this.projectStatus = false;
  }
  showSkills() {
    this.closeSkills = true;
    this.openSkills = false;
    this.skillsStatus = true;
  }
  hideSkills() {
    this.closeSkills = false;
    this.openSkills = true;
    this.skillsStatus = false;
  }

  itemIndex = 0;

  items = [
    {
      educId: 0,
      eduName: '',
      startDate: '',
      andDate: '',
      resumeEducationId: localStorage.getItem('resume_id'),
    },
  ];
  experience = [
    {
      id: 0,
      expName: '',
      tech: '',
      startDate: '',
      position: '',
      endDate: '',
      resumeExperienceId: localStorage.getItem('resume_id'),
    },
  ];
  project = [
    {
      id: 0,
      proTitle: '',
      discription: '',
      techs: '',
      arr: [''],
      resumeProjectId: localStorage.getItem('resume_id'),
    },
  ];

  date: string = '';
  onChangeInupt(event: any, id: number, value: string) {
    const item = this.items.find((i) => i.educId === id);
    if (item && value === 'name') {
      item.eduName = event.target.value;
    } else if (item && value === 'startDate') {
      this.date = event.target.value;
      item.startDate = this.convertTime(this.date);
    } else if (item && value === 'andDate') {
      this.date = event.target.value;
      item.andDate = this.convertTime(this.date);
    }
  }
  onChangeInuptExperience(event: any, id: number, value: string) {
    const item = this.experience.find((i) => i.id === id);
    if (item && value === 'expName') {
      item.expName = event.target.value;
    } else if (item && value === 'tech') {
      item.tech = event.target.value;
    } else if (item && value === 'startDate') {
      this.date = event.target.value;
      item.startDate = this.convertTime(this.date);
    } else if (item && value === 'position') {
      item.position = event.target.value;
    } else if (item && value === 'endDate') {
      this.date = event.target.value;
      item.endDate = this.convertTime(this.date);
    }
  }
  onChangeInuptProject(event: any, id: number, value: string) {
    const item = this.project.find((i) => i.id === id);
    if (item && value === 'name') {
      item.proTitle = event.target.value;
    } else if (item && value === 'description') {
      item.discription = event.target.value;
    } else if (item && value === 'languages') {
      item.techs = event.target.value;
      item.arr = item.techs.split(',');
      for (let i = 0; i < item.arr.length; i++) {
        item.arr[i] = item.arr[i].replace(',', '');
      }
    }
  }

  addInput() {
    this.items.push({
      educId: this.items.length + 1,
      eduName: '',
      startDate: '',
      andDate: '',
      resumeEducationId: localStorage.getItem('resume_id'),
    });
  }
  addInputExperience() {
    this.experience.push({
      id: this.experience.length + 1,
      expName: '',
      tech: '',
      startDate: '',
      position: '',
      endDate: '',
      resumeExperienceId: localStorage.getItem('resume_id'),
    });
  }
  addInputProject() {
    this.project.push({
      id: this.project.length + 1,
      proTitle: '',
      discription: '',
      techs: '',
      arr: [''],
      resumeProjectId: localStorage.getItem('resume_id'),
    });
  }
  removeInput(index: number) {
    this.items.splice(index, 1);
  }
  removeInputExperience(index: number) {
    this.experience.splice(index, 1);
  }
  removeInputProject(index: number) {
    this.project.splice(index, 1);
  }

  convertTime(time: string) {
    console.log(time);

    let parts = time.split('-');
    let d = new Date(`${parts[0]}-${parts[1]}-${parts[2]}`);
    let formattedDate =
      d.toLocaleString('default', { month: 'short' }) + ' ' + parts[0];
    return formattedDate;
  }
  splitString(str: string) {
    let arr = str.split(',');
    arr = arr.filter((item) => item !== ',');
    return arr;
  }

  // saving data
  saveEducation() {
    for (let i = 0; i < this.items.length; i++) {
      this.saveService.saveEducation(this.items[i]).subscribe((response) => {
        console.log(response);
      });
      console.log(this.items[i]);
    }
  }
  saveExperience() {
    for (let i = 0; i < this.experience.length; i++) {
      this.saveService
        .saveExperience(this.experience[i])
        .subscribe((response) => {
          console.log(response);
        });
      console.log(this.experience[i]);
    }
  }
  saveProject() {
    for (let i = 0; i < this.project.length; i++) {
      this.saveService.saveProject(this.project[i]).subscribe((response) => {
        console.log(response);
      });
    }
  }
  saveSkills() {
    const skills = {
      id: 0,
      skillTypes: '',
      skillName: this.formGroupSkill.value.skill,
      resumeSkillId: localStorage.getItem('resume_id'),
    };
    this.saveService.saveSkills(skills).subscribe((response) => {
      console.log(response);
    });
    console.log(skills);
  }
}
