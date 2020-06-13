import { Status } from './Status'

export class manualRequest{
    id:number
    totalCost:number
    startDate:Date
    endDate:Date
    status:Status
    userId:number
    vehicleId:number
    ownerId:number = 1
    constructor(){}
}