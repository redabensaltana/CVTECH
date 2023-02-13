import { Component } from '@angular/core';
import { FormGroup, FormControl, FormArray, Validators } from '@angular/forms';
@Component({
  selector: 'app-education-input',
  templateUrl: './education-input.component.html',
  styleUrls: ['./education-input.component.css']
})
export class EducationInputComponent {
 form: FormGroup;
  inputs = [];
 constructor() {
    this.form = new FormGroup({
      inputs: new FormArray([])
    });
  }
      addInput() {
    const inputs = this.form.get('inputs') as FormArray;
    inputs.push(new FormControl(''));
  }


}
