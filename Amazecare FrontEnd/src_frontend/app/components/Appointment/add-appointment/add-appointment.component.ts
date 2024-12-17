import { Component } from '@angular/core';
import { Appointment } from 'src/app/models/Appointment';
import { AppointmentService } from 'src/app/services/appointment.service';

@Component({
  selector: 'app-add-appointment',
  templateUrl: './add-appointment.component.html',
  styleUrls: ['./add-appointment.component.css']
})
export class AddAppointmentComponent {
  constructor(private service:AppointmentService){}

  addAppointment(app:Appointment):void
  { const token = localStorage.getItem('authToken'); // Or retrieve token from a service
    if (token) {
     this.service.addAppointment(app,token).subscribe(
    (response)=>{console.log(response),alert("AppointmentID:"+response.appointmentId+" added successfully");},
    (err)=>{console.log(err);
      if(app.timeSlot === 'conflict') {
        alert('Appointment is already scheduled for this time slot. Please choose a different time.');
      } 
      else{alert("Failed to schedule appointment. Check DoctorID and PatientID.")}
    }
  )}}
}
