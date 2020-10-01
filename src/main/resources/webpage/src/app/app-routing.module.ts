import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { CitiesComponent } from './cities';
import { HomeComponent } from './home';

const routes: Routes = [
  { path: 'cities', component: CitiesComponent },
  { path: '', component: HomeComponent },
  { path: '**', redirectTo: '' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
