import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BookDeatilviewComponent } from './book-deatilview.component';

describe('BookDeatilviewComponent', () => {
  let component: BookDeatilviewComponent;
  let fixture: ComponentFixture<BookDeatilviewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BookDeatilviewComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BookDeatilviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
