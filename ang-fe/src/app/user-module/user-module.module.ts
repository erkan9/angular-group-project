import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { UserProfilePageComponent } from './user-profile-page/user-profile-page.component';
import { MaterialModule } from '../shared/material.module';
import { RouterModule, Routes } from '@angular/router';
import { UserLoginComponent } from '../shared/components/user-login/user-login.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { UserRegisterComponent } from '../shared/components/user-register/user-register.component';
import { HttpClientModule, HttpClientXsrfModule } from '@angular/common/http';
import { ComponentsModule } from '../shared/components/components.module';
import { UserProfileApplicationsComponent } from './user-profile-applications/user-profile-applications.component';

const routes: Routes=[
  {path: "profile", component:UserProfilePageComponent},
  {path: "user-signin", component:UserLoginComponent},
  {path: "user-signup", component:UserRegisterComponent}
];

@NgModule({
  declarations: [
    UserProfilePageComponent,
    UserProfileApplicationsComponent
  ],
  imports: [
    HttpClientXsrfModule.withOptions({
      cookieName: 'app-Xsrf-Cookie',
      headerName: 'app-Xsrf-Header',
      }),
    ComponentsModule,
    CommonModule,
    MaterialModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    RouterModule.forChild(routes)
  ]
})
export class UserModuleModule { }
