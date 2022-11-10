import { JobCategory } from "../enum/job-category";
import { JobType } from "../enum/job-type";

export class UserApplication{
    public constructor (
        public id:string,
        public type: JobType,
        public category: JobCategory,
        public orgName:string,
        public response:number,
        public orgId:string,
        public title:string
        ){
            
        }
}