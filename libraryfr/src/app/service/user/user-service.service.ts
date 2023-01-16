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
  bookByAuthorlistandcatid( catid: any[], authorname: any[],pageno: any, pagesize: any, sortby: any): Observable<any> {
    
    console.log('---',catid,'++++',authorname);
    
    return this.http.get('http://localhost:8080/book/ufilter?catid=' + catid + '&author=' + authorname + '&pageNo=' + pageno + '&pageSize=' + pagesize + '&sortBy=' + sortby)

  }
  notfiview():Observable<any>{
    return this.http.get('http://localhost:8080/notification/viewmsg')
  }
  request(bookId:any):Observable<any>{

    return this.http.post('http://localhost:8080/request/',bookId)

  }
  notificationClear(){
    console.log("here")
    return this.http.delete('http://localhost:8080/notification/read')
  }
}