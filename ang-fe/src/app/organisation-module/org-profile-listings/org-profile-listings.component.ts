import { Component, OnInit } from '@angular/core';
import { OrgJobListing } from 'src/app/shared/models/job-offer/org-job-listing';
import { OrganisationService } from 'src/app/shared/services/organisation.service';

@Component({
  selector: 'app-org-profile-listings',
  templateUrl: './org-profile-listings.component.html',
  styleUrls: ['./org-profile-listings.component.css']
})
export class OrgProfileListingsComponent implements OnInit {

  source: OrgJobListing[] = new Array();

  displayedColumns:string[] = ["Title","Type","Category", "Status"];
  
  constructor(private organisationService:OrganisationService) {
    
   }

  ngOnInit(): void {
    this.organisationService.getOrganisationJobs().subscribe(res =>{ this.source = res; console.log(res)});
  }

}

