import { Observable } from 'rxjs/observable';
import { Observer } from 'rxjs/observer';
import { Subscription } from 'rxjs/Subscription';

export function stubableObservable(): Observable<any> {
  return Observable.create((observer: Observer<any>) => {});
}

export function stubableSubscription(): Subscription {
  return new Subscription();
}
