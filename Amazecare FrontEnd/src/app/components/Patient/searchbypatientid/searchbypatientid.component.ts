import { Component } from '@angular/core';
import { Patient } from 'src/app/models/Patient';
import { PatientService } from 'src/app/services/patient.service';

@Component({
  selector: 'app-searchbypatientid',
  templateUrl: './searchbypatientid.component.html',
  styleUrls: ['./searchbypatientid.component.css']
})
export class SearchbypatientidComponent {
  patientList: Patient[] =[]; // Store a single patient object
 

  constructor(private service: PatientService) { }

  // Fetch patient by ID
  getPatientById(formValue: { patientId: number }) {
    const token = localStorage.getItem('authToken'); // Or retrieve token from a service
    if (token) {
    const patientId = formValue.patientId;

    this.service.getPatientById(patientId,token).subscribe(
      (response: Patient) => {
        if (response) {
          this.patientList = [response]; // Store the fetched patient
          
        } 
      },
      (err) => {
        console.error('Error fetching patient by ID:', err);
       alert("No Patient Found with ID:"+patientId);
        
      }
    );
  }
  }
 
}
