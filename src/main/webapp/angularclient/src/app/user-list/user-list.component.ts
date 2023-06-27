import { Component, OnInit } from '@angular/core';
import { User } from '../model/user';
import { UserService } from '../service/user-service.service';
import {ActivatedRoute, Router} from "@angular/router";




@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit {

  users: User[] = [];




  constructor(private userService: UserService, private route: ActivatedRoute,
  private router: Router) {
  }
  // hook is intiated at init and method is called
  ngOnInit() {
    this.userService.findAll().subscribe(data => {
      this.users = data;
    });
  }

  deleteUser(id: string) {
   const num = parseFloat(id);


    this.userService.deleteUser(num).subscribe(result => this.gotoUserList());


  }
  gotoUserList() {
    console.log("After deletion, reloading the page");
    this.router.navigateByUrl('/', { skipLocationChange: true }).then(() => {
      this.router.navigate(['/users']);
    });
  }


}



