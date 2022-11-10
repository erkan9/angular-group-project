import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { OrganisationService } from './organisation.service';
import { UserService } from './user.service';

@Injectable({
  providedIn: 'root'
})
export class AppService {

  private isSidenavActive = new BehaviorSubject<boolean>(true);
  isSidenavActive$ = this.isSidenavActive.asObservable();
  constructor(private userService: UserService, private orgService: OrganisationService) { }

  public toggleSidenav():void {
    this.isSidenavActive.next(!this.isSidenavActive.value);
  }

  public isUserLoggedIn(){
    return this.userService.getCurrentUser() !== null && this.userService.getCurrentUser() !== undefined;
  }

  public isOrganisationLoggedIn(){
    return this.orgService.getCurrentOrganisation() !== null && this.orgService.getCurrentOrganisation() !== undefined;
  }

  public noneLoggedIn(){
    return !this.isUserLoggedIn() && !this.isOrganisationLoggedIn();
  }

  public isAnyoneLoggedIn(): boolean{
    return this.isUserLoggedIn() || this.isOrganisationLoggedIn();
  }

}
