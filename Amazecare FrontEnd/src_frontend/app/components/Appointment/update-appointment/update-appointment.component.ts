import { Component } from '@angular/core';
import { Appointment } from 'src/app/models/Appointment';
import { AppointmentService } from 'src/app/services/appointment.service';

@Component({
  selector: 'app-update-appointment',
  templateUrl: './update-appointment.component.html',
  styleUrls: ['./update-appointment.component.css']
})
export class UpdateAppointmentComponent {
  constructor(private service: AppointmentService) { }

  updateAppointment(appointment: Appointment) {
   
    const token = localStorage.getItem('authToken'); // Or retrieve token from a service
    if (token) {
    this.service.updateAppointment(appointment,token).subscribe(
      (response) => {
        console.log(response);
        alert("Appointment updated successfully");
      },
      (err) => {
        console.error(err);
        if(appointment.status==="Cancelled"){alert('Appointment is already cancelled, Failed to update.');}
          else{alert('Failed to update.DoctorID,PatientID or AppointmentID is invalid.');}}
       
      
    );
  }}
}
