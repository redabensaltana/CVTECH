import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ApprenantProfileComponent } from './apprenant-profile.component';

describe('ApprenantProfileComponent', () => {
  let component: ApprenantProfileComponent;
  let fixture: ComponentFixture<ApprenantProfileComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ApprenantProfileComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ApprenantProfileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
