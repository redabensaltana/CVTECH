import { TestBed } from '@angular/core/testing';

import { PetImageService } from './pet-image.service';

describe('PetImageService', () => {
  let service: PetImageService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PetImageService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
