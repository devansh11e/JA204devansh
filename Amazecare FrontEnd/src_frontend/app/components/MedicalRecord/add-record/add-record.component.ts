import { Component } from '@angular/core';
import { MedicalRecord } from 'src/app/models/MedicalRecord';
import { MedicalrecordService } from 'src/app/services/medicalrecord.service';

@Component({
  selector: 'app-add-record',
  templateUrl: './add-record.component.html',
  styleUrls: ['./add-record.component.css']
})
export class AddRecordComponent {
  constructor(private service:MedicalrecordService){}

  addRecord(record:MedicalRecord)
  { const token = localStorage.getItem('authToken'); // Or retrieve token from a service
    if (token) {
    this.service.addRecord(record,token).subscribe(
    (response)=>{
      if (response && response.recordId) {
        alert("RecordID "+response.recordId+" added successfully");
      }
        
      console.log(response);},
    (err)=>{console.log(err);alert('Failed to add record. Appointment ID might be invalid or The appointment is Cancelled.');}
  )}}
}
