import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { UserListComponent } from './user-list/user-list.component';
import { UserFormComponent } from './user-form/user-form.component';
import { LoginComponent } from './login/login.component';
import {AuthGuard} from "./service/login-service.service";

const routes: Routes = [
  { path: 'users', component:UserListComponent, canActivate: [AuthGuard] },
  { path: 'adduser', component: UserFormComponent },
  { path: 'login', component: LoginComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
