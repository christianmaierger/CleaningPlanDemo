import { ComponentFixture, TestBed } from '@angular/core/testing';

import { WeekdayCompComponent } from './weekday-comp.component';

describe('WeekdayCompComponent', () => {
  let component: WeekdayCompComponent;
  let fixture: ComponentFixture<WeekdayCompComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [WeekdayCompComponent]
    });
    fixture = TestBed.createComponent(WeekdayCompComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
