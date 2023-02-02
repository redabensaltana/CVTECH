import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { FileUploadService } from '../../services/file-upload.service';

@Component({
  selector: 'app-file-upload',
  templateUrl: './file-upload.component.html',
  styleUrls: ['./file-upload.component.css'],
})
export class FileUploadComponent {
  constructor(private http: HttpClient) {}
  images : String[] = [];
  onFileChanged(event: any): void {
  //get base64 of multiple images and console log it

    const files = event.target.files;
    if (files) {
      for (let file of files) {
        const reader = new FileReader();
        reader.onload = (e: any) => {
          // console.log("file " + e.target.result);
          console.log("file : " + e.target.result.split(',')[1] + "\n");
          this.images.push(e.target.result.split(',')[1]);
        };
        reader.readAsDataURL(file);
      }
    }



  }

  // sendImage(
  //   //imageBase64WithoutPrefix as array of strings
  //   imageBase64WithoutPrefix: string[],
  //   name: string,
  //   lastName: string
  // ): void {
  //   const body = {
  //     image: imageBase64WithoutPrefix,
  //     name: name,
  //     lastName: lastName,
  //   };
  //   const headers = new HttpHeaders().set('Content-Type', 'application/json');
  //   this.http.post('/api/send-image', body, { headers }).subscribe();
  // }
  new(): void {
    const petImageDto = {
      images : this.images,
      petId : 1,
      name : "test",
    };
    const headers = new HttpHeaders().set('Content-Type', 'application/json');
    console.log(JSON.stringify(petImageDto));
    this.http
      .post('http://localhost:8080/image/new', JSON.stringify(petImageDto), { headers })
      .subscribe();
  }
  
}
