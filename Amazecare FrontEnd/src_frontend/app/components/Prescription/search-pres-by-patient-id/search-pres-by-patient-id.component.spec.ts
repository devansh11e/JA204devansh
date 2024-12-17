import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchPresByPatientIdComponent } from './search-pres-by-patient-id.component';

describe('SearchPresByPatientIdComponent', () => {
  let component: SearchPresByPatientIdComponent;
  let fixture: ComponentFixture<SearchPresByPatientIdComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SearchPresByPatientIdComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SearchPresByPatientIdComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
