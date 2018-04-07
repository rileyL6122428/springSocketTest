import { environment } from '../../environments/environment';

export const STOMP_CONFIG = {
  url: environment.webSockets.ADDRESS,
  heartbeat_in: 0,
  heartbeat_out: 20000,
  reconnect_delay: 5000,
  debug: true
};
