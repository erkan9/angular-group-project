import { JobOffer } from "../job-offer/job-offer";

export class Organisation {

    public constructor(
        public organizationID:string,
        public organizationName:string,
        public organizationEmail:string,
        public password:string,
        public jobOffers:JobOffer[]){
        }
}
