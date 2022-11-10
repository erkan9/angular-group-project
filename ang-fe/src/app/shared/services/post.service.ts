import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { NewPost } from '../models/posts/new-post';
import { PostListing } from '../models/posts/post-listing';
import { PostDetail } from '../models/posts/psot-detail';
import { UserService } from './user.service';

@Injectable({
  providedIn: 'root'
})
export class PostService {

  constructor(private httpClient: HttpClient,private userService:UserService) { }

  public getAllPosts(): Observable<PostListing[]> {
    const url = "https://localhost:5001/api/PostsController/GetAll";

    return this.httpClient.get<PostListing[]>(url);
  }

  public getPostListing(id:string): Observable<PostListing> {
    const url = `https://localhost:5001/api/PostsController/GetPostListing/${id}`;

    return this.httpClient.get<PostListing>(url);
  }

  public getPostDetail(id:string): Observable<PostDetail> {
    const url = `https://localhost:5001/api/PostsController/GetPostDetail/${id}`;

    return this.httpClient.get<PostDetail>(url);
  }

  public rateOffer(id:string, isPositive:boolean): Observable<number>{
    let userId = this.userService.getStoredUserProfileData().id;
    const url = `https://localhost:5001/api/PostsController/RatePost/${id}`;

    return this.httpClient.put<number>(url, {"UserId":userId, "IsPositive":isPositive});
  }

  public create(post: NewPost) : Observable<PostDetail>{
    const url = "https://localhost:5001/api/PostsController/Create";

    return this.httpClient.post<PostDetail>(url,post);
  }

}
