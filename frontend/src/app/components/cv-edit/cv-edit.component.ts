import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { SaveService } from 'src/app/services/request/save.service';
@Component({
  selector: 'app-cv-edit',
  templateUrl: './cv-edit.component.html',
  styleUrls: ['./cv-edit.component.css'],
  providers: [SaveService],
})
export class CvEditComponent {
  formGroupEducation = new FormGroup({});
  createEducationForm() {
    for (let i = 0; i < this.items.length; i++) {
      this.formGroupEducation.addControl(
        'name' + i,
        new FormControl(this.items[i].eduName)
      );
      this.formGroupEducation.addControl(
        'description' + i,
        new FormControl(this.items[i].description)
      );
      this.formGroupEducation.addControl(
        'date' + i,
        new FormControl(this.items[i].date)
      );
    }
  }
  formGroupExperience = new FormGroup({});
  createExperienceForm() {
    for (let i = 0; i < this.experience.length; i++) {
      this.formGroupExperience.addControl(
        'name' + i,
        new FormControl(this.experience[i].name)
      );
      this.formGroupExperience.addControl(
        'description' + i,
        new FormControl(this.experience[i].description)
      );
      this.formGroupExperience.addControl(
        'date' + i,
        new FormControl(this.experience[i].date)
      );
      this.formGroupExperience.addControl(
        'post' + i,
        new FormControl(this.experience[i].post)
      );
      this.formGroupExperience.addControl(
        'location' + i,
        new FormControl(this.experience[i].location)
      );
    }
  }
  formGroupProject = new FormGroup({});
  createProjectForm() {
    for (let i = 0; i < this.project.length; i++) {
      this.formGroupProject.addControl(
        'name' + i,
        new FormControl(this.project[i].name)
      );
      this.formGroupProject.addControl(
        'description' + i,
        new FormControl(this.project[i].description)
      );
      this.formGroupProject.addControl(
        'languages' + i,
        new FormControl(this.project[i].languages)
      );
    }
  }
  skill: string[] = [''];
  formGroupSkill: FormGroup = new FormGroup({
    skill: new FormControl('Skills', [Validators.required]),
  });

  constructor(private saveService: SaveService) {
    this.createEducationForm();
    this.createExperienceForm();
    this.createProjectForm();
    console.log(this.project[0].arr);
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

  items = [{ educId: 0, eduName: '', description: '', date: '' }];
  experience = [
    { id: 0, name: '', description: '', date: '', post: '', location: '' },
  ];
  project = [{ id: 0, name: '', description: '', languages: '', arr: [''] }];

  date: string = '';
  onChangeInupt(event: any, id: number, value: string) {
    const item = this.items.find((i) => i.educId === id);
    if (item && value === 'name') {
      item.eduName = event.target.value;
    } else if (item && value === 'description') {
      item.description = event.target.value;
    } else if (item && value === 'date') {
      this.date = event.target.value;
      item.date = this.convertTime(this.date);
    }
  }
  onChangeInuptExperience(event: any, id: number, value: string) {
    const item = this.experience.find((i) => i.id === id);
    if (item && value === 'name') {
      item.name = event.target.value;
    } else if (item && value === 'description') {
      item.description = event.target.value;
    } else if (item && value === 'date') {
      this.date = event.target.value;
      item.date = this.convertTime(this.date);
    } else if (item && value === 'post') {
      item.post = event.target.value;
    } else if (item && value === 'location') {
      item.location = event.target.value;
    }
  }
  onChangeInuptProject(event: any, id: number, value: string) {
    const item = this.project.find((i) => i.id === id);
    if (item && value === 'name') {
      item.name = event.target.value;
    } else if (item && value === 'description') {
      item.description = event.target.value;
    } else if (item && value === 'languages') {
      item.languages = event.target.value;
      item.arr = item.languages.split(',');
      for (let i = 0; i < item.arr.length; i++) {
        item.arr[i] = item.arr[i].replace(',', '');
      }
    }
  }

  addInput() {
    this.items.push({
      educId: this.items.length + 1,
      eduName: '',
      description: '',
      date: '',
    });
  }
  addInputExperience() {
    this.experience.push({
      id: this.experience.length + 1,
      name: '',
      description: '',
      date: '',
      post: '',
      location: '',
    });
  }
  addInputProject() {
    this.project.push({
      id: this.project.length + 1,
      name: '',
      description: '',
      languages: '',
      arr: [''],
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
    this.splitString(this.formGroupSkill.value.skill).forEach((skill) => {
      this.saveService.saveSkills(skill).subscribe((response) => {
        console.log(response);
      });
    });
  }
}

