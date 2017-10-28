import { OnDestroy } from '@angular/core';
import { Subscription } from 'rxjs/Subscription';

export class SubscribingComponentBase implements OnDestroy {

  protected subscriptions: Array<Subscription>;

  protected setSubscriptions(subscriptions: Array<Subscription>): void {
    this.subscriptions = subscriptions;
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach((subscription: Subscription) => {
      subscription.unsubscribe();
    });
  }

}
