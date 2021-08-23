import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AirportsSearchComponent } from './airports-search.component';

describe('AirportsSearchComponent', () => {
  let component: AirportsSearchComponent;
  let fixture: ComponentFixture<AirportsSearchComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AirportsSearchComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AirportsSearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
