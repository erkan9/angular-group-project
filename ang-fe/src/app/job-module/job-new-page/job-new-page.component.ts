import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { JobCategory } from 'src/app/shared/models/enum/job-category';
import { JobType } from 'src/app/shared/models/enum/job-type';
import { NewJob } from 'src/app/shared/models/job-offer/new-job';
import { JobsService } from 'src/app/shared/services/jobs.service';

@Component({
  selector: 'app-job-new-page',
  templateUrl: './job-new-page.component.html',
  styleUrls: ['./job-new-page.component.css']
})
export class JobNewPageComponent implements OnInit {

  title!: FormGroup;
  choices!: FormGroup;
  description!: FormGroup;

  categoryOptions!:JobCategory[];
  typeOptions!:JobType[];

  private newJob!:NewJob;

  constructor(
    private fb: FormBuilder,
    private jobService: JobsService,
    private router: Router
    ) { }

  ngOnInit(): void {
    this.categoryOptions = Object.values(JobCategory);
    this.typeOptions = Object.values(JobType);
    this.buildForm();
  }

  private buildForm(): void {
    this.title = this.fb.group({
      title: [this.newJob?.title, [Validators.required]]
    });

    this.description = this.fb.group({
      description: [this.newJob?.description, [Validators.required, Validators.maxLength(2048)]]
    });

    this.choices = this.fb.group({
      type: [this.newJob?.type, [Validators.required]],
      category: [this.newJob?.category, [Validators.required]]
    });
  }

  submit(): void {
    if (this.title.invalid || this.description.invalid || this.choices.invalid) {
      console.log("Form Errors! Bruh");
      return;
    }

    this.newJob =new NewJob(
      this.title.get("title")?.value,
      this.description.get("description")?.value,
      this.choices.get("type")?.value,
      this.choices.get("category")?.value
    );

    console.log(this.newJob);
    console.log("submit complete");

     this.jobService.newJob(this.newJob).subscribe(
       next => {
         this.router.navigate(['job-detail', next.jobAdId]);
      });
  }

}
