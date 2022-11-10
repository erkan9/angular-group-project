import { Component, Input, OnInit, ViewChild } from '@angular/core';
import { UserService } from 'src/app/shared/services/user.service';
import { MatTable } from '@angular/material/table';
import { ProfileDisplayApplications } from 'src/app/shared/models/job-offer/profile-display-applications';

@Component({
  selector: 'app-user-profile-applications',
  templateUrl: './user-profile-applications.component.html',
  styleUrls: ['./user-profile-applications.component.css']
})
export class UserProfileApplicationsComponent implements OnInit {

  @ViewChild(MatTable) table!: MatTable<any>;

  fetchedJobs: ProfileDisplayApplications[] = new Array();
  displayedColumns: string[] = ["Title", "Type", "Category", "Publisher", "Response"];
  dataSource = this.fetchedJobs;

  constructor(private userService: UserService) { }

  async ngOnInit(): Promise<void> {
    this.userService.getUserApplicationsAsync(this.userService.getCurrentUser().userID).subscribe(next=>{
      console.log(next);
      this.dataSource = next;
    });
    this.table?.renderRows();
  }
}