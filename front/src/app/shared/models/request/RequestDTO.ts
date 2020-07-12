import { RequestStatus } from './RequestStatus';

export class RequestDTO {
    id : number;
    totalCost : number;
    startDate : Date;
    endDate : Date;
    status : RequestStatus;
    username : String;
    makePlusModel : String;
    vehicleId : number;
    bundleId : number;
    viewMode: boolean;
}