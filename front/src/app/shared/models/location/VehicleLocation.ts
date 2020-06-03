import { State } from './State';
import { City } from './City';
import { Street } from './Street';

export class VehicleLocation {
    id : number;
    state : State;
    city : City;
    street : Street;
}