import { Observer, Observable, Subscription } from 'rxjs';

export class BaseDispatcher<T> {

  private observer: Observer<T>;
  private observable: Observable<T>;

  constructor() {
    this.observable = new Observable<T>((observer: Observer<T>) => {
      this.observer = observer;
    });
  }

  dispatch(payload: T): void {
    this.observer.next(payload);
  }

  subscribe(subConfig: {
    next?: (T) => void,
    error?: (T) => void,
    complete?: () => void
  }): Subscription {
    let next = subConfig.next ? subConfig.next : (param: T) => {};
    let error = subConfig.error ? subConfig.error: (params: T) => {};
    let complete = subConfig.complete ? subConfig.complete : () => {};

    return this.observable.subscribe(next, error, complete);
  }

}
