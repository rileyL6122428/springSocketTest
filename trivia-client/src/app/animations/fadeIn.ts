import { trigger, style, animate, transition } from '@angular/animations';

export function fadeIn(params: { delay: number, duration: number}) {
  return trigger('fadeIn', [
    transition(':enter', [
      style({ opacity: '0' }),
      animate(`${params.duration}s ${params.delay}s`, style({ opacity: 1 }))
    ]),
  ]);
}
