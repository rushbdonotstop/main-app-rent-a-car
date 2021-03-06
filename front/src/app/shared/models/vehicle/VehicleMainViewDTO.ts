export class VehicleMainViewDTO{
    id : number;
    make : String;
    model : String;
    price : number;
    ownerUsername : String;
    startDate : Date;
    endDate: Date
    ownerId: number
    mileage: number
    averageRating : number

    constructor(id: number, make: String, model: String, price: number, ownerUsername: String, ownerId: number){
        this.id = id;
        this.make = make;
        this.model = model;
        this.price = price;
        this.ownerUsername = ownerUsername;
        this.ownerId = ownerId
    };
}