import { Component, Output, EventEmitter } from '@angular/core';
import { PokeballSVG } from './pokeball.svg';

@Component({
  selector: 'pokeball',
  templateUrl: './pokeball.component.html',
  styleUrls: ['./pokeball.component.scss']
})
export class PokeballComponent {

  @Output() selection: EventEmitter<void> = new EventEmitter<void>();

  private pokeballSVG: PokeballSVG;

  constructor() {
    this.pokeballSVG = new PokeballSVG();
  }

  animate(): void {
    (this.pokeballSVG.isOpen) ? this.animateClose() : this.animateOpen();
    this.pokeballSVG.isOpen = !this.pokeballSVG.isOpen;
  }

  get pokeballTopPathCoordinates(): string {
    return this.pokeballSVG.circleTopD;
  }

  get pokeballBottomPathCoordinates(): string {
    return this.pokeballSVG.circleBottomD;
  }

  get pokeballButtonRadiusY(): string {
    return this.pokeballSVG.buttonRadiusY;
  }

  get pokeballButtonY(): string {
    return this.pokeballSVG.buttonY;
  }

  get strokeWidth(): string {
    return this.pokeballSVG.strokeWidth;
  }

  private animateOpen(): void {
    const openIntervalId = setInterval(() => {
      if (this.pokeballSVG.isFullyOpen()) {
        clearInterval(openIntervalId);
      } else {
        this.pokeballSVG.open();
      }
    }, 5);

    setTimeout(() => this.animateClose(), 1200);
  }

  private animateClose(): void {
    const closeIntervalId = setInterval(() => {
      if (this.pokeballSVG.isFullyClosed()) {
        clearInterval(closeIntervalId);
      } else {
        this.pokeballSVG.close();
      }
    }, 4);
  }

  get canvasHeight(): string {
    return this.pokeballSVG.canvasHeight;
  }

  get canvasWidth(): string {
    return this.pokeballSVG.canvasWidth;
  }

}
