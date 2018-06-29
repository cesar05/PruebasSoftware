import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { VehiculoReciboComponent } from './vehiculo-recibo.component';

describe('VehiculoReciboComponent', () => {
  let component: VehiculoReciboComponent;
  let fixture: ComponentFixture<VehiculoReciboComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ VehiculoReciboComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(VehiculoReciboComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
