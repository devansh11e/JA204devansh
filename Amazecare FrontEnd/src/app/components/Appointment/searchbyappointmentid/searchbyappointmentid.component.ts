import { Component } from '@angular/core';
import { Appointment } from 'src/app/models/Appointment';
import { AppointmentService } from 'src/app/services/appointment.service';

@Component({
  selector: 'app-searchbyappointmentid',
  templateUrl: './searchbyappointmentid.component.html',
  styleUrls: ['./searchbyappointmentid.component.css']
})
export class SearchbyappointmentidComponent {
  appointment: Appointment | null = null; // Use a single appointment object

  constructor(private service: AppointmentService) { }

  // Fetch appointment by ID
  getAppointmentById(formValue: { appointmentId: number }) {
    const token = localStorage.getItem('authToken'); // Or retrieve token from a service
    if (token) {
    const appointmentId = formValue.appointmentId;

    this.service.getAppointmentById(appointmentId,token).subscribe(
      (response: Appointment) => {
        if (response) {
          this.appointment = response; // Store the fetched appointment
          
        } 
      },
      (err) => {
        console.error('Error fetching appointment by ID:', err);
       alert("No Appointment found with ID:"+appointmentId);
      }
    );
  }}

  // Delete appointment by ID
  deleteByAppointmentId(appointmentId: number) {
    const token = localStorage.getItem('authToken'); // Retrieve token from localStorage
    if (token) {
      this.service.getAppointmentById(appointmentId, token).subscribe(
        (appointment: any) => {
          if (appointment.status === 'Cancelled') {
            alert('Appointment is already cancelled.');
          } else {
            this.service.cancelAppointment(appointmentId, token).subscribe(
              () => {
                this.appointment = null; // Clear appointment after deletion
                alert('Appointment cancelled successfully.');
              },
              (err) => {
                console.error('Error cancelling appointment:', err);
                alert('Failed to cancel the appointment. Please try again.');
              }
            );
          }
        },
        (err) => {
          console.error('Error fetching appointment details:', err);
          alert('Failed to retrieve appointment details. Please try again.');
        }
      );
    } else {
      alert('Authorization token not found. Please log in again.');
    }
  }
  
}
