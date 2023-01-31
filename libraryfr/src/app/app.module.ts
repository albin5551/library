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
import { NgxPaginationModule } from 'ngx-pagination';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatPaginatorModule} from '@angular/material/paginator';
import { ReturnBookComponent } from './user/return-book/return-book.component';
import { ReturnViewComponent } from './admin/return-view/return-view.component';
import { AddUserComponent } from './admin/add-user/add-user.component';
import { DatePipe } from '@angular/common';
import { CategoryComponent } from './admin/category/category.component';
import { BookViewTestComponent } from './user/book-view-test/book-view-test.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import {MatDialogModule} from '@angular/material/dialog';
import { NotificationComponent } from './user/notification/notification.component';
import { MatButton, MatButtonModule } from '@angular/material/button';
import { RequestviewComponent } from './admin/requestview/requestview.component';
import { MatBadgeModule} from '@angular/material/badge'; 
import { MatIconModule} from '@angular/material/icon'; 
import { NgxNavbarModule } from 'ngx-bootstrap-navbar';
import { TrhomeComponent } from './login/trhome/trhome.component';




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
    ForgotPasswordComponent,
    NavUserComponent,
    BookListComponent,
    ReturnBookComponent,
    ReturnViewComponent,
    AddUserComponent,
    CategoryComponent,
    BookViewTestComponent,
    NotificationComponent,
    RequestviewComponent,
    TrhomeComponent,

    
   
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    RouterModule,
    ReactiveFormsModule,
    HttpClientModule,
    NgToastModule,
    NgxPaginationModule,
    BrowserAnimationsModule,
    MatPaginatorModule,
    NgbModule,
    MatDialogModule,
    MatButtonModule,
    MatBadgeModule,
    MatIconModule,
    NgxNavbarModule,
 
    
  
    
   
    
  
  ],
  providers: [{provide:HTTP_INTERCEPTORS,useClass:TokenServiceService,multi:true},[DatePipe]],
  bootstrap: [AppComponent],
  entryComponents:[NotificationComponent]
})
export class AppModule { }
