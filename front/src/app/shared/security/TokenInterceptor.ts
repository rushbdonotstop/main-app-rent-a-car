import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent } from '@angular/common/http';
import { AuthService } from 'src/app/core/services/auth.service';
import { Observable } from 'rxjs';

@Injectable()
export class TokenInterceptor implements HttpInterceptor {

  constructor(public authService: AuthService) {}

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
      if(this.authService.getToken()) {
        request = this.addToken(request, this.authService.getToken());
      }
    // @ts-ignore
    return next.handle(request);
  }

  private addToken(request: HttpRequest<any>, token: string) {
    const header = 'Bearer ' + token;
    return request.clone({
      setHeaders: {
        Authorization: header
      }
    });
  }
}