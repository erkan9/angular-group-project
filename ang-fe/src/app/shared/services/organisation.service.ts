import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { BehaviorSubject, Observable, retry } from 'rxjs';
import { OrgJobListing } from '../models/job-offer/org-job-listing';
import { Organisation } from '../models/organisation/organisation';
import { OrganisationRegister } from '../models/organisation/organisation-register';

@Injectable({
  providedIn: 'root'
})
export class OrganisationService {

  private currentOrganisation?: Organisation;

  public loggedIn = new BehaviorSubject<boolean>(false);
  loggedIn$ = this.loggedIn.asObservable();

  constructor(private httpClient: HttpClient, private router: Router) {

  }

  public getCurrentOrganisation(): Organisation {
    return JSON.parse(localStorage.getItem('currentOrganisation')!);
  }

  public deleteCurrentOrg(): boolean {
    const url = `http://localhost:8087/api/v1/organization/delete?orgId=${this.getCurrentOrganisation()?.organizationID}`;

    let isDeleted: boolean = false;
    this.httpClient.delete<boolean>(url).subscribe(result => {
      isDeleted = result;
      localStorage.removeItem("currentOrganisation");
      this.currentOrganisation = undefined;
      this.router.navigate(['org-signup']);
    });

    return isDeleted;
  }

  public logout() {
    this.loggedIn.next(false);
    localStorage.removeItem("currentOrganisation");
    this.currentOrganisation = undefined;
  }

  public getById(id: string): Observable<Organisation> {
    const url = `http://localhost:8087/api/v1/organization/getById?orgId=${id}`;

    return this.httpClient.get<Organisation>(url);
  }

  public login(email: string, password: string): Observable<Organisation> {
    const url = `http://localhost:8087/api/v1/organization/login?organizationEmail=${email}&password=${password}`;
    
    return this.httpClient.post<Organisation>(url,{});
  }

  public setOrganisation(org: Organisation) {
    this.currentOrganisation = new Organisation(org.organizationID, org.organizationName, org.organizationEmail, org.password, org.jobOffers);
    localStorage.setItem('currentOrganisation', JSON.stringify(this.currentOrganisation));
  }

  public getOrganisationJobs(): Observable<OrgJobListing[]> {
    const url = `http://localhost:8087/api/v1/orgJobs?id=${this.getCurrentOrganisation().organizationID}`;
    return this.httpClient.get<OrgJobListing[]>(url);
  }

  public update(id: string, email: string, username: string, password: string): Observable<Organisation> {
    const url = "http://localhost:8087/api/v1/organization/update";
    let body = { "organizationID": id, "organizationEmail": email, "organizationName": username, "password": password };

    console.log(body);

    return this.httpClient.patch<Organisation>(url, body);
  }

  public register(orgReg: OrganisationRegister): Observable<Organisation> {
    const url = "http://localhost:8087/api/v1/organization/register";

    return this.httpClient.post<Organisation>(url, orgReg);
  }
}
