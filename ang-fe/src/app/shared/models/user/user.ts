import { JobOffer } from "../job-offer/job-offer";

export class User {
    public constructor (
        public userID:string,
        public username:string,
        public userFirstName:string,
        public userEmail:string,
        public password:string,
        public applications:JobOffer[],
        public reviews:string[]){
        }
}
