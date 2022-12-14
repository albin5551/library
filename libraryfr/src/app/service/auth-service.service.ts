import { HttpBackend, HttpClient, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthServiceService {

  constructor(private http:HttpClient,private httpBackend: HttpBackend) { }
  login(data: any):Observable<any>{
    return this.http.post('http://localhost:8080/login',data);
  }

  getToken(){
    return localStorage.getItem('token')||"";
  }

  sendotp(data:any):Observable<any>{
    return this.http.post('http://localhost:8080/email/emailsentotp',data)
  }

  verify(data:any):Observable<any>{
    return this.http.post('http://localhost:8080/verify',data)
  }


 sendotpw(data:any):Observable <any>{
  return this.httpBackend
  .handle(new HttpRequest('POST','http://localhost:8080/email/emailsentotp',data));
 }
 verifyotp(data:any):Observable <any>{
  return this.httpBackend
  .handle(new HttpRequest('POST','http://localhost:8080/verify',data));
 }
}
