import { Component } from '@angular/core';
import { MedicalRecord } from 'src/app/models/MedicalRecord';
import { MedicalrecordService } from 'src/app/services/medicalrecord.service';

@Component({
  selector: 'app-update-record',
  templateUrl: './update-record.component.html',
  styleUrls: ['./update-record.component.css']
})
export class UpdateRecordComponent {
  constructor(private service:MedicalrecordService){}

  updateRecord(record:MedicalRecord)
  { const token = localStorage.getItem('authToken'); // Or retrieve token from a service
    if (token) {
    this.service.updateRecord(record,token).subscribe(
    (response)=>{console.log(response),alert(" Record updated successfully")},
    (err)=>{console.log(err),alert("Failed to update data.Either ID are invalid or Appointment is cancelled.")}
  );}}
}
