import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { ProfileDeleteDialogComponent } from 'src/app/shared/components/profile-delete-dialog/profile-delete-dialog.component';
import { UserProfileData } from 'src/app/shared/models/user-profile/user-profile';
import { UserService } from 'src/app/shared/services/user.service';

@Component({
  templateUrl: './user-profile-page.component.html',
  styleUrls: ['./user-profile-page.component.css']
})
export class UserProfilePageComponent implements OnInit {

  profileData!: UserProfileData;
  isEditing: boolean = false;
  editedFirstName!: string;
  editedEmail!: string;
  editedUsername!: string;
  newPassword: string = "";

  constructor(public dialog: MatDialog, private userService: UserService) { }

  ngOnInit(): void {
    this.userService.getUserProfileData(this.userService.getCurrentUser().userID).subscribe(
      next => {
        this.profileData = next;
        this.editedUsername = this.profileData.username;
        this.editedEmail = this.profileData.userEmail;
        this.editedFirstName = this.profileData.userFirstName;
      });
  }

  onDelete() {
    const dialogRef = this.dialog.open(ProfileDeleteDialogComponent, {
      width: '250px',
      data: {}
    });

    dialogRef.afterClosed().subscribe(() => {
      console.log('The dialog was closed');
    });
  }

  onEdit() {
    this.isEditing = !this.isEditing;
    console.log("Clicked Edit");
  }

  modifyAccount() {
    let id = this.userService.getCurrentUser().userID;
    this.userService.update(id, this.editedEmail, this.editedUsername, this.editedFirstName, this.newPassword).subscribe(
      next => {
        this.isEditing = false;
        this.userService.getUserProfileData(id).subscribe(next => {
          this.profileData = next;
          this.editedUsername = this.profileData.username;
          this.editedEmail = this.profileData.userEmail;
          this.editedFirstName = this.profileData.userFirstName;
        })
      }
    );
  }
}
