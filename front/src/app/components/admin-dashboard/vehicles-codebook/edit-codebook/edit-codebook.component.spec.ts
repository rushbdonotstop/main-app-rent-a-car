import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EditCodebookComponent } from './edit-codebook.component';

describe('EditCodebookComponent', () => {
  let component: EditCodebookComponent;
  let fixture: ComponentFixture<EditCodebookComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EditCodebookComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EditCodebookComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
