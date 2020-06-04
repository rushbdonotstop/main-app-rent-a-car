import { Status } from './Status'
import { NumberValueAccessor } from '@angular/forms'
import { VehicleMainViewDTO } from '../vehicle/VehicleMainViewDTO'
import { RequestAndVehicle } from './RequestAndVehicle'

export class Request{
    id:number
    totalCost:number
    startDate:Date = new Date()
    endDate:Date = new Date()
    status:Status
    userId:number
    vehicleId:number
    ownerId:number = 1
    bundle:number


    constructor(details : RequestAndVehicle){
        this.vehicleId=details.vehicleId
        this.userId=details.ownerId
    }

}