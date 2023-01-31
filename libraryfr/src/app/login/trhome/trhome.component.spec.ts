import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TrhomeComponent } from './trhome.component';

describe('TrhomeComponent', () => {
  let component: TrhomeComponent;
  let fixture: ComponentFixture<TrhomeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TrhomeComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TrhomeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
