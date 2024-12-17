import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DisplayRecordComponent } from './display-record.component';

describe('DisplayRecordComponent', () => {
  let component: DisplayRecordComponent;
  let fixture: ComponentFixture<DisplayRecordComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DisplayRecordComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DisplayRecordComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
