import { Component, OnInit } from '@angular/core';
import { VehiculosService } from '../servicios/vehiculos.service';
import { Vehiculo } from '../modelos/vehiculo-modelo';
import { RegistroParqueo } from '../modelos/registro-parqueo.modelo';
import { Respuesta } from '../modelos/respuesta.modelo';
import { Recibo } from '../modelos/recibo.modelo';

@Component({
  selector: 'app-vehiculo-ingresar',
  templateUrl: './vehiculo-ingresar.component.html',
  styleUrls: ['./vehiculo-ingresar.component.css']
})
export class VehiculoIngresarComponent {

  vehiculo: Vehiculo = new Vehiculo();

  resultado: Respuesta;

  vehiculosParqueados: RegistroParqueo[];

  recibo: Recibo = new Recibo("", "", "", "");

  constructor(private vehiculoService: VehiculosService) {

  }

  registrar(): void {
    this.vehiculoService.postVehiculoParqueo(this.vehiculo).subscribe(
      (res) => {
        this.vehiculo = new Vehiculo();
        this.resultado = new Respuesta(res.msj, true);
        this.vehiculoService.getVehiculosParqueados().subscribe(res => this.vehiculosParqueados = res);
      }, (err) => {
        this.vehiculo = new Vehiculo();
        if (err.status == 0) {
          this.resultado = new Respuesta("No fue posible establecer conexion con el servidor", false);
        }
        else {
          this.resultado = new Respuesta(err.error.message, false);
        }
      });

  }
}
