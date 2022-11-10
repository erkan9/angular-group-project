import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserRegister } from 'src/app/shared/models/user/user-register';
import { UserService } from 'src/app/shared/services/user.service';
import { OrganisationRegister } from '../../models/organisation/organisation-register';
import { User } from '../../models/user/user';
import { OrganisationService } from '../../services/organisation.service';

@Component({
  selector: 'app-user-register',
  templateUrl: './user-register.component.html',
  styleUrls: ['./user-register.component.css']
})
export class UserRegisterComponent implements OnInit {

  username!:string;
  name!:string;
  email!:string;
  password!:string;

  isOrgRegistering: boolean = true;

  constructor(
    private userService: UserService,
    private orgService: OrganisationService,
    private router: Router
  ) { }

  ngOnInit(): void {
  }

  registerUser() {
    if (this.isOrgRegistering) {
      this.orgService.register({ organizationName: this.name, organizationEmail: this.email, password: this.password }).subscribe(
        next => {
          this.orgService.setOrganisation(next);
          this.router.navigate(['org-profile']);
        }
      );
    }
    else {
      this.userService.register({ userFirstName: this.name, userEmail: this.email, password: this.password, username: this.username }).subscribe(
        next => {
          this.userService.setUser(new User(next.userID, next.username, next.userFirstName, next.userEmail, next.password, [], []));
          this.router.navigate(['profile']);
        }
      );
    }
  }

}
