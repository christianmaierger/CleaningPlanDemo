import { Component } from '@angular/core';
import { LoginService } from '../service/login-service.service';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';
import { subscribeOn } from 'rxjs';


@Component({
  templateUrl: './login.component.html'
})
export class LoginComponent {

  credentials = { username: '', password: '' };
  protected error: boolean = false;

  constructor(private app: LoginService, private http: HttpClient, private router: Router) {
  }

  login() {
    this.error = false; // Reset error flag before making the request

    this.app.authenticate(this.credentials, (e?: Error) => {
      if (e) {
        this.error = true; // Set the error flag to display the error message
        console.error('Login error:', e);
        // todo perhaps route to special failure page or back to login
      } else {
        // Successful authentication
        this.router.navigateByUrl('/');
      }
    })

    return false;
  }




}
