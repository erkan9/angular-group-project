import { Component, OnInit, ViewChild } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { ListingGetDto } from 'src/app/shared/models/job-offer/listing-get-dto';
import { JobsService } from 'src/app/shared/services/jobs.service';

@Component({
  selector: 'app-job-listing',
  templateUrl: './job-listing.component.html',
  styleUrls: ['./job-listing.component.css']
})
export class JobListingComponent implements OnInit {

  displayedColumns: string[]= ["Title", "Type", "Category", "Publisher"];
  dataSource!:MatTableDataSource<ListingGetDto>;

  constructor(private jobService:JobsService) { }

   ngOnInit(): void {

    this.jobService.getOffers().subscribe(next =>{
       this.dataSource =  new MatTableDataSource(next);
       console.log("ds",this.dataSource);
       console.log("nedt",next);
      });
  }
}