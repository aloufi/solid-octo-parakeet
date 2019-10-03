import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {VacationComponent} from './vacation/vacation.component';

const routes: Routes = [
  {
    path: 'VacationRequest',
    component: VacationComponent,
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
