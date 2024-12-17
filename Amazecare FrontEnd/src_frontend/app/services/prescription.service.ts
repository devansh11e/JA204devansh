import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, switchMap, throwError } from 'rxjs';
import { Prescription } from '../models/Prescription';

@Injectable({
  providedIn: 'root'
})
export class PrescriptionService {

  baseUrl:string="http://localhost:8081";


  constructor(private http:HttpClient) { }


  addPrescription(pre:Prescription,token:any):Observable<Prescription>{
    const headers = new HttpHeaders({
          Authorization: 'Bearer ' + token,
        });
    const patientUrl:string=this.baseUrl+`/api/patients/get-Patient-by-id/${pre.patientId}`;
    const doctorUrl:string=this.baseUrl+`/api/doctors/get-Doctor-by-id/${pre.doctorId}`;
    const prescriptionUrl=this.baseUrl+"/api/prescribemedications/add-Prescription";
   return this.http.get(patientUrl,{headers}).pipe(
    catchError(() => {
      throw new Error('Patient not found');
    }),
      switchMap(()=> this.http.get(doctorUrl,{headers}).pipe(
        catchError(() => {
          throw new Error('Doctor not found');})
      )),
      switchMap(()=>this.http.post<Prescription>(prescriptionUrl,pre,{headers})),
     catchError((error)=>{console.error('Error adding prescription:',error);
      return throwError(()=>new Error('Failed to add prescription'));
     })
    );
  }
  

  getAllPrescriptions(token:any):Observable<Prescription[]>
  { const headers = new HttpHeaders({
    Authorization: 'Bearer ' + token,
  });
    return this.http.get<Prescription[]>(this.baseUrl+"/api/prescribemedications/get-all-prescriptions",{headers});}

  getPrescriptionById(prescriptionId:number,token:any):Observable<Prescription>
  {const headers = new HttpHeaders({
    Authorization: 'Bearer ' + token,
  });
    return this.http.get<Prescription>(this.baseUrl+"/api/prescribemedications/get-Prescription-by-id/"+prescriptionId,{headers}).pipe(
    catchError((error) => {
      console.error('Error fetching prescriptions:', error);
      return throwError(() => new Error('.Failed to fetch prescriptions with id '+ prescriptionId));
    })
  );
  }

  getPrescriptionByPatientId(patientId: number,token:any): Observable<Prescription[]> {
    const headers = new HttpHeaders({
      Authorization: 'Bearer ' + token,
    });
    const url = this.baseUrl+`/api/prescribemedications/get-Prescription-by-patientid/${patientId}`;
    return this.http.get<Prescription[]>(url,{headers}).pipe(
      catchError((error) => {
        console.error('Error fetching prescriptions:', error);
        return throwError(() => new Error('Patient Not Found .Failed to fetch prescriptions'));
      })
    );
  }
}
