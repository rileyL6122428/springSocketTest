import { OnDestroy } from '@angular/core';
import { Subscription } from 'rxjs/Subscription';

export class SubscribingComponent implements OnDestroy {

  private subscriptions: Array<Subscription>;

  constructor() {
    this.subscriptions = new Array<Subscription>();
  }

  protected addSubscriptions(...newSubscriptions: Array<Subscription>): void {
    newSubscriptions.forEach((newSubscription: Subscription) => {
      this.addSubscription(newSubscription);
    });
  }

  protected addSubscription(newSubscription: Subscription): void {
    this.subscriptions.push(newSubscription);
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach((subscription: Subscription) => {
      subscription.unsubscribe();
    });
  }

}
