import { Component } from '@angular/core';

@Component({
  // selector binds to <app-root></app-root> in index html
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'This is a Cleaning Plan Website';
}
