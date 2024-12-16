import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, switchMap, throwError } from 'rxjs';
import { MedicalRecord } from '../models/MedicalRecord';

@Injectable({
  providedIn: 'root'
})
export class MedicalrecordService {

  baseUrl:string="http://localhost:8081/api/medicalrecords/";
  baseUrl1:string="http://localhost:8081";
  constructor(private http:HttpClient) { }

  addRecord(medical:MedicalRecord,token:any):Observable<MedicalRecord>
  {  const headers = new HttpHeaders({
    Authorization: 'Bearer ' + token,
       });
    const appointmentUrl:string=this.baseUrl1+`/api/appointments/getappointmentbyid/${medical.appointmentId}`;
     return this.http.get(appointmentUrl,{headers}).pipe(
      catchError(() => {
        throw new Error('Appointment not found');
      }),
      switchMap(()=>this.http.post<MedicalRecord>(this.baseUrl+"addMedicalrecord",medical,{headers})),
      catchError((error)=>{console.error('Error adding Medical Record:',error);
       return throwError(()=>new Error('Failed to add Medical Record'));
      })
     );
    }

  updateRecord(medical:MedicalRecord,token:any):Observable<MedicalRecord>
  {  const headers = new HttpHeaders({
    Authorization: 'Bearer ' + token,
       });
    return this.http.put<MedicalRecord>(this.baseUrl+"updateMedicalRecord/"+medical.recordId,medical,{headers});}

  getAllRecord(token:any):Observable<any>
  {  const headers = new HttpHeaders({
    Authorization: 'Bearer ' + token,
  });
return this.http.get(this.baseUrl+"getallMedicalRecord", { headers });}

  getRecordById(recordId:number,token:any):Observable<MedicalRecord>
  { const headers = new HttpHeaders({
    Authorization: 'Bearer ' + token,
       });
    return this.http.get<MedicalRecord>(this.baseUrl+`getRecordbyid/${recordId}`,{headers}).pipe(
    catchError((error) => {
      console.error('Error fetching Record:', error);
      return throwError(() => new Error('Record Not Found .Failed to fetch Record'));
    })
  );}

  getRecordByDiagnosis(diagnosis:string,token:any):Observable<MedicalRecord[]>
  { const headers = new HttpHeaders({
    Authorization: 'Bearer ' + token,
       });
    return this.http.get<MedicalRecord[]>(this.baseUrl+`getbydiagnosis/${diagnosis}`,{headers}).pipe(
    catchError((error) => {
      console.error('Error fetching Record:', error);
      return throwError(() => new Error('Record Not Found .Failed to fetch Record'));
    })
  );}
}
