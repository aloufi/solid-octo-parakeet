import {Component, OnInit} from '@angular/core';
import {FormControl, Validators} from '@angular/forms';
import { VacationService } from './vacationService/vacation.service';
import {Employee} from './model/employee';
import {Vacation} from './model/vacation';
import {DepartmentManager} from './model/departmentmanager';
import {VacationRequest} from './model/vacation-request';
import {VacationResponse} from './model/vacationresponse';

@Component({
  selector: 'app-vacation',
  templateUrl: './vacation.component.html',
  styleUrls: ['./vacation.component.css']
})
export class VacationComponent implements OnInit {

  constructor(private apiService: VacationService) {}

  employeeNumber = new FormControl('', [Validators.required]);

  employee: Employee;
  manager: DepartmentManager;
  vacation: Vacation[] = [];
  request: VacationRequest;
  response: VacationResponse;
  isManager = false;
  displayedColumns: string[] = ['vacationNumber', 'employeeNumber', 'status', 'requestDate', 'responseDate', 'action'];
  status: string[] = ['APPROVED', 'REJECTED'];


  getErrorMessage() {
    return this.employeeNumber.hasError('required') ? 'You must enter a value' : '';
  }

  ngOnInit(): void {
  }



  getEmployee(id) {
    this.apiService.getEmployee(id.value).subscribe((data) => {
      if (data.departmentManager === null) {
        this.isManager = true;
      } else {
        this.isManager = false;
      }
      this.manager = data.departmentManager;
      this.employee = data.employee;

      if (!this.isManager) {
        const request = {
          employeeNumber: this.employee.employeeNumber,
          managerNumber: this.manager.employeeNumber
        };
        this.request = request;
      }
    });
    this.getVacation(id);
  }



  private getVacation(id) {
    this.apiService.getEmployeeVacations(id.value).subscribe((data) => {
      this.vacation = data;
    });
  }

  private responseToVacationRequest(event, requestId) {

    const response = {
      vacationNumber: requestId,
      status: event.value
    };

    this.response = response;

     this.apiService.sendManagerResponse(this.response).subscribe( () => {
       this.getVacation(this.employeeNumber);
     });
  }

  sendVacationRequest() {
    console.log(this.request);
    this.apiService.sendVacationRequest(this.request).subscribe(() => {
      this.getVacation(this.employeeNumber);
    });
  }
}
