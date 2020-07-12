import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CommentRequestsComponent } from './comment-requests.component';

describe('CommentRequestsComponent', () => {
  let component: CommentRequestsComponent;
  let fixture: ComponentFixture<CommentRequestsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CommentRequestsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CommentRequestsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
