import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { VehiculoIngresarComponent } from './vehiculo-ingresar.component';

describe('VehiculoIngresarComponent', () => {
  let component: VehiculoIngresarComponent;
  let fixture: ComponentFixture<VehiculoIngresarComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ VehiculoIngresarComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(VehiculoIngresarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
