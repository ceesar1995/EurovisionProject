import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { MatPaginatorModule} from "@angular/material/paginator";
import { MatInputModule} from "@angular/material/input";
import { MatProgressSpinnerModule} from "@angular/material/progress-spinner";
import {MatSortModule } from "@angular/material/sort";
import { MatTableModule } from "@angular/material/table";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {HttpClientModule} from "@angular/common/http";
import { MatButtonModule } from '@angular/material/button';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CitiesComponent } from './cities';
import { HomeComponent } from './home';
import {CitiesService} from "./services/CitiesService";



@NgModule({
  declarations: [
    AppComponent,
    CitiesComponent,
    HomeComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    HttpClientModule,
    MatInputModule,
    MatTableModule,
    MatPaginatorModule,
    MatSortModule,
    MatProgressSpinnerModule,
    MatButtonModule
  ],
  providers: [CitiesService],
  bootstrap: [AppComponent]
})
export class AppModule { }
