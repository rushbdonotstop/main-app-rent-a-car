import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewVehicleReviewsComponent } from './view-vehicle-reviews.component';

describe('ViewVehicleReviewsComponent', () => {
  let component: ViewVehicleReviewsComponent;
  let fixture: ComponentFixture<ViewVehicleReviewsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ViewVehicleReviewsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewVehicleReviewsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
