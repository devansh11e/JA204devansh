import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { UserInfo } from 'src/app/models/UserInfo';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  showPassword = false;

  togglePasswordVisibility() {
    this.showPassword = !this.showPassword;
  }
  constructor( private router: Router,private service:UserService)
  {}
  
  addNewUser(user: UserInfo) {
    console.log(user.name);
    this.service.checkUsernameExists(user.name).subscribe(
      (exists: boolean) => {
        if (exists) {
          alert('Username already exists. Please choose a different username.');
        } else {
          this.service.addNewUser(user).subscribe(
            (response: any) => {
              console.log(response);
              alert('Registered successfully .');
              this.router.navigate(['/login-user']);
            },
            (err) => {
              console.error(err);
            }
          );
        }
      },
      (err) => {
        console.error('Error checking username:', err);
      }
    );
  }
}
