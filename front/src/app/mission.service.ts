import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export type Heading = 'N' | 'E' | 'S' | 'W';

export interface Position {
  x: number;
  y: number;
  heading: Heading;
}

export interface MissionResponse {
  plateau: { upperRightX: number; upperRightY: number };
  rovers: Position[];
}

@Injectable({ providedIn: 'root' })
export class MissionService {

  constructor(private http: HttpClient) {}

  run(input: string): Observable<MissionResponse> {
    return this.http.post<MissionResponse>('/missions', input, { headers: { 'Content-Type': 'text/plain' } });
  }
}
