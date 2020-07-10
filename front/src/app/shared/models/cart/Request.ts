import { Status } from './Status'
import { NumberValueAccessor } from '@angular/forms'
import { VehicleMainViewDTO } from '../vehicle/VehicleMainViewDTO'
import { RequestAndVehicle } from './RequestAndVehicle'

export class Request{
    id:number
    totalCost:number
    startDate:Date
    endDate:Date
    status:Status
    userId:number
    vehicleId:number
    ownerId:number
    bundle:number

    constructor(details : RequestAndVehicle){
        this.vehicleId=details.vehicleId
        this.userId=details.ownerId
        this.startDate=details.startDate
        this.endDate=details.endDate
        this.ownerId=details.ownerId
        this.totalCost=details.totalCost
    }

}