import { Image } from './Image';

export class Vehicle{
    id : number;

    mileage : number;
    mileageLimit : number;
    collisionProtection : boolean;
    childrenSeats : number;
    image : Image;
    startDate : Date;
    endDate : Date;

    fuelTypeId : number;
    styleId : number;
    transmissionId : number;
    modelId : number ;
    makeId : number ;
    locationId : number;
    userId : number;
}