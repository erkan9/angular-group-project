import { Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatTable, MatTableDataSource } from '@angular/material/table';
import { ActivatedRoute } from '@angular/router';
import { of, switchMap } from 'rxjs';
import { CandidateStatus } from 'src/app/shared/models/enum/candidate-status';
import { JobCategory } from 'src/app/shared/models/enum/job-category';
import { JobType } from 'src/app/shared/models/enum/job-type';
import { Applicant } from 'src/app/shared/models/job-candidate/applicant';
import { CandidateReply } from 'src/app/shared/models/job-candidate/candidate-reply';
import { JobDetail } from 'src/app/shared/models/job-offer/job-detail';
import { NewJob } from 'src/app/shared/models/job-offer/new-job';
import { AppService } from 'src/app/shared/services/app.service';
import { JobsService } from 'src/app/shared/services/jobs.service';
import { OrganisationService } from 'src/app/shared/services/organisation.service';
import { UserService } from 'src/app/shared/services/user.service';

@Component({
  selector: 'app-job-manage',
  templateUrl: './job-manage.component.html',
  styleUrls: ['./job-manage.component.css']
})
export class JobManageComponent implements OnInit {

  id: string = "";
  offer!: JobDetail;
  isEditing:boolean = false;
  
  title!: FormGroup;
  choices!: FormGroup;
  description!: FormGroup;

  categoryOptions!:JobCategory[];
  typeOptions!:JobType[];

  applicants: Applicant[] = new Array();

  @ViewChild(MatTable) table!: MatTable<any>;

  displayedColumns: string[] = ["fullName", "email", "status", "actions"];
  dataSource = new MatTableDataSource<Applicant>(this.applicants);

  constructor(
    private route: ActivatedRoute,
    private jobService: JobsService,
    public appService: AppService,
    private userService: UserService,
    private organisationService: OrganisationService,
    private fb: FormBuilder
  ) { }

  ngOnInit(): void {
    this.route.paramMap.pipe(
      switchMap(params => {
        this.id = String(params.get('id'));
        return this.jobService.getById(this.id);
      })).subscribe(res => {
        this.offer = res;
      });

    this.jobService.getApplicants(this.id).subscribe(applicants => {
      console.log(applicants);
      this.dataSource.data = applicants;
    });
    this.table?.renderRows();

    this.categoryOptions = Object.values(JobCategory);
    this.typeOptions = Object.values(JobType);
    this.buildForm();
  }

  giveApproval(offerId: string, candidateId: string, isApproved: boolean): void {

    this.jobService.GiveCandidateResponse(offerId, candidateId, isApproved).subscribe(() => {

        this.dataSource.data.forEach(c =>{

          if(c.userId == candidateId) c.status = isApproved? "Approved":"Rejected"
        }
        );
    }
    );
  }

  toBeResolved(candidateId: string): boolean {
    let toBeResolved: boolean = this.dataSource.data.find(c => c.userId == candidateId)?.status == "Pending";
    
    return toBeResolved;
  }

  handleEdit(): void {
    this.isEditing = !this.isEditing;
  }

  private buildForm(): void {
    this.title = this.fb.group({
      title: [this.offer?.header, [Validators.required]]
    });

    this.description = this.fb.group({
      description: [this.offer?.description, [Validators.required, Validators.maxLength(2048)]]
    });

    this.choices = this.fb.group({
      type: [this.offer?.jobAdType, [Validators.required]],
      category: [this.offer?.jobAdCategory, [Validators.required]]
    });
  }

  submit(): void {
    if (this.title.invalid || this.description.invalid || this.choices.invalid) {
      console.log("Form Errors! Bruh");
      return;
    }

      let t = this.title.get("title")?.value;
      let d = this.description.get("description")?.value;
      let tt = this.choices.get("type")?.value;
      let c = this.choices.get("category")?.value;

    console.log(t);

     this.jobService.updateJob(t, d, tt, c, this.offer.jobAdId).subscribe(
       () => {
         this.offer.description = d;
         this.offer.header = t;
         this.offer.jobAdCategory = c;
         this.offer.jobAdType = tt;
      });
  }
}
