import { Subscription } from 'rxjs/subscription';
import { stubableSubscription, stubableObservable } from './mocks';

export function watchForUnsubscription(jasmineSpy: any): Subscription {
  let mockSubscription = stubableSubscription(),
      mockObservable = stubableObservable();

  spyOn(mockSubscription, 'unsubscribe');
  spyOn(mockObservable, 'subscribe').and.returnValue(mockSubscription);
  jasmineSpy.and.returnValue(mockObservable);

  return mockSubscription;
}
