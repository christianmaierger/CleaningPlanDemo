import { Room } from "./room";
import {WeekDay} from "@angular/common";



export class Weekday {
  // the rooms to be cleaned that day
  private _rooms: Room[] = [];
  // WHich day it is, starting from 0 with Sunday to 6 Saturday
  private _day!: WeekDay;
  id!: number;



  // Getters and setters
  get day(): WeekDay {
    return this._day;
  }

  set day(value: WeekDay) {
    this._day = value;
  }


  get rooms(): Room[] {
    return this._rooms;
  }

  set rooms(value: Room[]) {
    this._rooms = value;
  }
}



