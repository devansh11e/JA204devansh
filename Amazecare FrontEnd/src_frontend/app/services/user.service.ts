import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { UserInfo } from '../models/UserInfo';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  baseUrl:string="http://localhost:8081/users/";
  constructor(private http:HttpClient) { }

  addNewUser(user:UserInfo):Observable<UserInfo>
  { return this.http.post<UserInfo>(this.baseUrl+`registration/new`,user,{responseType: 'text' as 'json',});}

  checkUsernameExists(username: string): Observable<boolean> {
    return this.http.get<boolean>(`${this.baseUrl}check-username?username=${username}`);
  }

  forgotPassword(username:string,password:string):Observable<any>
 { const body = { username, password };
  return this.http.post<any>(this.baseUrl+"forgot-password", body,{responseType: 'text' as 'json',});
}
}
