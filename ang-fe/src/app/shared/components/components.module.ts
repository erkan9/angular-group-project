import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ProfileDeleteDialogComponent } from './profile-delete-dialog/profile-delete-dialog.component';
import { MaterialModule } from '../material.module';
import { JobCategoryDisplayPipe } from './pipes/job-category-display.pipe';
import { JobTypeDisplayPipe } from './pipes/job-type-display.pipe';
import { CandidateDisplayPipe } from './pipes/candidate-display.pipe';
import { UserLoginComponent } from './user-login/user-login.component';
import { UserRegisterComponent } from './user-register/user-register.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';


@NgModule({
  declarations: [
    ProfileDeleteDialogComponent,
    JobCategoryDisplayPipe,
    JobTypeDisplayPipe,
    CandidateDisplayPipe,
    UserLoginComponent,
    UserRegisterComponent
  ],
  imports: [
    CommonModule,
    MaterialModule,
    FormsModule,
    ReactiveFormsModule
  ],
  exports:[
    JobCategoryDisplayPipe,
    JobTypeDisplayPipe,
    CandidateDisplayPipe
  ]
})
export class ComponentsModule { }
