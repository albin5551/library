import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewBookregComponent } from './view-bookreg.component';

describe('ViewBookregComponent', () => {
  let component: ViewBookregComponent;
  let fixture: ComponentFixture<ViewBookregComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ViewBookregComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ViewBookregComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
