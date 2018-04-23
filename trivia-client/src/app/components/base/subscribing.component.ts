import { OnDestroy } from '@angular/core';

interface RemovableSubscription { unsubscribe(); }

export class SubscribingComponent implements OnDestroy {

  private _subscriptions: Array<RemovableSubscription>;

  constructor() {
    this._subscriptions = new Array<RemovableSubscription>();
  }

  protected set subscriptions(subscriptions: RemovableSubscription | Array<RemovableSubscription>) {
    if ( subscriptions instanceof Array) {
      this._subscriptions = subscriptions;
    } else {
      this._subscriptions.push(subscriptions);
    }
  }

  ngOnDestroy(): void {
    this._subscriptions.forEach((subscription: RemovableSubscription) => {
      subscription.unsubscribe();
    });
  }
}
