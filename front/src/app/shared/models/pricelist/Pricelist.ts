import { VehicleDiscount } from './VehicleDiscount';

export class Pricelist{
    id : number;
    startDate : Date;
    endDate : Date;
    price : number;
    priceByMile : number;
    priceCollision : number;
    vehicleId : number;
    vehicleDiscount : VehicleDiscount;
}