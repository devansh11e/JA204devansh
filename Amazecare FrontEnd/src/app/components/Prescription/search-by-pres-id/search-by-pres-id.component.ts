import { Component } from '@angular/core';
import { Prescription } from 'src/app/models/Prescription';
import { PrescriptionService } from 'src/app/services/prescription.service';

@Component({
  selector: 'app-search-by-pres-id',
  templateUrl: './search-by-pres-id.component.html',
  styleUrls: ['./search-by-pres-id.component.css']
})
export class SearchByPresIdComponent {
  preList:Prescription[]=[];
  constructor(private service:PrescriptionService){}

  getPrescriptionById(formValue: { prescriptionId: number }) {
    const token = localStorage.getItem('authToken'); // Or retrieve token from a service
    if (token) {
    const prescriptionId = formValue.prescriptionId; // Extract the ID from the input
    console.log(prescriptionId);
  
    this.service.getPrescriptionById(prescriptionId,token).subscribe(
      (response: Prescription) => {
        if (response) {
          this.preList = [response]; // Store the fetched prescription
          console.log(this.preList);
        } 
      },
      (err: any) => {
        console.error('Error fetching prescription by ID:', err);
        alert( 'No Prescription found with the provided ID:'+formValue.prescriptionId);
      }
    );
  }}
}
