import { Component } from '@angular/core';
import { Doctor } from 'src/app/models/Doctor';
import { DoctorService } from 'src/app/services/doctor.service';

@Component({
  selector: 'app-add-doctor',
  templateUrl: './add-doctor.component.html',
  styleUrls: ['./add-doctor.component.css']
})
export class AddDoctorComponent {
  constructor(private service:DoctorService){}
  
  addDoctor(doctor:Doctor)
  { const token = localStorage.getItem('authToken'); // Or retrieve token from a service
    if (token) {
    this.service.addDoctor(doctor,token).subscribe(
    (response)=>{console.log(response),alert("DoctorID:"+response.doctorId+" added successfully")},
    (err)=>{console.log(err)}
  );
}}
}
