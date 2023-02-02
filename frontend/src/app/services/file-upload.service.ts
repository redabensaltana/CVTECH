import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
@Injectable({
  providedIn: 'root',
})
export class FileUploadService {
  getBase64(file: File | undefined) {
    throw new Error('Method not implemented.');
  }
  constructor(private http: HttpClient) {}

  saveImage(image: File) {
    const formData = new FormData();
    formData.append('image', image);

    this.http.post('/api/save-image', formData).subscribe(() => {
      console.log('Image saved successfully');
    });
  }
}
