import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-forgot-password',
  templateUrl: './forgot-password.component.html',
  styleUrls: ['./forgot-password.component.css']
})
export class ForgotPasswordComponent {
  username: string = '';
  password: string = '';
  showPassword = false;

  togglePasswordVisibility() {
    this.showPassword = !this.showPassword;
  }
  constructor(private router:Router,private service:UserService){}
  onSubmit(form:NgForm)
  { const { username, password } = form.value; 
    this.service.forgotPassword(username,password).subscribe(
    (response)=>{if(response){
      alert("Password Reset Successfully"),
      this.router.navigate(['/login-user']);
    }
  else{ alert('Username does not exist.')}},
  (err)=>{console.log(err);}
  )}
  

}
