import { Component } from '@angular/core';
import { LoginService } from './service/login-service.service';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { finalize } from 'rxjs/operators';


@Component({
  // selector binds to <app-root></app-root> in index html
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})

export class AppComponent {
  title = 'This is a Cleaning Plan Website';

  constructor(protected app: LoginService, private http: HttpClient, private router: Router) {

  }


  logout() {
    this.http.post('logout', {}).pipe(
      finalize(() => {
        this.app.authenticated = false;
        this.router.navigateByUrl('/login');
      })
    ).subscribe();
  }
}
