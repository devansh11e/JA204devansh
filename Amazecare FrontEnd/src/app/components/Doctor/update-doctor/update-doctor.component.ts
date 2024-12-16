import { Component } from '@angular/core';
import { Doctor } from 'src/app/models/Doctor';
import { DoctorService } from 'src/app/services/doctor.service';

@Component({
  selector: 'app-update-doctor',
  templateUrl: './update-doctor.component.html',
  styleUrls: ['./update-doctor.component.css']
})
export class UpdateDoctorComponent {
  constructor(private service:DoctorService){}

  updateDoctor(doctor:Doctor)
  { const token = localStorage.getItem('authToken'); // Or retrieve token from a service
    if (token) {
    this.service.updateDoctor(doctor,token).subscribe(
    (response)=>{console.log(response),alert("Record updated successfully")},
    (err)=>{console.log(err),alert("Failed to update doctor.Check details Properly")}
  );}}
}
