export class Report {
    id: number
    mileage: number
    additionalInfo: String
    vehicleId: number
    userId: number
    startDate: Date
    endDate: Date

    constructor(mileage: number, addI: String, vId: number, userId: number, startDate: Date, endDate: Date) {
        this.mileage = mileage
        this.additionalInfo = addI
        this.vehicleId = vId
        this.userId = userId
        this.startDate = startDate
        this.endDate = endDate
    }
}