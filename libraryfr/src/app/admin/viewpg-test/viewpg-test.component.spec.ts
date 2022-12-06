import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewpgTestComponent } from './viewpg-test.component';

describe('ViewpgTestComponent', () => {
  let component: ViewpgTestComponent;
  let fixture: ComponentFixture<ViewpgTestComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ViewpgTestComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ViewpgTestComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
