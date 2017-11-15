import { Observable } from 'rxjs/Observable';
import { Observer } from 'rxjs/Observer';
import { Subscription } from 'rxjs/Subscription';
import { BehaviorSubject } from 'rxjs/BehaviorSubject';
import { StompService, StompState } from '@stomp/ng2-stompjs';
import { StompHeaders } from '@stomp/stompjs';
import { Message } from '@stomp/stompjs';

export function stubableObservable(): Observable<any> {
  return Observable.create((observer: Observer<any>) => {});
}

export function stubableSubscription(): Subscription {
  return new Subscription();
}

export class StubableStompService {
    public subscribe(queueName: string, headers: StompHeaders = {}): Observable<Message> {
      return null;
    }

}
