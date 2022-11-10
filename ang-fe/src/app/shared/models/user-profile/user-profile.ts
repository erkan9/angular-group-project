import { UserApplication } from "./user-application";
import { UserExperience } from "./user-experience";
import { UserPost } from "./user-post";

export class UserProfileData {
    public constructor (
        public userID:string,
        public username:string,
        public userFirstName:string,
        public userEmail:string,
        public password:string,
        public posts:UserPost[],
        public applications:UserApplication[],
        public experiences:UserExperience[]
    ){
        }
}
