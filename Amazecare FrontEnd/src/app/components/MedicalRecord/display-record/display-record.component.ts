import { Component } from '@angular/core';
import { MedicalRecord } from 'src/app/models/MedicalRecord';
import { MedicalrecordService } from 'src/app/services/medicalrecord.service';

@Component({
  selector: 'app-display-record',
  templateUrl: './display-record.component.html',
  styleUrls: ['./display-record.component.css']
})
export class DisplayRecordComponent {
  recList:MedicalRecord[]=[];
  constructor(private service:MedicalrecordService){}
  

  getAllRecord()
  { const token = localStorage.getItem('authToken'); // Or retrieve token from a service
    if (token) {this.service.getAllRecord(token).subscribe(
    (records:MedicalRecord[])=>{ this.recList=records;
      if(this.recList.length===0)
      { console.log("List is Empty"),alert("List is Empty");}
      else{
      console.log(this.recList);}},
      (err)=>{console.log(err);}
  );}
}
}
