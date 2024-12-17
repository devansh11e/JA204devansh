import { Component } from '@angular/core';
import { Patient } from 'src/app/models/Patient';
import { PatientService } from 'src/app/services/patient.service';

@Component({
  selector: 'app-update-patient',
  templateUrl: './update-patient.component.html',
  styleUrls: ['./update-patient.component.css']
})
export class UpdatePatientComponent {
  constructor(private service: PatientService) { }

  updatePatient(patient: Patient) {
    const token = localStorage.getItem('authToken'); // Or retrieve token from a service
    if (token) {
      this.service.updatePatient(patient, token).subscribe(
        (response) => {
          console.log('Success Response:', response);
          alert("Record updated successfully");
        },
        (err) => {
          console.error('Error Response:', err);
          alert("Failed to update patient. No patient found with Id  "+patient.patientId);
        }
      );
    } else {
      alert("Authorization token not found. Please log in again.");
    }
  }
  
}
