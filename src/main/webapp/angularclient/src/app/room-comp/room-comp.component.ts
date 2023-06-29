import { Component, Input } from '@angular/core';
import { Room } from "../model/room";

@Component({
  selector: 'app-room-comp',
  templateUrl: './room-comp.component.html',
  styleUrls: ['./room-comp.component.css']
})
export class RoomCompComponent {
  @Input() room: Room;

  constructor() {
    this.room = new Room(); // Initialize with default values
  }
}
