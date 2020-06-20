export class Report {
    id: number
    mileage: number
    additionalInfo: String
    vehicleId: number
    userId: number

    constructor(mileage: number, addI: String, vId: number, userId: number) {
        this.mileage = mileage
        this.additionalInfo = addI
        this.vehicleId = vId
        this.userId = userId
    }
}