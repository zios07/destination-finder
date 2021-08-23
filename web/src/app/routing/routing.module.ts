import {NgModule} from '@angular/core';
import {RouterModule, Routes} from "@angular/router";
import {AirportsSearchComponent} from "../pages/airports-search/airports-search.component";
import {FareCalculatorComponent} from "../pages/fare-calculator/fare-calculator.component";
import {StatisticsComponent} from "../pages/statistics/statistics.component";

export const routes: Routes = [
  {path: 'airports', component: AirportsSearchComponent},
  {path: 'fares', component: FareCalculatorComponent},
  {path: 'statistics', component: StatisticsComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class RoutingModule { }
