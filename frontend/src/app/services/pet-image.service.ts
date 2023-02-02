import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class PetImageService {
  constructor(private http: HttpClient) {}

  sendPetImage(petImageDto: any) {
    const formData = new FormData();
    formData.append('name', petImageDto.name);
    formData.append('age', petImageDto.age.toString());
    for (const image of petImageDto.images) {
      formData.append('images', image);
    }

    const headers = new HttpHeaders();
    headers.set('Content-Type', 'multipart/form-data');

    return this.http.post('/api/new', formData, { headers });
  }
}
