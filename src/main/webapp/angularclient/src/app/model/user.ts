

export class User {
   private _id: string = '';
   private _name: string = '';
   private _email: string = '';


  get id(): string {
    return this._id;
  }

  get name(): string {
    return this._name;
  }

  get email(): string {
    return this._email;
  }


  set id(value: string) {
    this._id = value;
  }

  set name(value: string) {
    this._name = value;
  }

  set email(value: string) {
    this._email = value;
  }
}
