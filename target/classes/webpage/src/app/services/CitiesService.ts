import {Injectable} from "@angular/core";
import {HttpClient, HttpParams} from "@angular/common/http";
import {Observable} from "rxjs";
import {HttpCustomResponse} from "../model/HttpCustomResponse";

@Injectable()
export class CitiesService {

  constructor(private http:HttpClient) {}

  findCities(
    pageNumber = 0, pageSize = 3) : Observable<HttpCustomResponse>{

    return this.http.get<HttpCustomResponse>('http://localhost:8080/cities/queryByPage', {
      params: new HttpParams()
        .set('page', pageNumber.toString())
        .set('size', pageSize.toString())
    })
  }

  runAlgorithm() : Observable<any>{
    return this.http.get<any>('http://localhost:8080/cities/runAlgorithm', {
    })
  }
}
