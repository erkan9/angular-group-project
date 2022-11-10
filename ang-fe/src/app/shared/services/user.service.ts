import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { BehaviorSubject, Observable } from 'rxjs';
import { ProfileDisplayApplications } from '../models/job-offer/profile-display-applications';
import { UserProfileData } from '../models/user-profile/user-profile';
import { User } from '../models/user/user';
import { UserRegister } from '../models/user/user-register';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private currentUser?: User;

  constructor(private httpClient: HttpClient, private router: Router) { }

  public loggedIn = new BehaviorSubject<boolean>(true);
  loggedIn$ = this.loggedIn.asObservable();

  public getCurrentUser(): User {
    return JSON.parse(localStorage.getItem('currentUser')!);
  }

  public getStoredUserProfileData(): UserProfileData {
    console.log("from storage")
    console.log(JSON.parse(localStorage.getItem('userProfilePageData')!));
    return JSON.parse(localStorage.getItem('userProfilePageData')!);
  }

  public register(user: UserRegister): Observable<User> {
    const url = "http://localhost:8087/api/v1/user/register";

    return this.httpClient.post<User>(url, user);
  }

  public login(email: string, password: string,): Observable<User> {
    const url = `http://localhost:8087/api/v1/user/login?username=${email}&password=${password}`;
    let body = { "username": email, "password": password };

    return this.httpClient.post<User>(url, body, {
      headers: {
        'withCredentials': 'true'
      }
    })
  };

  public getUserProfileData(id: string): Observable<UserProfileData> {
    const url = `http://localhost:8087/api/v1/user?userID=${id}`;

    return this.httpClient.get<UserProfileData>(url);
  }

  public logout() {
    this.loggedIn.next(false);
    localStorage.removeItem("currentUser");
    localStorage.removeItem("userProfilePageData");
    this.currentUser = undefined;
  }

  public setUser(result: User) {
    this.currentUser = new User(result.userID, result.username, result.userFirstName, result.userEmail, result.password, result.applications, result.reviews);
    localStorage.setItem('currentUser', JSON.stringify(this.currentUser));
  }

  public deleteCurrentUser(): boolean {
    const url = `http://localhost:8087/api/v1/user/delete?userID=${this.getCurrentUser()?.userID}`;

    let isDeleted: boolean = false;
    this.httpClient.delete<boolean>(url).subscribe(result => {
      isDeleted = result;
      localStorage.removeItem("currentUser");
      this.currentUser = undefined;
      this.router.navigate(['user-signup']);
    });

    return isDeleted;
  }

  public getUserApplicationsAsync(id: string): Observable<ProfileDisplayApplications[]> {
    let applications: ProfileDisplayApplications[] = new Array();

    const getAppliedOffersUrl = `http://localhost:8087/api/v1/jobAdds/userApplied?userId=${id}`;

    return this.httpClient.get<ProfileDisplayApplications[]>(getAppliedOffersUrl);
  }

  public update(id: string, email: string, username: string, fistName: string, password: string): Observable<void> {
    const url = "http://localhost:8087/api/v1/user/update";

    let body = { "userID": id, "userEmail": email, "username": username, "password": password, "userFirstName":fistName };

    console.log(body);

    return this.httpClient.patch<any>(url, body);
  }
}
