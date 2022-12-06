import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserServiceService {

  constructor(private http:HttpClient) { }

  addUser(data:any):Observable<any>{
    return this.http.post('http://localhost:8080/users',data);
  }
  getUserId(id:any):Observable<any>{
    return this.http.get('http://localhost:8080/users/'+id);
  }
  userEdit(id:any,data:any):Observable<any>{
    return this.http.patch('http://localhost:8080/users/'+id,data);
  }
}