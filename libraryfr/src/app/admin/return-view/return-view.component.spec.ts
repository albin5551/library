import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReturnViewComponent } from './return-view.component';

describe('ReturnViewComponent', () => {
  let component: ReturnViewComponent;
  let fixture: ComponentFixture<ReturnViewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ReturnViewComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ReturnViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
