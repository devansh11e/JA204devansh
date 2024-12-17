import { Component } from '@angular/core';
import { Prescription } from 'src/app/models/Prescription';
import { PrescriptionService } from 'src/app/services/prescription.service';

@Component({
  selector: 'app-search-pres-by-patient-id',
  templateUrl: './search-pres-by-patient-id.component.html',
  styleUrls: ['./search-pres-by-patient-id.component.css']
})
export class SearchPresByPatientIdComponent {
  preList:Prescription[]=[];
  constructor(private service:PrescriptionService){}

  getPrescriptionByPatientId(data: any) {
    const token = localStorage.getItem('authToken'); // Or retrieve token from a service
    if (token) {
    const patientID = data.patientId; // Extract the ID from the input
    console.log(patientID);
  
    this.service.getPrescriptionByPatientId(patientID,token).subscribe(
      (response: Prescription[]) => {
        if (response && response.length > 0) {
          this.preList = response; // Store the fetched prescriptions
          console.log(this.preList);
        }
      },
      (err: any) => {
        console.error('Error fetching prescriptions by patient ID:', err);
        
        alert("No prescription found with PatientId  "+patientID);
      }
    );
  }}

}
