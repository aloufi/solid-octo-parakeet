import { Injectable } from '@angular/core';
import {HttpClient, HttpClientModule, HttpHeaders} from '@angular/common/http';
import {Employee} from '../model/employee';
import {Observable, throwError} from 'rxjs';
import {catchError, retry} from 'rxjs/operators';
import {Vacation} from '../model/vacation';
import {VacationRequest} from '../model/vacation-request';
import {VacationResponse} from '../model/vacationresponse';
@Injectable({
  providedIn: 'root'
})
export class VacationService {

  apiURL = 'http://localhost:8099';

  constructor(private httpClient: HttpClient) {}


  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  }

  getEmployee(id): Observable<any> {
    return this.httpClient.get<any>(this.apiURL + '/getEmployee/' + id)
      .pipe(
        retry(1),
        catchError(this.handleError)
      );
  }

  getEmployeeVacations(id): Observable<Vacation[]> {
    return this.httpClient.get<Vacation[]>(this.apiURL + '/VacationEmployeeDetails/' + id)
      .pipe(
        retry(1),
        catchError(this.handleError)
      );
  }

  sendVacationRequest(request: VacationRequest): Observable<VacationRequest> {
    return this.httpClient.post<VacationRequest>(this.apiURL + '/createNewVacationRequest' , request)
      .pipe(
        retry(1),
        catchError(this.handleError)
      );
  }

  sendManagerResponse(response: VacationResponse): Observable<VacationResponse> {
    return this.httpClient.put<VacationResponse>(this.apiURL + '/updateVacationRequest' , response)
      .pipe(
        retry(1),
        catchError(this.handleError)
      );
  }

  handleError(error) {
    let errorMessage = '';
    if (error.error instanceof ErrorEvent) {
      // Get client-side error
      errorMessage = error.error.message;
    } else {
      // Get server-side error
      errorMessage = `Error Code: ${error.status}\nMessage: ${error.message}`;
    }
    window.alert(errorMessage);
    return throwError(errorMessage);
  }
}
