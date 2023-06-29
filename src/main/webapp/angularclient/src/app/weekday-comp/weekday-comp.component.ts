import { Component, OnInit } from '@angular/core';
import { Room } from '../model/room';
import { Weekday } from '../model/weekday';
import { RoomService } from '../service/room-service.service';
import { WeekdayService } from '../service/weekday-service';

@Component({
  selector: 'app-weekday-comp',
  templateUrl: './weekday-comp.component.html',
  styleUrls: ['./weekday-comp.component.css']
})
export class WeekdayCompComponent implements OnInit {
  weekday: Weekday;
  room: Room[];

  constructor(private weekdayService: WeekdayService) {
    this.weekday = new Weekday();
    this.room =  [];
  }


  ngOnInit() {
    this.weekdayService.getWeekdayById(this.weekday.id).subscribe(data => {
      this.weekday = data;
    });
  }

  getWeekday(id: number) {
    this.weekdayService.getWeekdayById(id).subscribe(
      (response: Weekday) => {
        this.weekday = response;
      },
      (error) => {
        console.log('Error retrieving weekday:', error);
      }
    );
  }
}

