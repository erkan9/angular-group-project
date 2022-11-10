import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { AppService } from 'src/app/shared/services/app.service';
import { OrganisationService } from 'src/app/shared/services/organisation.service';
import { UserService } from 'src/app/shared/services/user.service';

@Component({
  selector: 'app-side-nav',
  templateUrl: './side-nav.component.html',
  styleUrls: ['./side-nav.component.css']
})
export class SideNavComponent implements OnInit {

  isOpenned:boolean = false;
  subscription: Subscription = new Subscription();
  isUserLoggedIn = false;
  subscription2: Subscription = new Subscription();
  isOrgLoggedIn = false;

  constructor(public appService: AppService, private userService: UserService,private router: Router, private organisationService: OrganisationService) {
   }

  ngOnInit(): void {
    this.subscription = this.appService.isSidenavActive$
    .subscribe(fixed => this.isOpenned = fixed);
    this.userService.loggedIn$.subscribe(next=>this.isUserLoggedIn = next );

    this.subscription = this.appService.isSidenavActive$
    .subscribe(fixed => this.isOpenned = fixed);
    this.organisationService.loggedIn$.subscribe(next=>this.isOrgLoggedIn = next );
  }

  public handleLogout(){
    this.userService.logout();
    this.organisationService.logout();
    this.router.navigate(['user-signup']);
  }

}
