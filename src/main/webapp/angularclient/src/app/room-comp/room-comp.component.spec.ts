import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RoomCompComponent } from './room-comp.component';

describe('RoomCompComponent', () => {
  let component: RoomCompComponent;
  let fixture: ComponentFixture<RoomCompComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [RoomCompComponent]
    });
    fixture = TestBed.createComponent(RoomCompComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
