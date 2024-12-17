import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchbypatientidComponent } from './searchbypatientid.component';

describe('SearchbypatientidComponent', () => {
  let component: SearchbypatientidComponent;
  let fixture: ComponentFixture<SearchbypatientidComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SearchbypatientidComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SearchbypatientidComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
