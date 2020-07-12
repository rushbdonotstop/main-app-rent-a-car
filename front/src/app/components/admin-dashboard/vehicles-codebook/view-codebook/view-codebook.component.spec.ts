import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewCodebookComponent } from './view-codebook.component';

describe('ViewCodebookComponent', () => {
  let component: ViewCodebookComponent;
  let fixture: ComponentFixture<ViewCodebookComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ViewCodebookComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewCodebookComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
