import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { UserModuleModule } from '../user-module/user-module.module';
import { OrganisationModuleModule } from '../organisation-module/organisation-module.module';
import { ComponentsModule } from '../shared/components/components.module';
import { AppComponent } from './app-component/app.component';
import { NavComponent } from './nav/nav.component';
import { MaterialModule } from '../shared/material.module';
import { SideNavComponent } from './side-nav/side-nav.component';
import { HttpClientXsrfModule } from '@angular/common/http';
 
@NgModule({
  declarations: [
    AppComponent,
    NavComponent,
    SideNavComponent
  ],
  imports: [
    HttpClientXsrfModule,
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    UserModuleModule,
    OrganisationModuleModule,
    ComponentsModule,
    MaterialModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
