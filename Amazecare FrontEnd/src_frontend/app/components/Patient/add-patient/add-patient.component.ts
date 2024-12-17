import { Component } from '@angular/core';
import { Patient } from 'src/app/models/Patient';
import { PatientService } from 'src/app/services/patient.service';

@Component({
  selector: 'app-add-patient',
  templateUrl: './add-patient.component.html',
  styleUrls: ['./add-patient.component.css']
})
export class AddPatientComponent {
  constructor(private service:PatientService){}

  addPatient(pat:Patient):void
  { const token = localStorage.getItem('authToken'); // Or retrieve token from a service
    if (token) {this.service.addPatient(pat,token).subscribe(
    (response)=>{console.log(response),alert("PatientID:"+response.patientId+" patient added successfully.")},
    (err: any)=>{console.log(err)}
  )}}
}
