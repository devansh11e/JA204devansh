import { Component } from '@angular/core';
import { Prescription } from 'src/app/models/Prescription';
import { PrescriptionService } from 'src/app/services/prescription.service';

@Component({
  selector: 'app-display-prescription',
  templateUrl: './display-prescription.component.html',
  styleUrls: ['./display-prescription.component.css']
})
export class DisplayPrescriptionComponent {
  preList:Prescription[]=[];
  constructor(private service:PrescriptionService){}

  getAllPrescriptions()
  {const token = localStorage.getItem('authToken'); // Or retrieve token from a service
    if (token) {
    this.service.getAllPrescriptions(token).subscribe(
    (pres:Prescription[])=>{this.preList=pres;
      console.log(this.preList);},
      (err)=>{console.log(err)}
  );}}
}
