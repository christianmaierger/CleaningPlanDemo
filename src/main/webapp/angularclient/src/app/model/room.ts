import {User} from "./user";

//this is a DTO class
export class Room {
  // todo better default values or interface with optional fields
  // the user that is to clean the Room
  id!: number;
  name!: string;
  user?: User;
  userEdible: Boolean=false;



}
