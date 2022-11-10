import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MaterialModule } from '../shared/material.module';
import { RouterModule, Routes } from '@angular/router';
import { JobListingComponent } from './job-listing/job-listing.component';
import { JobDetailsComponent } from './job-details/job-details.component';
import { JobNewPageComponent } from './job-new-page/job-new-page.component';
import { ComponentsModule } from '../shared/components/components.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { JobManageComponent } from './job-manage/job-manage.component';

const routes: Routes = [
  {path:"job-list", component:JobListingComponent},
  {path:"job-detail/:id", component:JobDetailsComponent},
  {path:"job-manage/:id", component:JobManageComponent},
  {path:"job-new-page", component:JobNewPageComponent}
];

@NgModule({
  declarations: [JobListingComponent, JobDetailsComponent, JobNewPageComponent, JobManageComponent],
  imports: [
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    ReactiveFormsModule,
    ComponentsModule,
    CommonModule,
    MaterialModule,
    RouterModule.forChild(routes)
  ]
})
export class JobModuleModule { }
