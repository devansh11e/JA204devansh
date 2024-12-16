import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';
import { Patient } from '../models/Patient';

@Injectable({
  providedIn: 'root'
})
export class PatientService {

  baseUrl: string = "http://localhost:8081/api/patients/";

  constructor(private http: HttpClient) { }

  addPatient(patient: Patient,token:any): Observable<Patient> {
    const headers = new HttpHeaders({
      Authorization: 'Bearer ' + token,
    });
    return this.http.post<Patient>(this.baseUrl+"registerpatient", patient,{headers});
  }

  updatePatient(patient: Patient,token:any): Observable<Patient> {
    const headers = new HttpHeaders({
      Authorization: 'Bearer ' + token,
    });
    return this.http.put<Patient>(this.baseUrl+"updatepatient/"+patient.patientId, patient,{headers});
  }


  getAllPatients(token:any): Observable<any> {

    const headers = new HttpHeaders({
        Authorization: 'Bearer ' + token,
      });
    return this.http.get(this.baseUrl+"getAllPatients", { headers });
  }

  getPatientById(patientId: number,token:any): Observable<Patient> {
    const headers = new HttpHeaders({
      Authorization: 'Bearer ' + token,
    });
    return this.http.get<Patient>(this.baseUrl+`getPatientbyid/${patientId}`,{headers}).pipe(
      catchError((error) => {
        console.error('Error fetching Patient by ID:', error);
        return throwError(() => new Error('Patient Not Found. Failed to fetch Patient by ID'));
      })
    );
  }

  getPatientByName(patientName: string,token:any): Observable<Patient[]> {
    const headers = new HttpHeaders({
      Authorization: 'Bearer ' + token,
    });
    return this.http.get<Patient[]>(this.baseUrl+"getPatientByName/"+patientName,{headers}).pipe(
      catchError((error) => {
        console.error('Error fetching Patient by Name:', error);
        return throwError(() => new Error('Patient Not Found. Failed to fetch Patient by Name'));
      })
    );
  }
}
