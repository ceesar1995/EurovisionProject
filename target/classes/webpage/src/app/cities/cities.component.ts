import {Component, OnInit, ViewChild} from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import {CitiesService} from "../services/CitiesService";
import {City} from "../model/City";
import {HttpCustomResponse} from "../model/HttpCustomResponse";
import {tap} from "rxjs/operators";

@Component({
  templateUrl: 'cities.component.html',
  styleUrls: ['cities.component.css']})
export class CitiesComponent implements OnInit{

  displayedColumns: string[] = ['id', 'name'];
  dataSource = new MatTableDataSource<City>();
  info : HttpCustomResponse;
  loaded: boolean= false;

  @ViewChild(MatPaginator) paginator: MatPaginator;


  constructor( private citiesService: CitiesService) { }
  ngOnInit() {

    this.getCitiesList(0,25);
    this.dataSource.paginator = this.paginator;
  }

  getCitiesList(page,size){
    this.citiesService.findCities(page,size).pipe().subscribe(data => {
      this.info = data;
      this.dataSource = new MatTableDataSource<City>(data.content);
      this.loaded = true;
    });
  }

  ngAfterViewInit() {
    this.paginator.page
      .pipe(
        tap(() => this.loadCitiesPage())
      )
      .subscribe();
  }

  loadCitiesPage() {
    this.loaded=false;
    this.getCitiesList(
      this.paginator.pageIndex,
      this.paginator.pageSize);
  }

  runAlgorithm(){
    this.loaded = false;
    this.citiesService.runAlgorithm().pipe().subscribe(data => {
      console.log(data);
      this.info.totalElements = data.length;
      this.paginator.pageSize = data.length;
      this.paginator.pageIndex = 0;
      this.dataSource = new MatTableDataSource<City>(data);
      this.loaded = true;
    });  }

}

