import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { ProfileDeleteDialogComponent } from 'src/app/shared/components/profile-delete-dialog/profile-delete-dialog.component';
import { OrganisationService } from 'src/app/shared/services/organisation.service';

@Component({
  selector: 'app-org-profile-page',
  templateUrl: './org-profile-page.component.html',
  styleUrls: ['./org-profile-page.component.css']
})
export class OrgProfilePageComponent implements OnInit {
  
  companyName?: string;
  email?: string;
  isEditing: boolean = false;
  editedEmail!: string;
  editedUsername!: string;
  newPassword: string = "";




  constructor(public dialog: MatDialog,private organisationService:OrganisationService) { }

  ngOnInit(): void {
    let id = this.organisationService.getCurrentOrganisation().organizationID;
    this.organisationService.getById(id).subscribe(
      next => {
        this.companyName = this.editedUsername = next.organizationName;
        this.email = this.editedEmail = next.organizationEmail;
      }
    );
  }

  onDelete(){
    const dialogRef = this.dialog.open(ProfileDeleteDialogComponent, {
      width: '250px',
      data: {}
    });

    dialogRef.afterClosed().subscribe(() => {
      console.log('The dialog was closed');
    });
  }

  onEdit(){
    this.isEditing=!this.isEditing;
    console.log("Clicked Edit");
  }

  modifyAccount(){
    let id = this.organisationService.getCurrentOrganisation().organizationID;
    this.organisationService.update(id, this.editedEmail, this.editedUsername, this.newPassword).subscribe(
      ()=> {
        this.isEditing = false;
        this.organisationService.getById(id).subscribe(next => {
          this.companyName = this.editedUsername = next.organizationName;
          this.email = this.editedEmail = next.organizationEmail;
        })
      }
    );
  }
}
