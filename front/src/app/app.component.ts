import { Component } from '@angular/core';
import { HttpErrorResponse } from '@angular/common/http';
import { firstValueFrom } from 'rxjs';

import { Heading, MissionService, Position } from './mission.service';

const MAX_SIZE = 15;
const STEP_DELAY_MS = 300;
const ROTATION: Record<Heading, number> = { N: 0, E: 90, S: 180, W: 270 };

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  readonly headings: Heading[] = ['N', 'E', 'S', 'W'];

  upperRightX = 5;
  upperRightY = 5;
  rows: number[] = [];
  columns: number[] = [];

  start: Position = { x: 1, y: 2, heading: 'N' };
  rovers: Position[] = [];
  instructions = '';

  fileInput = '';
  fileMode = false;
  error = '';
  busy = false;

  constructor(private missionService: MissionService) {
    this.updateGrid();
    this.deploy();
  }

  resize(dx: number, dy: number): void {
    this.upperRightX = Math.min(MAX_SIZE, Math.max(1, this.upperRightX + dx));
    this.upperRightY = Math.min(MAX_SIZE, Math.max(1, this.upperRightY + dy));
    this.start.x = Math.min(this.start.x, this.upperRightX);
    this.start.y = Math.min(this.start.y, this.upperRightY);
    this.updateGrid();
    this.deploy();
  }

  startIsValid(): boolean {
    return this.start.x >= 0 && this.start.x <= this.upperRightX
      && this.start.y >= 0 && this.start.y <= this.upperRightY;
  }

  deploy(): void {
    this.fileMode = false;
    this.instructions = '';
    this.error = '';
    this.rovers = [{ ...this.start }];
  }

  async send(instruction: 'L' | 'R' | 'M'): Promise<void> {
    await this.runInstructions(this.instructions + instruction);
  }

  async replay(): Promise<void> {
    const all = this.instructions;
    this.busy = true;
    this.rovers = [{ ...this.start }];
    for (let i = 1; i <= all.length; i++) {
      await this.delay(STEP_DELAY_MS);
      await this.runInstructions(all.substring(0, i));
    }
    this.busy = false;
  }

  async runFile(): Promise<void> {
    this.error = '';
    try {
      const response = await firstValueFrom(this.missionService.run(this.fileInput));
      this.upperRightX = response.plateau.upperRightX;
      this.upperRightY = response.plateau.upperRightY;
      this.updateGrid();
      this.fileMode = true;
      this.instructions = '';
      this.rovers = response.rovers;
    } catch (e) {
      this.showError(e);
    }
  }

  canMove(): boolean {
    const rover = this.rovers[0];
    switch (rover.heading) {
      case 'N':
        return rover.y < this.upperRightY;
      case 'S':
        return rover.y > 0;
      case 'E':
        return rover.x < this.upperRightX;
      case 'W':
        return rover.x > 0;
    }
  }

  roverAt(x: number, y: number): Position | undefined {
    return this.rovers.find(r => r.x === x && r.y === y);
  }

  rotation(heading: Heading): string {
    return `rotate(${ROTATION[heading]}deg)`;
  }

  private async runInstructions(instructions: string): Promise<void> {
    const input = `${this.upperRightX} ${this.upperRightY}\n`
      + `${this.start.x} ${this.start.y} ${this.start.heading}\n`
      + instructions;
    this.error = '';
    try {
      const response = await firstValueFrom(this.missionService.run(input));
      this.instructions = instructions;
      this.rovers = response.rovers;
    } catch (e) {
      this.showError(e);
    }
  }

  private showError(e: unknown): void {
    const error = e as HttpErrorResponse;
    this.error = typeof error.error === 'string' ? error.error : error.message;
  }

  private updateGrid(): void {
    this.columns = Array.from({ length: this.upperRightX + 1 }, (_, i) => i);
    this.rows = Array.from({ length: this.upperRightY + 1 }, (_, i) => this.upperRightY - i);
  }

  private delay(ms: number): Promise<void> {
    return new Promise(resolve => setTimeout(resolve, ms));
  }
}
