import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MyVehiclesComponent } from './my-vehicles.component';


describe('MyVehicleComponent', () => {
    let component: MyVehiclesComponent;
    let fixture: ComponentFixture<MyVehiclesComponent>;

    beforeEach(async(()=> {
        TestBed.configureTestingModule({
            declarations: [MyVehiclesComponent]
        })
        .compileComponents();
    }));

    beforeEach(() => {
        fixture = TestBed.createComponent(MyVehiclesComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
