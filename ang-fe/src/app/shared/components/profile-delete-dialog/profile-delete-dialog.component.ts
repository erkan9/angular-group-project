import { Component, OnInit } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
import { AppService } from '../../services/app.service';
import { OrganisationService } from '../../services/organisation.service';
import { UserService } from '../../services/user.service';

@Component({
  selector: 'app-profile-delete-dialog',
  templateUrl: './profile-delete-dialog.component.html',
  styleUrls: ['./profile-delete-dialog.component.css']
})
export class ProfileDeleteDialogComponent implements OnInit {

  constructor(
    public dialogRef: MatDialogRef<ProfileDeleteDialogComponent>,
    private userService: UserService,
    private orgService: OrganisationService,
    private appService: AppService
   ) { }

  ngOnInit(): void {
  }

  onConfirmedDelete(){
    console.log("delete confirmed");

    let isDeleted: boolean = false;


    if(this.appService.isUserLoggedIn()) isDeleted = this.userService.deleteCurrentUser();

    if(this.appService.isOrganisationLoggedIn()) isDeleted = this.orgService.deleteCurrentOrg();
    
     
    this.dialogRef.close();
  }

  onRejectedDelete(){
    this.dialogRef.close();
  }

}
