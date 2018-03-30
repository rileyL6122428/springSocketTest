import { Subscription } from 'rxjs/Subscription';
import { stubableSubscription, stubableObservable } from './mocks';
import "jasmine";

export function watchForUnsubscription(jasmineSpy: any): Subscription {
  let mockSubscription = stubableSubscription(),
      mockObservable = stubableObservable();

  spyOn(mockSubscription, 'unsubscribe');
  spyOn(mockObservable, 'subscribe').and.returnValue(mockSubscription);
  jasmineSpy.and.returnValue(mockObservable);

  return mockSubscription;
}
