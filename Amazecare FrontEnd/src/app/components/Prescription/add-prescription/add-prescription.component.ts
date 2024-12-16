import { Component } from '@angular/core';
import { Prescription } from 'src/app/models/Prescription';
import { PrescriptionService } from 'src/app/services/prescription.service';

@Component({
  selector: 'app-add-prescription',
  templateUrl: './add-prescription.component.html',
  styleUrls: ['./add-prescription.component.css']
})
export class AddPrescriptionComponent {
  constructor(private service:PrescriptionService){}
   

  addPrescription(pre:Prescription)
  {const token = localStorage.getItem('authToken'); // Or retrieve token from a service
    if (token) {
    this.service.addPrescription(pre,token).subscribe(
    (response)=>{console.log(response),alert("Prescription ID:"+response.prescriptionId+" added successfully")},
    (err)=>{console.log(err),alert("Failed to update data.Check Details Properly.")}
  );}}
}
