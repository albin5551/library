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

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    AdminHomeComponent,
    BookRegistrationComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    RouterModule,
    ReactiveFormsModule,
    HttpClientModule,
    
  ],
  providers: [{provide:HTTP_INTERCEPTORS,useClass:TokenServiceService,multi:true}],
  bootstrap: [AppComponent]
})
export class AppModule { }
