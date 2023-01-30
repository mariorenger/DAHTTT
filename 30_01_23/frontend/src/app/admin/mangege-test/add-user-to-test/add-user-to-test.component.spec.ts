import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddUserToTestComponent } from './add-user-to-test.component';

describe('AddUserToTestComponent', () => {
  let component: AddUserToTestComponent;
  let fixture: ComponentFixture<AddUserToTestComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddUserToTestComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddUserToTestComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
