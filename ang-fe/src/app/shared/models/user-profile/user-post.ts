export class UserPost{
    public constructor (
        public id:string,
        public title:string,
        public intro:string,
        public publishedDate:Date,
        public score:number
        ){
            
        }
}