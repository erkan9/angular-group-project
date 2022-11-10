export class OrganisationResponse{

    public constructor(
        public id: string,
        public name: string,
        public email: string,
        public offerIds: string[]
        ){}
}