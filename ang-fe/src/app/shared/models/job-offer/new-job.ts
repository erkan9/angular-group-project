import { JobCategory } from "../enum/job-category";
import { JobType } from "../enum/job-type";

export class NewJob{
    constructor(
        public title:string,
        public description:string,
        public type:JobType,
        public category:JobCategory
    ){}
}