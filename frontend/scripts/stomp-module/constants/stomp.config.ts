import { StompConfig } from '@stomp/ng2-stompjs';
import  { Chance } from 'chance';

export const STOMP_CONFIG: StompConfig = {
  // url: 'ws://localhost:8090/matchmaking/websocket',

  url: 'ws://192.168.0.7.:8090/matchmaking/websocket',
  headers: {
    // user: "test-user",
    username: "test-username"
  },
  heartbeat_in: 0,
  heartbeat_out: 20000,
  reconnect_delay: 5000,
  debug: true
};
