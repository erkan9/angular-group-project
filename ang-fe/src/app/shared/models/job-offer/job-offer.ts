import { JobCategory } from "../enum/job-category";
import { JobType } from "../enum/job-type";
import { Organisation } from "../organisation/organisation";
import { User } from "../user/user";

export class JobOffer {

    public constructor(
        public id:string,
        public title:string,
        public description:string,
        public likeCount:number,
        public type: JobType,
        public category: JobCategory,
        public publisher:Organisation,
        public pendingCandidates?:User[],
        public rejectedCandidates?:User[],
        public acceptedCandidate?:User
    ){}
    
}
