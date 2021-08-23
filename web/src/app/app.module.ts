import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppComponent} from './app.component';
import {HttpClientModule} from "@angular/common/http";
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {TypeaheadModule} from 'ngx-bootstrap/typeahead';
import {FormsModule} from "@angular/forms";
import {FareCalculatorComponent} from './pages/fare-calculator/fare-calculator.component';
import {AirportsSearchComponent} from './pages/airports-search/airports-search.component';
import {StatisticsComponent} from './pages/statistics/statistics.component';
import {RoutingModule} from "./routing/routing.module";
import {NavbarComponent} from './shared/navbar/navbar.component';
import {MatTableModule} from "@angular/material/table";
import {MatSortModule} from "@angular/material/sort";
import {MatPaginatorModule} from "@angular/material/paginator";

@NgModule({
  declarations: [
    AppComponent,
    FareCalculatorComponent,
    AirportsSearchComponent,
    StatisticsComponent,
    NavbarComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    RoutingModule,
    BrowserAnimationsModule,
    TypeaheadModule.forRoot(),
    FormsModule,
    MatTableModule,
    MatSortModule,
    MatPaginatorModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
