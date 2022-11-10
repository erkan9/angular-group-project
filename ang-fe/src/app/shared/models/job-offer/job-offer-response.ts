import { JobCategory } from "../enum/job-category";
import { JobType } from "../enum/job-type";

export class JobOfferResponse{

    public constructor(
        public id: string,
        public title: string,
        public description: string,
        public reviewScore: number,
        public type: JobType,
        public category: JobCategory,
        public publisherId: string,
        public andidateIds: string[],
        public reviewIds: string[]
        ){};
}