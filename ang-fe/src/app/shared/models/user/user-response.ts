export class UserResponseDTO{

    constructor(
        public id: string,
        public firstName: string,
        public lastName : string,
        public description: string,
        public email: string,
        public candidateIds: string[],
        public reviewIds: string[]
        ){};
}
