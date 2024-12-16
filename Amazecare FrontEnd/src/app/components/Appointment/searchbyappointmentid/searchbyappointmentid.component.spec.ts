import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchbyappointmentidComponent } from './searchbyappointmentid.component';

describe('SearchbyappointmentidComponent', () => {
  let component: SearchbyappointmentidComponent;
  let fixture: ComponentFixture<SearchbyappointmentidComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SearchbyappointmentidComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SearchbyappointmentidComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
