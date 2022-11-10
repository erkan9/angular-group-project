import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Experience } from '../models/experience/experience';
import { NewExperience } from '../models/experience/new-experience';

@Injectable({
  providedIn: 'root'
})
export class ExperienceService {

  constructor(
    private httpClient: HttpClient
    ) 
    {
      
    }

  public create(newExperience: NewExperience): Observable<Experience> {
    let url = "https://localhost:5001/api/ExperienceController/Create";

    return this.httpClient.post<Experience>(url,newExperience);
  }
}
