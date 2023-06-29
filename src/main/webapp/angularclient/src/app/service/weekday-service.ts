import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Weekday } from '../model/weekday';
import {Room} from "../model/room";

@Injectable()
export class WeekdayService {
  private weekdaysUrl: string;

  constructor(private http: HttpClient) {
    this.weekdaysUrl = 'http://localhost:8080/weekdays';
  }


  public getWeekdayById(id: number): Observable<Weekday> {
    const url = `${this.weekdaysUrl}/${id}`;
    return this.http.get<Weekday>(url);
  }

  public addRoomToWeekday(weekdayId: number, room: Room): Observable<void> {
    const url = `${this.weekdaysUrl}/${weekdayId}`;
    return this.http.post<void>(url, room);
  }

  public deleteRoomFromWeekday(weekdayId: number, room: Room): Observable<void> {
    const url = `${this.weekdaysUrl}/${weekdayId}`;
    return this.http.delete<void>(url, { body: room });
  }
}
