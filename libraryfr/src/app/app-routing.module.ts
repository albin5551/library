import { Component, NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddUserComponent } from './admin/add-user/add-user.component';
import { AdminHomeComponent } from './admin/admin-home/admin-home.component';
import { BookDeatilviewComponent } from './admin/book-deatilview/book-deatilview.component';
import { BookRegistrationComponent } from './admin/book-registration/book-registration.component';
import { BookReportComponent } from './admin/book-report/book-report.component';
import { CategoryComponent } from './admin/category/category.component';
import { EditBookComponent } from './admin/edit-book/edit-book.component';
import { ReturnViewComponent } from './admin/return-view/return-view.component';
import { TestviewComponent } from './admin/testview/testview.component';
import { ViewBookregComponent } from './admin/view-bookreg/view-bookreg.component';
import { ViewpgTestComponent } from './admin/viewpg-test/viewpg-test.component';
import { ForgotPasswordComponent } from './login/forgot-password/forgot-password.component';
import { LoginComponent } from './login/login.component';
import { BookListComponent } from './user/book-list/book-list.component';
import { ReturnBookComponent } from './user/return-book/return-book.component';
import { UserHomeComponent } from './user/user-home/user-home.component';

const routes: Routes = [
  {path:'',redirectTo:'/login',pathMatch:'full'},
  {path:'login',component:LoginComponent},
  {path:'bookreg',component:BookRegistrationComponent},
  {path:'viewbook',component:ViewBookregComponent},
  {path:'editbook/:id',component:EditBookComponent},
  {path:'viewtest',component:ViewpgTestComponent},
  {path:'bookdview/:id',component:BookDeatilviewComponent},
  {path:'adminhome',component:AdminHomeComponent},
  {path:'testview',component:TestviewComponent},
  {path:'report',component:BookReportComponent},
  {path:'forgotpass',component:ForgotPasswordComponent},
  {path:'userhome',component:UserHomeComponent},
  {path:'bookuview',component:BookListComponent},
  {path:'return',component:ReturnBookComponent},
  {path:'adreturn',component:ReturnViewComponent},
  {path:'useradd',component:AddUserComponent},
  {path:'addcat',component:CategoryComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
