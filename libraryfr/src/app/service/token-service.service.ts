import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Injectable, Injector } from '@angular/core';
import { Observable } from 'rxjs';
import { AuthServiceService } from './auth-service.service';

@Injectable({
  providedIn: 'root'
})
export class TokenServiceService implements HttpInterceptor {

  constructor(private inject:Injector) { }
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

    let authService=this.inject.get(AuthServiceService);
    let jwttoken=req.clone({
      setHeaders:{
        content:'application/json',
        Authorization:'Library '+authService.getToken()
      }
    })
    return next.handle(jwttoken);
      
  }
}
