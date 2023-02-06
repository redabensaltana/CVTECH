import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CvEditComponent } from './cv-edit.component';

describe('CvEditComponent', () => {
  let component: CvEditComponent;
  let fixture: ComponentFixture<CvEditComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CvEditComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CvEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
