import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { OrgProfilePageComponent } from './org-profile-page/org-profile-page.component';
import { OrgProfileListingsComponent } from './org-profile-listings/org-profile-listings.component';
import { MaterialModule } from '../shared/material.module';
import { ComponentsModule } from '../shared/components/components.module';
import { RouterModule, Routes } from '@angular/router';
import { FormsModule } from '@angular/forms';

const routes: Routes = [
  {path:"org-profile", component:OrgProfilePageComponent}
];


@NgModule({
  declarations: [
    OrgProfilePageComponent,
    OrgProfileListingsComponent
  ],
  imports: [
    CommonModule,
    MaterialModule,
    ComponentsModule,
    FormsModule,
    RouterModule.forChild(routes)
  ]
})
export class OrganisationModuleModule { }
