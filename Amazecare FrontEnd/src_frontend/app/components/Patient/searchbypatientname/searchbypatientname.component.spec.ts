import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchbypatientnameComponent } from './searchbypatientname.component';

describe('SearchbypatientnameComponent', () => {
  let component: SearchbypatientnameComponent;
  let fixture: ComponentFixture<SearchbypatientnameComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SearchbypatientnameComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SearchbypatientnameComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
