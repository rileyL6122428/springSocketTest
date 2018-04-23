export class PokeballSVG {

  isOpen: boolean;

  baselineX: number;
  baselineY: number;

  topOpeningHeight: number;
  topOpeningHeightDelta: number;

  bottomOpeningHeight: number;
  bottomOpeningHeightDelta: number;

  buttonHeight: number;
  buttonHeightDelta: number;

  buttonYRadius: number;
  buttonYRadiusDelta: number;

  radiusX: number;
  radiusY: number;

  height: number;
  width: number;

  _strokeWidth: number;

  constructor() {
    this.baselineX = 55;
    this.baselineY = 60;
    this.topOpeningHeight = this.baselineY;
    this.bottomOpeningHeight = this.baselineY;
    this.buttonHeight = this.baselineY;
    this.buttonYRadius = 12.5;

    this.topOpeningHeightDelta = .4;
    this.bottomOpeningHeightDelta = .575;
    this.buttonHeightDelta = .41;
    this.buttonYRadiusDelta = .045;

    this._strokeWidth = 5;
    this.radiusX = 50;
    this.radiusY = 75;

    this.width = 110;
    this.height = 120;
  }

  get circleTopD(): string {
    return `
      M
          ${this.baselineX - this.radiusX} ${this.baselineY}
        C
          ${this.baselineX - this.radiusX} ${this.baselineY - this.radiusY}
          ${this.baselineX + this.radiusX} ${this.baselineY - this.radiusY}
          ${this.baselineX + this.radiusX} ${this.baselineY}

        C
          ${this.baselineX + this.radiusX} ${this.bottomOpeningHeight}
          ${this.baselineX - this.radiusX} ${this.bottomOpeningHeight}
          ${this.baselineX - this.radiusX} ${this.baselineY}
          `;
  }

  get circleBottomD(): string {
    return `
      M
        5 60
      C
        ${this.baselineX - this.radiusX} ${this.baselineY + this.radiusY}
        ${this.baselineX + this.radiusX} ${this.baselineY + this.radiusY}
        ${this.baselineX + this.radiusX} ${this.baselineY}
      C
        ${this.baselineX + this.radiusX} ${this.topOpeningHeight}
        ${this.baselineX - this.radiusX} ${this.topOpeningHeight}
        ${this.baselineX - this.radiusX} ${this.baselineY}
    `;
  }

  open(): void {
    this.topOpeningHeight += this.topOpeningHeightDelta;
    this.bottomOpeningHeight -= this.bottomOpeningHeightDelta;
    this.buttonHeight -= this.buttonHeightDelta;
    this.buttonYRadius -= this.buttonYRadiusDelta;
  }

  isFullyOpen(): boolean {
    return this.bottomOpeningHeight <= this.baselineY - 50;
  }

  close(): void {
    this.topOpeningHeight -= this.topOpeningHeightDelta;
    this.bottomOpeningHeight += this.bottomOpeningHeightDelta;
    this.buttonHeight += this.buttonHeightDelta;
    this.buttonYRadius += this.buttonYRadiusDelta;
  }

  isFullyClosed(): boolean {
    return this.bottomOpeningHeight === this.baselineY;
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

  get canvasHeight(): string {
    return `${this.height}`;
  }

  get canvasWidth(): string {
    return `${this.width}`;
  }

}
