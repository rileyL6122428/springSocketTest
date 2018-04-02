import { Subscription } from 'rxjs/Subscription';

export class StreamSubscription {

  constructor(
    private compositeSubscriptions: Array<Subscription>
  ) { }

  unsubscribe(): void {
    this.compositeSubscriptions.forEach((subscription: Subscription) => {
      subscription.unsubscribe();
    });
  }

}
