import { Component } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent {
  formGroupEducation = new FormGroup({});
  createEducationForm() {
    for (let i = 0; i < this.items.length; i++) {
      this.formGroupEducation.addControl(
        'name' + i,
        new FormControl(this.items[i].name)
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
  constructor() {
    this.createEducationForm();
  }
  formGroupInfromation: FormGroup = new FormGroup({
    fullname: new FormControl('Full Name', [Validators.required]),
    email: new FormControl('Your Email', [Validators.required]),
    github: new FormControl('Your Github', [Validators.required]),
    phone: new FormControl('xxx-xxx-xxxx', [Validators.required]),
    location: new FormControl('Your Location', [Validators.required]),
  });
  // formGroupEducation: FormGroup = new FormGroup({
  //   school: new FormControl('Your School', [Validators.required]),
  //   degree: new FormControl('Your Degree', [Validators.required]),
  //   major: new FormControl('Your Major', [Validators.required]),
  //   gpa: new FormControl('Your GPA', [Validators.required]),
  //   graduation: new FormControl('Your Graduation', [Validators.required]),
  // });
  informationStatus: boolean = false;
  closeInformation: boolean = false;
  openInformation: boolean = true;
  educationStatus: boolean = false;
  closeEducation: boolean = false;
  openEducation: boolean = true;

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

  // items: { id: number; value: string }[] = [{ id: 0, value: '' }];
  itemIndex = 0;

  // addInput() {
  //   this.items.push({ id: this.itemIndex + 1, value: '' });
  // }
  // onChangeInupt(event: any, index: number) {
  //   console.log(event.target.value, index);
  //   this.items[index].value = event.target.value;
  // }
  items = [{ id: 0, name: '', description: '', date: '' }];
  date: string = '';
  onChangeInupt(event: any, id: number, value: string) {
    const item = this.items.find((i) => i.id === id);
    if (item && value === 'name') {
      item.name = event.target.value;
    } else if (item && value === 'description') {
      item.description = event.target.value;
    } else if (item && value === 'date') {
      this.date = event.target.value;
      item.date = this.convertTime(this.date);
    }
  }
  addInput() {
    this.items.push({
      id: this.items.length + 1,
      name: '',
      description: '',
      date: '',
    });
  }
  removeInput(index: number) {
    this.items.splice(index, 1);
  }

  //function return "hello world"
  convertTime(time: string) {
    console.log(time);

    let parts = time.split('-');
    let d = new Date(`${parts[0]}-${parts[1]}-${parts[2]}`);
    let formattedDate =
      d.toLocaleString('default', { month: 'short' }) + ' ' + parts[0];
    return formattedDate;
  }

  title = 'resume';
}
