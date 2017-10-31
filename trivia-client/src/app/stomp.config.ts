import { StompConfig } from '@stomp/ng2-stompjs';

export const STOMP_CONFIG: StompConfig = {
  url: 'ws://localhost:8090/matchmaking/websocket',
  // url: 'ws://192.168.1.81:8090/matchmaking/websocket',
  
  headers: {
    username: "test-username"
  },
  heartbeat_in: 0,
  heartbeat_out: 20000,
  reconnect_delay: 5000,
  debug: true
};
