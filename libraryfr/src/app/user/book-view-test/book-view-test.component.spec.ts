import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BookViewTestComponent } from './book-view-test.component';

describe('BookViewTestComponent', () => {
  let component: BookViewTestComponent;
  let fixture: ComponentFixture<BookViewTestComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BookViewTestComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BookViewTestComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
