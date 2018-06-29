import { Component, Input, Output } from '@angular/core';
import { VehiculosService } from '../servicios/vehiculos.service';
import { RegistroParqueo } from '../modelos/registro-parqueo.modelo';
import { Recibo } from '../modelos/recibo.modelo';

@Component({
  selector: 'app-vehiculos',
  templateUrl: './vehiculos.component.html',
  styleUrls: ['./vehiculos.component.css']
})
export class VehiculosComponent {
  @Input() vehiculosParqueados: RegistroParqueo[];
  @Input() recibo: Recibo;

  result: any;
  filter: string;

  constructor(private vehiculoService: VehiculosService) {
    this.getVehiculosParqueados();
  }

  getVehiculosParqueados(): void {
    this.vehiculoService.getVehiculosParqueados().subscribe(res => this.vehiculosParqueados = res);
  }

  ngOnInit() {
  }

  registrarSalida(registro: any): void {
    if (confirm("Esta seguro de registrar la salida del vehiculo con placas " + registro.placa)) {
      this.vehiculoService.putVehiculoParqueado(registro).subscribe((res) => {
        this.result = res;
        this.recibo.placa = registro.placa;
        this.recibo.cilindraje = registro.cilindraje;
        this.recibo.fechaEntrada = registro.fechaIngreso;
        this.recibo.valorPagar = res;
        this.getVehiculosParqueados();
      });
    }
  }

}
