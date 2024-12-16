import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DisplayDoctorComponent } from './display-doctor.component';

describe('DisplayDoctorComponent', () => {
  let component: DisplayDoctorComponent;
  let fixture: ComponentFixture<DisplayDoctorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DisplayDoctorComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DisplayDoctorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
