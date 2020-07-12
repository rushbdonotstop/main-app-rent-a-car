import { RequestDTO } from './requestDTO';

export class BundleDTO {
    id : number;
    totalCost : number;
    requestsList : RequestDTO[];
    username : String;
}