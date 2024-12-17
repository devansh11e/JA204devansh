import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AppComponent } from './app.component';
import { DoctordashboardComponent } from './components/Dashboard/doctordashboard/doctordashboard.component';
import { AdmindashboardComponent } from './components/Dashboard/admindashboard/admindashboard.component';
import { AddDoctorComponent } from './components/Doctor/add-doctor/add-doctor.component';
import { AddAppointmentComponent } from './components/Appointment/add-appointment/add-appointment.component';
import { DisplayAppointmentComponent } from './components/Appointment/display-appointment/display-appointment.component';
import { SearchbyappointmentidComponent } from './components/Appointment/searchbyappointmentid/searchbyappointmentid.component';
import { UpdateAppointmentComponent } from './components/Appointment/update-appointment/update-appointment.component';
import { PatientdashboardComponent } from './components/Dashboard/patientdashboard/patientdashboard.component';
import { DisplayDoctorComponent } from './components/Doctor/display-doctor/display-doctor.component';
import { SearchByIdComponent } from './components/Doctor/search-by-id/search-by-id.component';
import { SearchByNameComponent } from './components/Doctor/search-by-name/search-by-name.component';
import { UpdateDoctorComponent } from './components/Doctor/update-doctor/update-doctor.component';
import { AddRecordComponent } from './components/MedicalRecord/add-record/add-record.component';
import { DisplayRecordComponent } from './components/MedicalRecord/display-record/display-record.component';
import { SearchByDiagnosisComponent } from './components/MedicalRecord/search-by-diagnosis/search-by-diagnosis.component';
import { SearchByRecordIdComponent } from './components/MedicalRecord/search-by-record-id/search-by-record-id.component';
import { UpdateRecordComponent } from './components/MedicalRecord/update-record/update-record.component';
import { AddPatientComponent } from './components/Patient/add-patient/add-patient.component';
import { DisplayPatientComponent } from './components/Patient/display-patient/display-patient.component';
import { SearchbypatientidComponent } from './components/Patient/searchbypatientid/searchbypatientid.component';
import { SearchbypatientnameComponent } from './components/Patient/searchbypatientname/searchbypatientname.component';
import { UpdatePatientComponent } from './components/Patient/update-patient/update-patient.component';
import { AddPrescriptionComponent } from './components/Prescription/add-prescription/add-prescription.component';
import { DisplayPrescriptionComponent } from './components/Prescription/display-prescription/display-prescription.component';
import { SearchByPresIdComponent } from './components/Prescription/search-by-pres-id/search-by-pres-id.component';
import { SearchPresByPatientIdComponent } from './components/Prescription/search-pres-by-patient-id/search-pres-by-patient-id.component';
import { RegisterComponent } from './components/register/register.component';
import { SecurityComponent } from './components/security/security.component';
import { HomeComponent } from './components/Dashboard/home/home.component';
import { ForgotPasswordComponent } from './components/forgot-password/forgot-password.component';

const routes: Routes = [
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  {path:'home',component:HomeComponent},
  {path:'doctor',component:DoctordashboardComponent},
  {path:'admin',component:AdmindashboardComponent},
  {path:'add-doctor',component:AddDoctorComponent},
  {path:'update-doctor',component:UpdateDoctorComponent},
  {path:'display-doctor',component:DisplayDoctorComponent},
  {path:'search-doctorId',component:SearchByIdComponent},
  {path:'search-doctorName',component:SearchByNameComponent},
  {path:'add-record',component:AddRecordComponent},
  {path:'update-record',component:UpdateRecordComponent},
  {path:'display-record',component:DisplayRecordComponent},
  {path:'search-diagnosis',component:SearchByDiagnosisComponent},
  {path:'search-recordId',component:SearchByRecordIdComponent},
  {path:'add-prescription',component:AddPrescriptionComponent},
  {path:'display-prescription',component:DisplayPrescriptionComponent},
  {path:'search-presId',component:SearchByPresIdComponent},
  {path:'searchpres-patientId',component:SearchPresByPatientIdComponent},
  {path:'search-patientId',component:SearchbypatientidComponent},
  {path:'add-patient',component:AddPatientComponent},
  {path:'add-appointment',component:AddAppointmentComponent},
  {path:'patient',component:PatientdashboardComponent},
  {path:'update-appointment',component:UpdateAppointmentComponent},
  {path:'search-appointmentId',component:SearchbyappointmentidComponent},
  {path:'display-appointment',component:DisplayAppointmentComponent},
  {path:'display-patient',component:DisplayPatientComponent},
  {path:'search-patientName',component:SearchbypatientnameComponent},
  {path:'update-patient',component:UpdatePatientComponent},
  {path:'register-user',component:RegisterComponent},
  {path:'login-user',component:SecurityComponent},
  {path:'reset-password',component:ForgotPasswordComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
