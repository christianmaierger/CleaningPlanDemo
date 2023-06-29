import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Room } from '../model/room';
import { Observable } from 'rxjs';


@Injectable()
export class RoomService {


  private roomsUrl: string;

  constructor(private http: HttpClient) {
    this.roomsUrl = 'http://localhost:8080/rooms';
  }

  public findAll(): Observable<Room[]> {
    let roomList: Observable<Room[]> = this.http.get<Room[]>(this.roomsUrl);
    roomList.forEach( room => console.log(room))
    return roomList;
  }

  public saveRoom(room: Room) {
    console.log(this.roomsUrl)
    console.log("And in the service room before post looks like: " + room.toString() + room.name)
    return this.http.post<Room>(this.roomsUrl, room);
  }

  public saveChange(room: Room) {
    return this.http.put(this.roomsUrl + '/' + room.id, room, { responseType: 'text' });
  }


  public deleteRoom(roomId: number) {
    const url = `${this.roomsUrl}/${roomId}`;
    console.log(this.roomsUrl)
    console.log("This url is called for deletion: " + url)
    return this.http.delete(url);
  }

}
