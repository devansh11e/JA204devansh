import { Component } from '@angular/core';
import { Patient } from 'src/app/models/Patient';
import { PatientService } from 'src/app/services/patient.service';

@Component({
  selector: 'app-searchbypatientname',
  templateUrl: './searchbypatientname.component.html',
  styleUrls: ['./searchbypatientname.component.css']
})
export class SearchbypatientnameComponent {
  patientList: Patient[]=[]; // Store a single patient object
  

  constructor(private service: PatientService) { }

  // Fetch patient by name
  getPatientByName(data: any) {
    const token = localStorage.getItem('authToken'); // Or retrieve token from a service
    if (token) {
    console.log(data.patientName);
    this.service.getPatientByName(data.patientName,token).subscribe(
      (response: Patient[]) => {
        if (response && response.length > 0) {
          this.patientList = response; // Update the list with the response
          console.log(this.patientList);
        } 
      },
      (err: any) => {
        console.log('Error occurred:', err);
        alert("No Patient found with Name:"+data.patientName);
      }
    );
  }}

}
