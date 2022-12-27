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
  getUser(){
    return this.http.get('http://localhost:8080/users');
  }
  getUserId(id:any):Observable<any>{
    return this.http.get('http://localhost:8080/users/'+id);
  }
  userEdit(id:any,data:any):Observable<any>{
    return this.http.patch('http://localhost:8080/users/'+id,data);
  }
  getBook(){
    return this.http.get('http://localhost:8080/book');
  }
  rentBook(data:any){
    return this.http.post('http://localhost:8080/rent/',data);
  }

  pagenate(pageno:any,pagesize:any,sortby:any):Observable<any>{
    return this.http.get('http://localhost:8080/book/pagenateds?pageNo='+pageno+'&pageSize='+pagesize+'&sortBy='+sortby)

  }

  rentDetails(){
    return this.http.get('http://localhost:8080/rent/list/user')
  }

  retrun(id:any,data:any){
    // console.log(id,"  ",data);
    
    
    return this.http.put('http://localhost:8080/rent/'+id,data)
  }

  getbyRentId(id:any){
    return this.http.get('http://localhost:8080/rent/'+id)
  }
  
}