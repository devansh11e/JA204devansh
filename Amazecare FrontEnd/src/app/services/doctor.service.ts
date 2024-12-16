import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';
import { Doctor } from '../models/Doctor';

@Injectable({
  providedIn: 'root'
})
export class DoctorService {

  baseUrl:string ="http://localhost:8081/api/doctors/";

  constructor(private http:HttpClient) { }


  addDoctor(doctor:Doctor,token:any):Observable<Doctor>
  { const headers = new HttpHeaders({
      Authorization: 'Bearer ' + token,
         });
    return this.http.post<Doctor>(this.baseUrl+"addDoctor",doctor,{headers});}

  updateDoctor(doctor:Doctor,token:any):Observable<Doctor>
  { const headers = new HttpHeaders({
    Authorization: 'Bearer ' + token,
       });
    return this.http.put<Doctor>(this.baseUrl+"updateDoctor/"+doctor.doctorId,doctor,{headers});}


  getAllDoctors(token:any):Observable<Doctor[]>
  {const headers = new HttpHeaders({
    Authorization: 'Bearer ' + token,
       });
    return this.http.get<Doctor[]>(this.baseUrl+"getallDoctor",{headers});}

  getDoctorById(doctorId:number,token:any):Observable<Doctor>
  { const headers = new HttpHeaders({
    Authorization: 'Bearer ' + token,
       });
    return this.http.get<Doctor>(this.baseUrl+`getDoctorbyid/${doctorId}`,{headers}).pipe(
    catchError((error) => {
      console.error('Error fetching Doctor:', error);
      return throwError(() => new Error('Doctor Not Found .Failed to fetch Doctor'));
    })
  );
  }

  getDoctorByName(doctorName:string,token:any):Observable<Doctor[]>
  {const headers = new HttpHeaders({
    Authorization: 'Bearer ' + token,
       });
    return this.http.get<Doctor[]>(this.baseUrl+"getbyDoctorName/"+doctorName,{headers}).pipe(
    catchError((error) => {
      console.error('Error fetching Doctor:', error);
      return throwError(() => new Error('Doctor Not Found .Failed to fetch Doctor'));
    })
  );}
}
