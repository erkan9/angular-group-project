import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UserModuleModule } from '../user-module/user-module.module';
import { OrganisationModuleModule } from '../organisation-module/organisation-module.module';
import { JobModuleModule } from '../job-module/job-module.module';
import { NavComponent } from './nav/nav.component';

const routes: Routes = [
  {path:"app-nav",component:NavComponent},
  {path:"**", redirectTo:"user-signin"}
];

@NgModule({
  imports:
  [
    RouterModule.forRoot(routes),
    UserModuleModule,
    OrganisationModuleModule,
    JobModuleModule
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
