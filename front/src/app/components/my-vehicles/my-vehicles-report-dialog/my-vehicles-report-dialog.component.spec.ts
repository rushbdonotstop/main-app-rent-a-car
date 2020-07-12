import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MyVehiclesReportDialogComponent } from './my-vehicles-report-dialog.component';

describe('ReportDialogComponent', () => {
  let component: MyVehiclesReportDialogComponent;
  let fixture: ComponentFixture<MyVehiclesReportDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MyVehiclesReportDialogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MyVehiclesReportDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});