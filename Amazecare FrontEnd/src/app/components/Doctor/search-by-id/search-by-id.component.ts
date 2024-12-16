import { Component } from '@angular/core';
import { Doctor } from 'src/app/models/Doctor';
import { DoctorService } from 'src/app/services/doctor.service';

@Component({
  selector: 'app-search-by-id',
  templateUrl: './search-by-id.component.html',
  styleUrls: ['./search-by-id.component.css']
})
export class SearchByIdComponent {
  docList: Doctor[]=[];
  
  constructor(private service:DoctorService){}

  getDoctorById(formValue: { doctorId: number }) {
    const token = localStorage.getItem('authToken'); // Or retrieve token from a service
    if (token) {
    const doctorId = formValue.doctorId; // Extract the ID from the input
    console.log(doctorId);
  
    this.service.getDoctorById(doctorId,token).subscribe(
      (response: Doctor) => {
        if (response) {
          this.docList = [response]; // Store the fetched doctor
          console.log(this.docList);
        } 
      },
      (err: any) => {
        console.error('Error fetching doctor by ID:', err);
       alert("No doctor found with ID  "+doctorId);
      }
    );
  }}

}
