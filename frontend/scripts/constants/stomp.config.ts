import { StompConfig } from '@stomp/ng2-stompjs';
import  { Chance } from 'chance';

export const stompConfig: StompConfig = {
  // url: 'ws://localhost:8090/matchmaking/websocket',
  url: 'ws://192.168.0.7:8090/matchmaking/websocket',
  headers: {
    username: new Chance().name()
  },
  heartbeat_in: 0,
  heartbeat_out: 20000,
  reconnect_delay: 5000,
  debug: true
};
