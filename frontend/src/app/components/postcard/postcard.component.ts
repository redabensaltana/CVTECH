import { Component, OnInit } from '@angular/core';
import { GetService } from 'src/app/services/request/get.service';
import { FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: '[app-postcard]',
  templateUrl: './postcard.component.html',
  styleUrls: ['./postcard.component.css'],
})
export class PostcardComponent implements OnInit {
  constructor(private getService: GetService) {}
  searchControl = new FormControl('');
  searchForm = new FormGroup({
    selectControl: new FormControl('', [Validators.required]),
  });
  filterBy : string = '';
  ngOnInit(): void {
    this.getUsers();
    console.log(this.devs);

     this.searchForm.get('selectControl')?.valueChanges.subscribe((value) => {
      this.searchControl.valueChanges.subscribe((values) => {
        if (values && value === "lastName") {
          this.devs = this.devs.filter((dev: any) => {
            return dev.lastName.toLowerCase().includes(values.toLowerCase());
          });
        }else if(values && value === 'userTitle'){

          this.devs = this.devs.filter((dev: any) => {
            return dev.userTitle.toLowerCase().includes(values.toLowerCase());
          });
        }
        else if(values && value == 'email'){
          this.devs = this.devs.filter((dev: any) => {
            return dev.email.toLowerCase().includes(values.toLowerCase());
          });
        }
         else if(values == null || values == ''){
          this.devs = [];
          this.getUsers();
        }
      });
     });
  }

  users: any = [{}];
  devs: any = [];
  getUsers() {
    this.getService.getUsers().subscribe((data) => {
      this.users = data;
      this.users.forEach((element: any) => {
        if (element.role == 'DEV') {
          this.devs.push(element);
        }
      });
    });
  }

}


