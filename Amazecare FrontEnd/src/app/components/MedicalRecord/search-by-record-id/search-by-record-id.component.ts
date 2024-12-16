import { Component } from '@angular/core';
import { MedicalRecord } from 'src/app/models/MedicalRecord';
import { MedicalrecordService } from 'src/app/services/medicalrecord.service';

@Component({
  selector: 'app-search-by-record-id',
  templateUrl: './search-by-record-id.component.html',
  styleUrls: ['./search-by-record-id.component.css']
})
export class SearchByRecordIdComponent {
  recList:MedicalRecord[]=[];
  constructor(private service:MedicalrecordService){}

  getRecordById(formValue: { recordId: number })
{ const token = localStorage.getItem('authToken'); // Or retrieve token from a service
  if (token) {
    const recordId = formValue.recordId;
  this.service.getRecordById(recordId,token).subscribe(
  (records:MedicalRecord)=>{if(records){this.recList=[records]; console.log(this.recList);}
  else{console.log('No records found');
    alert('No records found');
  }},
  (err)=>{console.log(err),alert("No Record found with ID:"+formValue.recordId);}
);}}
}
