import { Component, ViewChild, OnInit, Input } from '@angular/core';
import { VehiculosComponent } from './vehiculos/vehiculos.component';
import { VehiculoIngresarComponent } from './vehiculo-ingresar/vehiculo-ingresar.component';
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'Ceiba Software House';

  ngOnInit() {
  }
}
