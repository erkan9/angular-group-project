import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { UserService } from 'src/app/shared/services/user.service';
import { User } from '../../models/user/user';
import { OrganisationService } from '../../services/organisation.service';

@Component({
  selector: 'app-user-login',
  templateUrl: './user-login.component.html',
  styleUrls: ['./user-login.component.css']
})
export class UserLoginComponent implements OnInit {

  logInAsOrg:boolean = false;
  formGroup:FormGroup;

  constructor(private userService: UserService, private router: Router, private formBuilder:FormBuilder, private organizationService:OrganisationService) {
    this.formGroup = formBuilder.group({
      isOrg: false,
      username: "",
      password: ""
    });
  }

  ngOnInit(): void {}

  async onLogin(){

    let isOrg:boolean = this.formGroup.get("isOrg")?.value;
    let username:string = this.formGroup.get("username")?.value;
    let password:string = this.formGroup.get("password")?.value;

    if(isOrg)
    {
      this.organizationService.login(username,password).subscribe(next =>{
        this.organizationService.setOrganisation(next);
        this.router.navigate(['org-profile']);
      })
    }
    else
    {
      this.userService.login(username, password).subscribe(
        next => {
          this.userService.setUser(new User(next.userID, next.username, next.userFirstName, next.userEmail, next.password, [], []));
          this.router.navigate(['profile']);
        }
      );
      
  }
}

}
