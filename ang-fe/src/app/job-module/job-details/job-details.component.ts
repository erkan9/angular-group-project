import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { switchMap } from 'rxjs';
import { JobDetail } from 'src/app/shared/models/job-offer/job-detail';
import { AppService } from 'src/app/shared/services/app.service';
import { JobsService } from 'src/app/shared/services/jobs.service';

@Component({
  selector: 'app-job-details',
  templateUrl: './job-details.component.html',
  styleUrls: ['./job-details.component.css']
})
export class JobDetailsComponent implements OnInit {

  offer!:JobDetail;

  constructor(private route: ActivatedRoute, private jobService: JobsService, public appService: AppService) {
  }

  ngOnInit(): void {
    this.route.paramMap.pipe(
      switchMap(params => {
        let id = String(params.get('id'));
        return this.jobService.getById(id);
      })).subscribe(res=>{
        this.offer = res;

        console.log(this.offer);
      });
  }

  handleApplying(): void {
    this.jobService.apply(this.offer?.jobAdId).subscribe(
      next => {

      },
      error => console.log(error)
    );
  }

  handleLike(): void {
    this.jobService.likeOffer(this.offer.jobAdId).subscribe(() => this.offer.numberOfLikes++);
  }
}
