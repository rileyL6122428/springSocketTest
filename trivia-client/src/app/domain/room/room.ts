import { RoomMessage } from '../chat-room-message/room-message';
import { User } from '../user/user';
import { Chat } from '../chat/chat';
import { Pokemon } from '../pokemon/pokemon';

export class Room {

  constructor(params) {
    this.name = params['name'];
    this.maxNumberOfUsers = params['maxNumberOfUsers'];
    // this.messages = params['messages'];
    this.chat = params['chat'];
    this.users = params['users'];
    // this.mascot = new Pokemon("Charizard");
    // this.mascot = new Pokemon('Rayquaza');
    // this.mascot = new Pokemon('Lugia');
    // this.mascot = new Pokemon('Eevee');
    // this.mascot = new Pokemon('Blaziken');
    // this.mascot = new Pokemon('Gengar');
    // this.mascot = new Pokemon('Pikachu');
    // this.mascot = new Pokemon('Lucario');
    // this.mascot = new Pokemon('Mewtwo');
    this.mascot = new Pokemon('Garchomp');
  }
  readonly mascot: Pokemon;
  readonly name: string;
  readonly maxNumberOfUsers: number;
  // readonly messages: Array<RoomMessage>;
  readonly chat: Chat;
  readonly users: Map<string, User>;

}
