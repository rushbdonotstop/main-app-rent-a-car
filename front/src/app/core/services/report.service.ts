import { Injectable } from '@angular/core';
import { HttpClient, HttpHandler, HttpHeaders } from '@angular/common/http';
import { Report } from 'src/app/shared/models/report/Report';
import { identifierModuleUrl } from '@angular/compiler';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json' 
  })
}

@Injectable({
  providedIn: 'root'
})
export class ReportService {

  constructor(private http: HttpClient) { }

  getAllFromVehicle(vehicleId: number) {
    return this.http.get<Report[]>('server/request/report/vehicle/'+vehicleId, httpOptions);
  }

  sendReport(report:Report){
    return this.http.post<Report>('server/request/report',report,httpOptions);
  }
}
