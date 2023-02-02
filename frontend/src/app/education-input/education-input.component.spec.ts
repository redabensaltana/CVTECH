import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EducationInputComponent } from './education-input.component';

describe('EducationInputComponent', () => {
  let component: EducationInputComponent;
  let fixture: ComponentFixture<EducationInputComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EducationInputComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EducationInputComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
