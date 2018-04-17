import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'pokeball-two',
  templateUrl: './pokeball-two.component.html',
  styleUrls: ['./pokeball-two.component.scss']
})
export class PokeballTwoComponent {

  private isOpen: boolean;

  private baselineX: number;
  private baselineY: number;

  private topOpeningHeight: number;
  private topOpeningHeightDelta: number;

  private bottomOpeningHeight: number;
  private bottomOpeningHeightDelta: number;

  private buttonHeight: number;
  private buttonHeightDelta: number;

  private buttonYRadius: number;
  private buttonYRadiusDelta: number;

  private radiusX: number;
  private radiusY: number;

  private height: number;
  private width: number;

  private _strokeWidth: number;

  constructor() {
    this.baselineX = 110;
    this.baselineY = 120;
    this.topOpeningHeight = this.baselineY;
    this.bottomOpeningHeight = this.baselineY;
    this.buttonHeight = this.baselineY;
    this.buttonYRadius = 25;

    this.topOpeningHeightDelta = .8;
    this.bottomOpeningHeightDelta = 1.15;
    this.buttonHeightDelta = .82;
    this.buttonYRadiusDelta = .09;

    this._strokeWidth = 5;
    this.radiusX = 100;
    this.radiusY = 150;

    this.width = 220;
    this.height = 240;
  }

  animate(): void {
    (this.isOpen) ? this.animateClose() : this.animateOpen();
    this.isOpen = !this.isOpen;
  }

  get outerCircleTopD(): string {
    return `
        M
          ${this.baselineX - this.radiusX} ${this.baselineY}
        C
          ${this.baselineX - this.radiusX} ${this.baselineY - this.radiusY}
          ${this.baselineX + this.radiusX} ${this.baselineY - this.radiusY}
          ${this.baselineX + this.radiusX} ${this.baselineY}
      `;
  }

  get outerCircleBottomD(): string {
    return `
      M
        ${this.baselineX - this.radiusX} ${this.baselineY}
      C
        ${this.baselineX - this.radiusX} ${this.baselineY + this.radiusY}
        ${this.baselineX + this.radiusX} ${this.baselineY + this.radiusY}
        ${this.baselineX + this.radiusX} ${this.baselineY}
    `;
  }

  get innerCircleTopD(): string {
    return `
      M
        ${this.baselineX - this.radiusX} ${this.baselineY}
      C
        ${this.baselineX - this.radiusX} ${this.topOpeningHeight}
        ${this.baselineX + this.radiusX} ${this.topOpeningHeight}
        ${this.baselineX + this.radiusX} ${this.baselineY}
    `;
  }

  get innerCircleBottomD(): string {
    return `
      M
        ${this.baselineX - this.radiusX} ${this.baselineY}
      C
        ${this.baselineX - this.radiusX} ${this.bottomOpeningHeight}
        ${this.baselineX + this.radiusX} ${this.bottomOpeningHeight}
        ${this.baselineX + this.radiusX} ${this.baselineY}
    `;
  }

  get buttonRadiusY(): string {
    return `${this.buttonYRadius}`;
  }

  get buttonY(): string {
    return `${this.buttonHeight}`;
  }

  get strokeWidth(): string {
    return `${this._strokeWidth}`;
  }

  private animateOpen(): void {
    const openIntervalId = setInterval(() => {
      if (this.bottomOpeningHeight > this.baselineY - 100) {
        this.open();
      } else {
        clearInterval(openIntervalId);
      }
    }, 5);
  }

  private animateClose(): void {
    const closeIntervalId = setInterval(() => {
      if (this.bottomOpeningHeight !== this.baselineY) {
        this.close();
      } else {
        clearInterval(closeIntervalId);
      }
    }, 4);
  }

  open(): void {
    this.topOpeningHeight += this.topOpeningHeightDelta;
    this.bottomOpeningHeight -= this.bottomOpeningHeightDelta;
    this.buttonHeight -= this.buttonHeightDelta;
    this.buttonYRadius -= this.buttonYRadiusDelta;
  }

  close(): void {
    this.topOpeningHeight -= this.topOpeningHeightDelta;
    this.bottomOpeningHeight += this.bottomOpeningHeightDelta;
    this.buttonHeight += this.buttonHeightDelta;
    this.buttonYRadius += this.buttonYRadiusDelta;
  }

}
