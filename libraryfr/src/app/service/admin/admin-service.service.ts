import { HttpClient, HttpEvent, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AdminServiceService {

  constructor(private http: HttpClient) { }

  addBook(data: any): Observable<any> {
    return this.http.post('http://localhost:8080/book', data)
  }

  getBook() {
    return this.http.get('http://localhost:8080/book')
  }
  getBookbyId(id: any): Observable<any> {
    return this.http.get('http://localhost:8080/book/' + id)
  }
  updateBook(id: any, data: any) {
    return this.http.put('http://localhost:8080/book/' + id, data)
  }
  pagenate(pageno: any, pagesize: any, sortby: any): Observable<any> {
    return this.http.get('http://localhost:8080/book/pagenated?pageNo=' + pageno + '&pageSize=' + pagesize + '&sortBy=' + sortby)

  }

  deletBook(id: any): Observable<any> {
    return this.http.delete('http://localhost:8080/book/' + id)
  }

  retrunview() {
    return this.http.get('http://localhost:8080/rent')
  }
  returnApprove(id: any, data: any) {
    return this.http.put('http://localhost:8080/rent/approve/' + id, data)
  }

  pagenated(pageno: any, pagesize: any, sortby: any):Observable<any> {
    return this.http.get('http://localhost:8080/rent/pagenated?pageNo=' + pageno + '&pageSize=' + pagesize + '&sortBy=' + sortby)

  }
  rentSearch(data: any, pageno: any, pagesize: any, sortby: any): Observable<any> {
    return this.http.get('http://localhost:8080/rent/search/pagenateds?keyword=' + data + '&pageNo=' + pageno + '&pageSize=' + pagesize + '&sortBy=' + sortby)
  }

  search(data: any, pageno: any, pagesize: any, sortby: any): Observable<any> {
    console.log('+++++++++++', data)
    return this.http.get('http://localhost:8080/book/search/pagenateds?keyword=' + data + '&pageNo=' + pageno + '&pageSize=' + pagesize + '&sortBy=' + sortby)
  }

  chart():Observable<any>{
    return this.http.get('http://localhost:8080/book/bycategory')
  }
  chartbar():Observable<any>{
    return this.http.get('http://localhost:8080/rent/chart')
  }

  // uploadCsv(file:File):Observable<HttpEvent<any>>{
  //   const formData: FormData = new FormData();

  //   formData.append('file', file);

  //       const req = new HttpRequest('POST', 'http://localhost:8080/csv/upload', formData, {
  //     reportProgress: true,
  //     responseType: 'json'

  //   });
  //   return this.http.request(req)
  // }

  upload(file: File): Observable<any> {
    const formData: FormData = new FormData();

    formData.append('file', file);
    return this.http.post('http://localhost:8080/csv/upload', formData)

  }

  download(): Observable<Blob> {
    return this.http.get('http://localhost:8080/csv/download', { responseType: 'blob' });
  }
  export(): Observable<Blob> {
    return this.http.get('http://localhost:8080/rent/export', { responseType: 'blob' });
  }

  addCategory(data:any):Observable<any>{
    return this.http.post('http://localhost:8080/category',data)
  }
  loadCategory():Observable<any>{
    return this.http.get('http://localhost:8080/category');
  }
  exportSearch(data:any): Observable<Blob> {
    return this.http.get('http://localhost:8080/rent/search/export/'+data, { responseType: 'blob' });
  }

  imageUpload(image:any,bookId:any):Observable<any>{


    const formData: FormData = new FormData();
    formData.append('image',image );
    

    return this.http.post('http://localhost:8080/book/save/image/'+bookId,formData);

  }
}
