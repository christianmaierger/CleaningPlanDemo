import {Component, OnInit} from '@angular/core';
import { Room } from '../model/room';
import { RoomService } from '../service/room-service.service';
import {ActivatedRoute, Router} from "@angular/router";



@Component({
  selector: 'app-room-list',
  templateUrl: './room-list.component.html',
  styleUrls: ['./room-list.component.css']
})
export class RoomListComponent implements OnInit {

  // this will be a list of all rooms registered in the application
  rooms: Room[] = [];
  newRoom: Room;
  selectedRowIndex?: number;
  rowEditable: boolean[] = [];


  constructor(private roomService: RoomService, private route: ActivatedRoute,
              private router: Router) {
    this.newRoom= new Room();
  }

  // hook is intiated at init and method is called



  ngOnInit() {
    this.roomService.findAll().subscribe(data => {
      this.rooms = data;
    });
  }

  onSubmit() {
    console.log(this.newRoom)
    this.roomService.saveRoom(this.newRoom).subscribe(result => this.gotoRoomList());
  }

  deleteRoom(id: number) {
    this.roomService.deleteRoom(id).subscribe(result => this.gotoRoomList());
  }
  gotoRoomList() {
    console.log("After deletion, reloading the page");
    this.router.navigateByUrl('/', { skipLocationChange: true }).then(() => {
      this.router.navigate(['/rooms']);
    });
  }

  saveChanges(room: Room) {
    this.roomService.saveChange(room).subscribe(
      response => {
        console.log(response); // Log the response body
        // Handle the response as needed
      },
      error => {
        console.log(error); // Log any error that occurs
        // Handle the error as needed
      }
    );

  }

  toggleEdit(i: any) {
      if (this.rowEditable[i]) {
        this.rowEditable[i]=false;
      } else {
        this.rowEditable[i]=true;
      }

  }
}
