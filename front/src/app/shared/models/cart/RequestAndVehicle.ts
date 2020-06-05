import { Status } from './Status'
import { VehicleMainViewDTO } from '../vehicle/VehicleMainViewDTO'

export class RequestAndVehicle{
    id:number
    totalCost:number
    startDate:Date
    endDate:Date
    status:Status
    userId:number
    vehicleId:number
    ownerId:number
    bundle:number

    make : String;
    model : String;
    price : number;
    ownerUsername : String;

    constructor(vehicle:VehicleMainViewDTO){
        this.make = vehicle.make;
        this.model = vehicle.model;
        this.price = vehicle.price;
        this.ownerUsername = vehicle.ownerUsername;
        this.vehicleId = vehicle.id
        this.startDate=vehicle.startDate
        this.endDate=vehicle.endDate
    }
}