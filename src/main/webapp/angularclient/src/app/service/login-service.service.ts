import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot} from "@angular/router";

@Injectable()
export class LoginService {

  authenticated = false;
  private usersUrl: string;

  constructor(private http: HttpClient) {
    this.usersUrl = 'http://localhost:8080/users';
  }

  authenticate(credentials: any, callback?: (error?: Error) => void) {
    const headers = new HttpHeaders(credentials ? {
      authorization: 'Basic ' + btoa(credentials.username + ':' + credentials.password + ':' + credentials.role)
    } : {});

    this.http.get(this.usersUrl, { headers: headers }).subscribe(
      (response: any) => {
        console.log("THis is the server response for auth reqest" + response)

        if (response['name']) {
          this.authenticated = true;
          callback && callback(); // Invoke the callback function if it's provided
        } else {
          this.authenticated = false;
          callback && callback(new Error("Username and/or password false")); // Invoke the callback function with an error parameter if it's provided
        }
      },
      (error) => {
        // Handle error case here
        console.log("This error happend during auth " + error)
        callback && callback(error);
      }
    );
  }
}

@Injectable()
export class AuthGuard implements CanActivate {
  constructor(private authService: LoginService, private router: Router) {}

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): boolean {
   // if (this.authService.authenticated) {
    if (true==true) {
      console.log("user is authentificated")
      return true;
    } else {
      console.log("user is not authentificated will redirect")
      this.router.navigate(['/login']);
      return false;
    }
  }
}
