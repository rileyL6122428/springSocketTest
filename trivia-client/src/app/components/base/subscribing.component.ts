import { OnDestroy } from '@angular/core';
import { RemovableSubscription } from './removable-subscription';

export class SubscribingComponent implements OnDestroy {

  private _subscriptions: Array<RemovableSubscription>;

  constructor() {
    this._subscriptions = new Array<RemovableSubscription>();
  }

  set subscriptions(subs: RemovableSubscription) {
    this._subscriptions.push(subs);
  }

  protected addSubscriptions(...newSubscriptions: Array<RemovableSubscription>): void {
    newSubscriptions.forEach((newSubscription: RemovableSubscription) => {
      this.addSubscription(newSubscription);
    });
  }

  protected addSubscription(newSubscription: RemovableSubscription): void {
    this._subscriptions.push(newSubscription);
  }

  ngOnDestroy(): void {
    this._subscriptions.forEach((subscription: RemovableSubscription) => {
      subscription.unsubscribe();
    });
  }

}
