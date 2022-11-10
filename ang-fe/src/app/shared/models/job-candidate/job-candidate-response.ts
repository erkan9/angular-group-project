export class JobCandidateResponse{

    public constructor(
        public id:string,
        public offerId:string,
        public userId:string,
        public status:number
    ){}
  }