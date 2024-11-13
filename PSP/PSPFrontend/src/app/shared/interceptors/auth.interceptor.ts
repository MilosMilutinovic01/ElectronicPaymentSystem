import {
  HttpEvent,
  HttpHandlerFn,
  HttpHeaders,
  HttpInterceptorFn,
  HttpRequest,
} from '@angular/common/http';
import { ACCESS_TOKEN } from '../model/constant.model';
import { Observable } from 'rxjs';

export const authInterceptor: HttpInterceptorFn = (
  req: HttpRequest<unknown>,
  next: HttpHandlerFn
): Observable<HttpEvent<unknown>> => {
  const token = localStorage.getItem(ACCESS_TOKEN);
  let headers = {
    'Content-Type': 'application/json;charset=UTF-8',
    'Access-Control-Allow-Origin': '*',
    Authorization: 'Bearer ' + localStorage.getItem(ACCESS_TOKEN),
  };
  const req1 = req.clone({
    setHeaders: headers,
  });
  console.log(req, req1)
  if (req.url.includes('auth')) {
    return next(req);
  }
  return next(req1);
};
