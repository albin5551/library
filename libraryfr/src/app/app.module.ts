import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule } from '@angular/router';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {HttpClientModule,HTTP_INTERCEPTORS} from "@angular/common/http"

import { LoginComponent } from './login/login.component';
import { TokenServiceService } from './service/token-service.service';
import { AdminHomeComponent } from './admin/admin-home/admin-home.component';
import { BookRegistrationComponent } from './admin/book-registration/book-registration.component';
import { ViewBookregComponent } from './admin/view-bookreg/view-bookreg.component';
import { EditBookComponent } from './admin/edit-book/edit-book.component';
import { ViewpgTestComponent } from './admin/viewpg-test/viewpg-test.component';
import { BookDeatilviewComponent } from './admin/book-deatilview/book-deatilview.component';
import { NavbarAdminComponent } from './admin/navbar-admin/navbar-admin.component';
import { TestviewComponent } from './admin/testview/testview.component';
import { BookReportComponent } from './admin/book-report/book-report.component';
import { UserHomeComponent } from './user/user-home/user-home.component';
import { ForgotPasswordComponent } from './login/forgot-password/forgot-password.component';
import { NgToastModule } from 'ng-angular-popup';
import { NavUserComponent } from './user/nav-user/nav-user.component';
import { BookListComponent } from './user/book-list/book-list.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    AdminHomeComponent,
    BookRegistrationComponent,
    ViewBookregComponent,
    EditBookComponent,
    ViewpgTestComponent,
    BookDeatilviewComponent,
    NavbarAdminComponent,
    TestviewComponent,
    BookReportComponent,
    UserHomeComponent,
    ForgotPasswordComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    RouterModule,
    ReactiveFormsModule,
    HttpClientModule,
    NgToastModule
    
  ],
  providers: [{provide:HTTP_INTERCEPTORS,useClass:TokenServiceService,multi:true}],
  bootstrap: [AppComponent]
})
export class AppModule { }
