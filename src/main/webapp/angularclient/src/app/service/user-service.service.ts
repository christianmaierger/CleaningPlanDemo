import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { User } from '../model/user';
import { Observable } from 'rxjs';

@Injectable()
export class UserService {

  private usersUrl: string;

  constructor(private http: HttpClient) {
    this.usersUrl = 'http://localhost:8080/users';
  }

  public findAll(): Observable<User[]> {
    let userList: Observable<User[]> = this.http.get<User[]>(this.usersUrl);
    userList.forEach( user => console.log(user))
    return userList;
  }

  public save(user: User) {
    console.log("before sending user looks like", JSON.stringify(user));
    return this.http.post<User>(this.usersUrl, user);
  }

  public saveChange(user: User,) {
    const url = `${this.usersUrl}/${user.id}`;
    return this.http.put<User>(url, user);
  }

  public deleteUser(userId: number) {
    const url = `${this.usersUrl}/${userId}`;
    return this.http.delete(url);
  }



}
