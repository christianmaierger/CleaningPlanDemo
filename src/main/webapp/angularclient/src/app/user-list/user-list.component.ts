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
  rowEditable: boolean[] = [];
  emailInvalid = false;
  nameInvalid = false;


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
    this.router.navigateByUrl('/', {skipLocationChange: true}).then(() => {
      this.router.navigate(['/users']);
    });
  }

  saveChanges(user: User) {
    this.userService.saveChange(user).subscribe(
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
      this.rowEditable[i] = false;
    } else {
      this.rowEditable[i] = true;
    }
  }

  // when the input for mail changes, this is triggerd based on if validation throws errors
  onEmailInputChange(email: any) {
    console.log("onEmailInputChangecalled")
   if ( email.errors && (email.dirty || email.touched)) {
     this.emailInvalid = true ;
   } else {
     this.emailInvalid = false;
   }
    console.log(this.emailInvalid)
  }

  onNameInputChange(name: any) {

    if ( name.errors && (name.dirty || name.touched)) {
      this.emailInvalid = true ;
    } else {
      this.emailInvalid = false;
    }
    console.log(this.nameInvalid)
  }

}



