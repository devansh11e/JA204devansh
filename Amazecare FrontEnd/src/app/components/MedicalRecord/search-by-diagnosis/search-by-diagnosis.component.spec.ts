import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchByDiagnosisComponent } from './search-by-diagnosis.component';

describe('SearchByDiagnosisComponent', () => {
  let component: SearchByDiagnosisComponent;
  let fixture: ComponentFixture<SearchByDiagnosisComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SearchByDiagnosisComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SearchByDiagnosisComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
