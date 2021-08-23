import {Location} from "./location";

export interface Fare {

  amount: number;
  currency: string;
  origin: Location;
  destination: Location;

}
