import { Component } from '@angular/core';
import { MedicalRecord } from 'src/app/models/MedicalRecord';
import { MedicalrecordService } from 'src/app/services/medicalrecord.service';

@Component({
  selector: 'app-search-by-diagnosis',
  templateUrl: './search-by-diagnosis.component.html',
  styleUrls: ['./search-by-diagnosis.component.css']
})
export class SearchByDiagnosisComponent {
  recList:MedicalRecord[]=[];
  constructor(private service:MedicalrecordService){}


  getRecordByDiagnosis(data: any)
{ const token = localStorage.getItem('authToken'); // Or retrieve token from a service
  if (token) {
  this.service.getRecordByDiagnosis(data.diagnosis,token).subscribe(
  (records:MedicalRecord[])=>{this.recList=records;
  console.log(this.recList);},
  (err)=>{console.log(err),alert("Failed to retrieve data")}
);}}
}
