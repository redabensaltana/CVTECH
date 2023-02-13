import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GenerateCvComponent } from './generate-cv.component';

describe('GenerateCvComponent', () => {
  let component: GenerateCvComponent;
  let fixture: ComponentFixture<GenerateCvComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GenerateCvComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GenerateCvComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
