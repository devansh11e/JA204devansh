import { Component } from '@angular/core';
import { Doctor } from 'src/app/models/Doctor';
import { DoctorService } from 'src/app/services/doctor.service';

@Component({
  selector: 'app-search-by-name',
  templateUrl: './search-by-name.component.html',
  styleUrls: ['./search-by-name.component.css']
})
export class SearchByNameComponent {
  docList: Doctor[]=[];
  constructor(private service:DoctorService){}

  getDoctorByName(data: any) {
    const token = localStorage.getItem('authToken'); // Or retrieve token from a service
    if (token) {
    console.log(data.doctorName);
    this.service.getDoctorByName(data.doctorName,token).subscribe(
      (response: Doctor[]) => {
        if (response && response.length > 0) {
          this.docList = response; // Update the list with the response
          console.log(this.docList);
        } else {
          this.docList = []; // Clear the list
          
        }
      },
      (err: any) => {
        console.log('Error occurred:', err);
        alert( "No records found with the name  "+data.doctorName); // Set the error message
      }
    );
  }}

}
