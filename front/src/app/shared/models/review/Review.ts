import { ReviewStatus } from './ReviewStatus';

export class Review {

    id : number;
    text : String;
    rating : number;
    status : ReviewStatus;
    vehicleId : number;
    userId : number;
    date : Date;

}