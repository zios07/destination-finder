import {AfterViewInit, Component, ViewChild} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../../environments/environment";
import {Location} from "../../interfaces/location";
import {MatTableDataSource} from "@angular/material/table";
import {MatSort} from "@angular/material/sort";
import {MatPaginator, PageEvent} from "@angular/material/paginator";

@Component({
  selector: 'app-airports-search',
  templateUrl: './airports-search.component.html',
  styleUrls: ['./airports-search.component.css']
})
export class AirportsSearchComponent implements AfterViewInit {

  airports: Location[] = [];
  error: string = "";
  displayedColumns: string[] = ['name', 'code', 'description'];
  // @ts-ignore
  dataSource;

  page: number = 0;
  size: number = 10;
  totalPages: number = 0;
  totalElements: number = 0;
  term: string = '';

  loaded: boolean = false;

  // @ts-ignore
  @ViewChild(MatSort) sort: MatSort;
  // @ts-ignore
  @ViewChild(MatPaginator) paginator: MatPaginator;

  ngAfterViewInit() {
    this.loadAirports();
  }

  constructor(private http: HttpClient) {
  }

  loadAirports() {
    this.loaded = false;
    this.http.get<Location[]>(environment.apiUrl + '/airports?page=' + this.page + '&size=' + this.size + '&term=' + this.term)
      .subscribe((data: any) => {
        if (data._embedded && data._embedded.locations) {
          this.dataSource = new MatTableDataSource(data._embedded.locations);
          this.dataSource.sort = this.sort;
          this.dataSource.paginator = this.paginator;
        } else {
          this.dataSource = new MatTableDataSource([]);
        }

        if (data.page) {
          this.totalPages = data.page.totalPages;
          this.totalElements = data.page.totalElements;
        } else {
          this.totalPages = 0;
          this.totalElements = 0;
        }

        this.loaded = true;
      }, error => {
        this.error = error;
        this.loaded = false;
      });
  }

  pageChanged(event: PageEvent) {
    console.log(event);
    this.page = event.pageIndex;
    this.loadAirports();

  }
}
