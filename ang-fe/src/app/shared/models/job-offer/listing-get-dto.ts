export class ListingGetDto {
    
    public constructor(
        public header:string,
        public jobAdType: string,
        public jobAdCategory: string,
        public publisherName: string,
        public jobAdPublisherID:number,
        public response:string,
        public jobAdId:string
        ){}
}
