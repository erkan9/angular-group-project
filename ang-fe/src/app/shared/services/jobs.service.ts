import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { Applicant } from '../models/job-candidate/applicant';
import { NewCandidateResponse } from '../models/job-candidate/new-candidate-response';
import { JobDetail } from '../models/job-offer/job-detail';
import { JobOfferListing } from '../models/job-offer/jobOfferListing';
import { ListingGetDto } from '../models/job-offer/listing-get-dto';
import { NewJob } from '../models/job-offer/new-job';
import { ProfileDisplayApplications } from '../models/job-offer/profile-display-applications';
import { OrganisationService } from './organisation.service';
import { UserService } from './user.service';

@Injectable({
  providedIn: 'root'
})
export class JobsService {

  constructor(private httpClient: HttpClient, private userService: UserService, private organisationService: OrganisationService) { }

  public getOffers(): Observable<ListingGetDto[]> {
    const getOffersUrl = `http://localhost:8087/api/v1/jobAdds`;

    return this.httpClient.get<ListingGetDto[]>(getOffersUrl);
  }


  public getById(id: string | null): Observable<JobDetail> {
    const getOffersUrl = `http://localhost:8087/api/v1/jobAdds?id=${id}`;
    return this.httpClient.get<JobDetail>(getOffersUrl);
  }

  public apply(id: string | null): Observable<NewCandidateResponse> {
    let userId = this.userService.getCurrentUser().userID;
    const getOffersUrl = `http://localhost:8087/api/v1/jobAdds/apply?candidateId=${userId}&jobAdId=${id}`;
    return this.httpClient.post<NewCandidateResponse>(getOffersUrl, "");
  }

  public rateOffer(rating: boolean, id: string | null): void {
    let userId = this.userService.getCurrentUser().userID;

    let body = JSON.stringify({
      "UserId": userId,
      "OfferId": id,
      "IsPositive": rating
    });

    const options = {
      headers: new HttpHeaders({ 'Content-Type': 'application/json' })
    }

    const getOffersUrl = `https://localhost:5001/api/ReviewController/Create`;
    this.httpClient.post(getOffersUrl, body, options).subscribe();
  }

  public newJob(newJob: NewJob): Observable<JobDetail> {
    let orgId = this.organisationService.getCurrentOrganisation().organizationID;
    const url = `http://localhost:8087/api/v1/jobAdds/add`;

    const options = {
      headers: new HttpHeaders({ 'Content-Type': 'application/json' })
    }

    let body = JSON.stringify({
       "header": newJob.title,
       "description": newJob.description,
       "jobAdType": newJob.type,
       "jobAdCategory": newJob.category,
       "jobAdPublisherID":orgId,
       "isActive": true,
       "numberOfLikes":0
      });
    return this.httpClient.post<JobDetail>(url, body, options);
  }

  public likeOffer(jobadID: string): Observable<void>{
    const url = `http://localhost:8087/api/v1/jobAdds/like?userId=${this.userService.getCurrentUser().userID}&jobAdId=${jobadID}`;

    return this.httpClient.post<any>(url,{});
    
  }


  public getApplicants(id: string): Observable<Applicant[]> {
    const url = `http://localhost:8087/api/v1/jobAdds/candidates?jobAdId=${id}`;

    return this.httpClient.get<Applicant[]>(url);
  }

  public GiveCandidateResponse(offerId: string, candidateId: string, isApproved: boolean): Observable<void> {
    const url = `http://localhost:8087/api/v1/jobAdds/respond?userId=${candidateId}&jobAdId=${offerId}&approval=${isApproved}`;
    

    return this.httpClient.post<any>(url,{});
  }

  updateJob(title:string, desc: string, type:string, cat:string, id:string): Observable<any>{
    let orgId = this.organisationService.getCurrentOrganisation().organizationID;
    const url = `http://localhost:8087/api/v1/jobAdds/update`;

    const options = {
      headers: new HttpHeaders({ 'Content-Type': 'application/json' })
    }

    let body = JSON.stringify({
      "jobAdId": id,
       "header": title,
       "description": desc,
       "jobAdType": type,
       "jobAdCategory": cat,
       "jobAdPublisherID":orgId
      });

      return this.httpClient.patch<any>(url, body, options);
  }
}
