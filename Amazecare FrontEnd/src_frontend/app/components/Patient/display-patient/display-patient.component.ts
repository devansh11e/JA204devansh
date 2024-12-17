import { Component } from '@angular/core';
import { Subscription } from 'rxjs';
import { Patient } from 'src/app/models/Patient';
import { PatientService } from 'src/app/services/patient.service';

@Component({
  selector: 'app-display-patient',
  templateUrl: './display-patient.component.html',
  styleUrls: ['./display-patient.component.css']
})
export class DisplayPatientComponent {
  patientList:Patient[]=[];
  constructor(private service:PatientService){}
  
  getAllPatients(): void {
    const token = localStorage.getItem('authToken'); // Or retrieve token from a service
    if (token) {
      this.service.getAllPatients(token).subscribe(
        (patient: Patient[]) => {
          this.patientList = patient;
          if(this.patientList.length===0)
            { console.log("List is Empty"),alert("List is Empty");}
            else{
            console.log(this.patientList);}
        },
        (err) => {
          console.log(err);
        }
      );
    } 
  }
  
  
}
