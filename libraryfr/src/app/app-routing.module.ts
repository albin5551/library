import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BookRegistrationComponent } from './admin/book-registration/book-registration.component';
import { LoginComponent } from './login/login.component';

const routes: Routes = [

  {path:'login',component:LoginComponent},
  {path:'bookreg',component:BookRegistrationComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
