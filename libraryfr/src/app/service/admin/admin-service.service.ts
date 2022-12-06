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
}
