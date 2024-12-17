import { Component, OnInit } from '@angular/core';
import { AuthRequest } from 'src/app/models/AuthRequest';
import { JwtClientService } from 'src/app/services/jwt-client.service';
import { Router } from '@angular/router';
@Component({
  selector: 'app-security',
  templateUrl: './security.component.html',
  styleUrls: ['./security.component.css']
})
export class SecurityComponent implements OnInit {
  response: any;
  token: any;
  authRequest: AuthRequest = new AuthRequest();
  showPassword = false;

  togglePasswordVisibility() {
    this.showPassword = !this.showPassword;
  }
  constructor(private jwtService: JwtClientService, private router: Router) {}

  ngOnInit(): void {
    // This could be used to initialize something if needed
    // this.getAccessToken(this.authRequest);
  }

  // Method to read form data and call getAccessToken
  readFormData(formData: any) {
    this.authRequest.username = formData.form.value.username;
    this.authRequest.password = formData.form.value.password;

    // Get the token after form submission
    this.getAccessToken(this.authRequest);
  }

  // Method to get the access token from the service
  public getAccessToken(authRequest: any) {
    // Calling the service to get the token
    this.jwtService.getGeneratedToken(authRequest).subscribe(
      (genToken) => {
        this.token = genToken;
        console.log('Generated Token:', genToken);
        
        // Once the token is generated, call the method to check the user's role
        this.accessApi(authRequest.username,this.token);
      },
      (error) => {
        console.error('Error generating token:', error),alert('Username or password is incorrect. Please try again.');
      }
    );
  }
   username(username: any, token: any) {
      throw new Error('Method not implemented.');
   }

  // Method to access the API and check user role
  public accessApi(username:string, token: any) {
    // Call the service method to get the user role using the token
    this.jwtService.getUserRole(username,token).subscribe(
      (role) => {  localStorage.setItem('authToken', token);
        console.log('Token saved:', token);
        console.log('User Role:', role);
        
        // Redirect based on the role
        if (role === 'admin') {
          this.router.navigate(['/admin']);
        } else if (role === 'doctor') {
          this.router.navigate(['/doctor']);}
          else if (role === 'patient') {
            this.router.navigate(['/patient']);
        } else {
          console.error('Unauthorized role');
        }
      },
      (error) => {
        console.error('Error fetching user role:', error);
      }
    );
  }
}



