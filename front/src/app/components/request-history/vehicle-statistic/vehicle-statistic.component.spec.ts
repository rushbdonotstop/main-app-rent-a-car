import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { VehicleStatisticComponent } from './vehicle-statistic.component';

describe('VehicleStatisticComponent', () => {
  let component: VehicleStatisticComponent;
  let fixture: ComponentFixture<VehicleStatisticComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ VehicleStatisticComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(VehicleStatisticComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
