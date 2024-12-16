import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, switchMap, tap, throwError } from 'rxjs';
import { Appointment } from '../models/Appointment';

@Injectable({
  providedIn: 'root'
})
export class AppointmentService {

  baseUrl: string = "http://localhost:8081/api/appointments";

  constructor(private http: HttpClient) { }

  addAppointment(app: Appointment, token: string): Observable<Appointment> {
    const headers = new HttpHeaders({
      Authorization: 'Bearer ' + token,
      'Content-Type': 'application/json',
    });
  
    const patientUrl: string = `http://localhost:8081/api/patients/getPatientbyid/${app.patientId}`;
    const doctorUrl: string = `http://localhost:8081/api/doctors/getDoctorbyid/${app.doctorId}`;
    const appointmentUrl: string = this.baseUrl+`/scheduleappointment`;
    const doctorAppointmentsUrl: string = this.baseUrl+`/byDoctor/${app.doctorId}`;
  
   // Helper method to check for conflicts
  const checkForConflicts = (existingAppointments: Appointment[] | null | undefined): void => {
    if (!existingAppointments || existingAppointments.length === 0) {
      // No existing appointments, no conflicts
      return;
    }
    const conflict = existingAppointments.some(
      (existingAppointment) =>
        existingAppointment.appointmentDate === app.appointmentDate &&
        existingAppointment.timeSlot === app.timeSlot &&
        existingAppointment.status !== 'Cancelled'
    );
    if (conflict) {
      app.timeSlot="conflict";
      throw new Error('Appointment is already scheduled for this slot');
    }
  };
  
    // Validate patient and doctor, then check for conflicts, and finally create the appointment
    return this.http.get(patientUrl, { headers }).pipe(
      catchError(() => throwError(() => new Error('Patient not found'))),
      switchMap(() =>
        this.http.get(doctorUrl, { headers }).pipe(
          catchError(() => throwError(() => new Error('Doctor not found')))
        )
      ),
      switchMap(() =>
        this.http.get<Appointment[]>(doctorAppointmentsUrl, { headers }).pipe(
          tap(checkForConflicts), // Check for conflicts
          switchMap(() => this.http.post<Appointment>(appointmentUrl, app, { headers })) // Create appointment
        )
      ),
      catchError((error) => {
        console.error('Error adding appointment:', error);
        return throwError(() => new Error('Failed to add appointment'));
      })
    );
  }
  
  

  updateAppointment(appointment: Appointment, token: string): Observable<Appointment> {
    const headers = new HttpHeaders({
      Authorization: 'Bearer ' + token,
      'Content-Type': 'application/json',
    });
  
    const url = this.baseUrl+`/updateappointment/${appointment.appointmentId}`;
  
    return this.http.put<Appointment>(url, appointment, { headers }).pipe(
      catchError((error) => {
        console.error('Error updating appointment:', error);
        return throwError(() => new Error('Failed to update appointment'));
      })
    );
  }

  cancelAppointment(appointmentId: number,token:any): Observable<any> {
    const headers = new HttpHeaders({
      Authorization: 'Bearer ' + token,
      'Content-Type': 'application/json',
    });
    const body = { status: 'Cancelled' };  // Update status to 'Cancelled'
  
    return this.http.put(this.baseUrl+"/cancelappointment/"+appointmentId, body,{headers});  // Use PUT to update the appointment status
  }

  getAllAppointments(token:any): Observable<Appointment[]> {
    const headers = new HttpHeaders({
      Authorization: 'Bearer ' + token,
      'Content-Type': 'application/json',
    });
    return this.http.get<Appointment[]>(this.baseUrl+"/getallappointments",{headers}).pipe(
      catchError((error) => {
        console.error('Error fetching appointments:', error);
        return throwError(() => new Error('Failed to fetch appointments'));
      })
    );
  }

  getAppointmentById(appointmentId: number,token:any): Observable<Appointment> {
    const headers = new HttpHeaders({
      Authorization: 'Bearer ' + token,
      'Content-Type': 'application/json',
    });
    return this.http.get<Appointment>(this.baseUrl+`/getappointmentbyid/${appointmentId}`,{headers}).pipe(
      catchError((error) => {
        console.error('Error fetching appointment by ID:', error);
        return throwError(() => new Error('Appointment not found'));
      })
    );
  }
}
