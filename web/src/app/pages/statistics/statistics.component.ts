import {Component, OnInit} from '@angular/core';
import {Statistics} from "../../interfaces/statistics";
import {HttpClient} from "@angular/common/http";
import {environment} from "../../../environments/environment";

@Component({
  selector: 'app-statistics',
  templateUrl: './statistics.component.html',
  styleUrls: ['./statistics.component.css']
})
export class StatisticsComponent implements OnInit {

  statistics: Statistics = {} as Statistics;
  loaded: boolean = false;

  constructor(private http: HttpClient) {
  }

  ngOnInit(): void {
    this.loadStatistics();
  }


  loadStatistics() {
    this.loaded = false;
    this.http.get<Statistics>(environment.apiUrl + '/metrics')
      .subscribe((data: Statistics) => {
        this.statistics = data;
        this.loaded = true;
      }, error => {
        this.loaded = false;
      })
  }

}
