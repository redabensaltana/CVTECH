import { Component } from '@angular/core';
import { GetService } from 'src/app/services/request/get.service';
import { ActivatedRoute } from '@angular/router';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-apprenant-profile',
  templateUrl: './apprenant-profile.component.html',
  styleUrls: ['./apprenant-profile.component.css']
})
export class ApprenantProfileComponent {
  ids : string = '';
   constructor(private route: ActivatedRoute, private getService: GetService) {
    this.route.params.subscribe((params) => {
      this.ids = params['id'];
      console.log(this.ids);
    });
  }

}
