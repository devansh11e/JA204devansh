import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class JwtClientService {

  constructor(private http:HttpClient,private router:Router) { }


    baseURL:string = 'http://localhost:8081/';

    getGeneratedToken(requestBody: any){

        return this.http.post(this.baseURL+"users/login/authenticate",requestBody,{responseType: 'text' as 'json'});

    }

    getUserRole(username: string, token: string) {
      // Prepare the Authorization header with the token
      const tokenString = 'Bearer ' + token;
      const headers = new HttpHeaders({
        'Content-Type': 'application/json',
        'Access-Control-Allow-Origin': 'http://localhost:4200',
      }).set('Authorization', tokenString);
  
      // Make the GET request to fetch the role
      return this.http.get(this.baseURL + 'users/role', {
        headers,
        params: { username },
        responseType: 'text', // Expect plain text response
      });
    }
}
