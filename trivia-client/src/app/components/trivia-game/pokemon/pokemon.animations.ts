import { trigger, state, style, animate, transition } from '@angular/animations';

export const pokemonAnimations = [
  trigger('capture', [
    state('attempting', style({
      'height': '40px',
      'width': '40px',
      'border-radius': '100%',
      'left': 'calc(50% - 23px)',
      'top': '170px',
      'z-index': '-1'
    })),
    state('not-attempting', style({})),
    transition('not-attempting => attempting', [
      animate('500ms ease-out', style({
        'height': '40px',
        'width': '40px',
        'border-radius': '100%',
        'left': 'calc(50% - 23px)'
      })),
      animate('500ms 100ms ease-in-out', style({ 'top': '170px' })),
      animate('1ms', style({ 'z-index': '-1' }))
    ]),
  ])
];
