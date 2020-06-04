export class VehicleMainViewDTO{
    id : number;
    make : String;
    model : String;
    price : number;
    ownerUsername : String;

    constructor(id: number, make: String, model: String, price: number, ownerUsername: String){
        this.id = id;
        this.make = make;
        this.model = model;
        this.price = price;
        this.ownerUsername = ownerUsername;
    };
}