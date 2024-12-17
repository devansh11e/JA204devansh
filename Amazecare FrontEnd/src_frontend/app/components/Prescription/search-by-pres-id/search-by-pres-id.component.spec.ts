import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchByPresIdComponent } from './search-by-pres-id.component';

describe('SearchByPresIdComponent', () => {
  let component: SearchByPresIdComponent;
  let fixture: ComponentFixture<SearchByPresIdComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SearchByPresIdComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SearchByPresIdComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
