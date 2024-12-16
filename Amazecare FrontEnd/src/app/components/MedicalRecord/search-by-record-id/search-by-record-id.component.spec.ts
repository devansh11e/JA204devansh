import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchByRecordIdComponent } from './search-by-record-id.component';

describe('SearchByRecordIdComponent', () => {
  let component: SearchByRecordIdComponent;
  let fixture: ComponentFixture<SearchByRecordIdComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SearchByRecordIdComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SearchByRecordIdComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
