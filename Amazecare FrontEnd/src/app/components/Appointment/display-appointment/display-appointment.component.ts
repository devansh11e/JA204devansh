import { Component } from '@angular/core';
import { Appointment } from 'src/app/models/Appointment';
import { AppointmentService } from 'src/app/services/appointment.service';

@Component({
  selector: 'app-display-appointment',
  templateUrl: './display-appointment.component.html',
  styleUrls: ['./display-appointment.component.css']
})
export class DisplayAppointmentComponent {
  appointmentList: Appointment[] = [];
  errorMessage: string = '';

  constructor(private service: AppointmentService) {}

  getAllAppointments() {
    const token = localStorage.getItem('authToken'); // Or retrieve token from a service
    if (token) {
    this.service.getAllAppointments(token).subscribe(
      (response:Appointment[]) => {
        this.appointmentList = response;
        console.log("Appointments fetched successfully:", response);
      },
      (err) => {
        console.error("Error fetching appointments:", err);
        this.errorMessage = "Failed to fetch appointments.";
      }
    );
  }}

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
