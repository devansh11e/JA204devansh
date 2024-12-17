import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import {HttpClientModule} from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AddAppointmentComponent } from './components/Appointment/add-appointment/add-appointment.component';
import { UpdateAppointmentComponent } from './components/Appointment/update-appointment/update-appointment.component';
import { DisplayAppointmentComponent } from './components/Appointment/display-appointment/display-appointment.component';
import { SearchbyappointmentidComponent } from './components/Appointment/searchbyappointmentid/searchbyappointmentid.component';
import { AddDoctorComponent } from './components/Doctor/add-doctor/add-doctor.component';
import { UpdateDoctorComponent } from './components/Doctor/update-doctor/update-doctor.component';
import { DisplayDoctorComponent } from './components/Doctor/display-doctor/display-doctor.component';
import { SearchByIdComponent } from './components/Doctor/search-by-id/search-by-id.component';
import { SearchByNameComponent } from './components/Doctor/search-by-name/search-by-name.component';
import { PatientdashboardComponent } from './components/Dashboard/patientdashboard/patientdashboard.component';
import { DoctordashboardComponent } from './components/Dashboard/doctordashboard/doctordashboard.component';
import { AdmindashboardComponent } from './components/Dashboard/admindashboard/admindashboard.component';
import { AddRecordComponent } from './components/MedicalRecord/add-record/add-record.component';
import { DisplayRecordComponent } from './components/MedicalRecord/display-record/display-record.component';
import { UpdateRecordComponent } from './components/MedicalRecord/update-record/update-record.component';
import { SearchByRecordIdComponent } from './components/MedicalRecord/search-by-record-id/search-by-record-id.component';
import { SearchByDiagnosisComponent } from './components/MedicalRecord/search-by-diagnosis/search-by-diagnosis.component';
import { AddPatientComponent } from './components/Patient/add-patient/add-patient.component';
import { UpdatePatientComponent } from './components/Patient/update-patient/update-patient.component';
import { DisplayPatientComponent } from './components/Patient/display-patient/display-patient.component';
import { SearchbypatientidComponent } from './components/Patient/searchbypatientid/searchbypatientid.component';
import { SearchbypatientnameComponent } from './components/Patient/searchbypatientname/searchbypatientname.component';
import { AddPrescriptionComponent } from './components/Prescription/add-prescription/add-prescription.component';
import { DisplayPrescriptionComponent } from './components/Prescription/display-prescription/display-prescription.component';
import { SearchByPresIdComponent } from './components/Prescription/search-by-pres-id/search-by-pres-id.component';
import { SearchPresByPatientIdComponent } from './components/Prescription/search-pres-by-patient-id/search-pres-by-patient-id.component';
import { RegisterComponent } from './components/register/register.component';
import { SecurityComponent } from './components/security/security.component';
import { AppointmentService } from './services/appointment.service';
import { DoctorService } from './services/doctor.service';
import { MedicalrecordService } from './services/medicalrecord.service';
import { PatientService } from './services/patient.service';
import { PrescriptionService } from './services/prescription.service';
import { JwtClientService } from './services/jwt-client.service';
import { UserService } from './services/user.service';
import { HomeComponent } from './components/Dashboard/home/home.component';
import { ForgotPasswordComponent } from './components/forgot-password/forgot-password.component';

@NgModule({
  declarations: [
    AppComponent,
    AddAppointmentComponent,
    UpdateAppointmentComponent,
    DisplayAppointmentComponent,
    SearchbyappointmentidComponent,
    AddDoctorComponent,
    UpdateDoctorComponent,
    DisplayDoctorComponent,
    SearchByIdComponent,
    SearchByNameComponent,
    PatientdashboardComponent,
    DoctordashboardComponent,
    AdmindashboardComponent,
    AddRecordComponent,
    DisplayRecordComponent,
    UpdateRecordComponent,
    SearchByRecordIdComponent,
    SearchByDiagnosisComponent,
    AddPatientComponent,
    UpdatePatientComponent,
    DisplayPatientComponent,
    SearchbypatientidComponent,
    SearchbypatientnameComponent,
    AddPrescriptionComponent,
    DisplayPrescriptionComponent,
    SearchByPresIdComponent,
    SearchPresByPatientIdComponent,
    RegisterComponent,
    SecurityComponent,
    HomeComponent,
    ForgotPasswordComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [DoctorService,MedicalrecordService,PrescriptionService,AppointmentService,PatientService,JwtClientService,UserService],
  bootstrap: [AppComponent]
})
export class AppModule { }
