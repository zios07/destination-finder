import {Component, OnInit} from '@angular/core';
import {Fare} from "../../interfaces/fare";
import {Observable, Subscriber} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {map, mergeMap} from "rxjs/operators";
import {environment} from "../../../environments/environment";
import {Location} from "../../interfaces/location";

@Component({
  selector: 'app-fare-calculator',
  templateUrl: './fare-calculator.component.html',
  styleUrls: ['./fare-calculator.component.css']
})
export class FareCalculatorComponent implements OnInit {

  error: boolean = false;
  loaded: boolean = false;

  fares: Fare = {} as Fare;

  origin: string = '';
  originCode: string = '';
  destination: string = '';
  destinationCode: string = '';

  originDataSource: Observable<Location[]>;
  destinationDataSource: Observable<Location[]>;

  originTypeaheadLoading: boolean = false;
  destinationTypeaheadLoading: boolean = false;

  constructor(private http: HttpClient) {
    this.originDataSource = new Observable((observer: Subscriber<string>) => {
      // Runs on every search
      observer.next(this.origin);
    })
      .pipe(
        mergeMap((token: string) => this.getLocations(token))
      );

    this.destinationDataSource = new Observable((observer: Subscriber<string>) => {
      // Runs on every search
      observer.next(this.destination);
    })
      .pipe(
        mergeMap((token: string) => this.getLocations(token))
      );
  }

  getLocations(token: string): Observable<Location[]> {
    return this.http.get<any>(environment.apiUrl + '/airports?term=' + token).pipe(map(item => item._embedded.locations));
  }

  calculateFares() {
    this.error = false;
    this.http.get<Fare>(environment.apiUrl + '/fares/' + this.originCode + '/' + this.destinationCode).subscribe(fares => {
      this.fares = fares;
      this.loaded = true;
    }, error => {
      this.loaded = false;
      this.error = true;
    });
  }

  originSelected(val: any) {
    if (val && val.item) {
      this.originCode = val.item.code
    }
  }

  destinationSelected(val: any) {
    if (val && val.item) {
      this.destinationCode = val.item.code
    }
  }

  changeOriginTypeaheadLoading(e: boolean): void {
    this.originTypeaheadLoading = e;
  }

  changeDestinationTypeaheadLoading(e: boolean): void {
    this.destinationTypeaheadLoading = e;
  }

  ngOnInit(): void {
  }

}
