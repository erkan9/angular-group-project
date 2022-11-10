export class JobDetail{

    public constructor(
        public jobAdId:string,
        public header:string,
        public description:string,
        public jobAdType:string,
        public jobAdCategory: string,
        public jobAdPublisherID: number,
        public isActive: boolean,
        public numberOfLikes: number
        ){}
    
}