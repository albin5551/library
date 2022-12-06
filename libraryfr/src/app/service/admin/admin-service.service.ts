import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AdminServiceService {

  constructor(private http:HttpClient){ }

  addBook(data:any):Observable<any>{
    return this.http.post('http://localhost:8080/book',data)
  }

  getBook(){
    return this.http.get('http://localhost:8080/book')
  }
  getBookbyId(id:any):Observable<any>{
    return this.http.get('http://localhost:8080/book/'+id)
  }
  updateBook(id:any,data:any){
    return this.http.put('http://localhost:8080/book/'+id,data)
  }
  pagenate(pageno:any,pagesize:any,sortby:any){
    return this.http.get('http://localhost:8080/book/pagenated?pageNo='+pageno+'&pageSize='+pagesize+'&sortBy='+sortby)

  }

  deletBook(id:any):Observable<any>{
    return this.http.delete('http://localhost:8080/book/'+id)
  }
}
