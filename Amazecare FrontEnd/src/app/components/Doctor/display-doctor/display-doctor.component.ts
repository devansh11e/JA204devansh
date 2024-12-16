import { Component } from '@angular/core';
import { Doctor } from 'src/app/models/Doctor';
import { DoctorService } from 'src/app/services/doctor.service';
import { JwtClientService } from 'src/app/services/jwt-client.service';

@Component({
  selector: 'app-display-doctor',
  templateUrl: './display-doctor.component.html',
  styleUrls: ['./display-doctor.component.css']
})
export class DisplayDoctorComponent {
  docList: Doctor[]=[];
  userRole: string = '';
  constructor(private service:DoctorService){}
  

  getAllDoctors()
  { const token = localStorage.getItem('authToken'); // Or retrieve token from a service
    if (token) {
    this.service.getAllDoctors(token).subscribe(
    (doctors: Doctor[]) => {
      this.docList = doctors;
      if (this.docList.length === 0) {
        console.log("List is empty");
      } else {
        console.log(this.docList);
      }
    },
    (err: any) => {
      console.error("Error retrieving doctor list:", err);
    }
  );
}}
}
