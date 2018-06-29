import { Component, OnInit, Input } from '@angular/core';
import { Recibo } from '../modelos/recibo.modelo';

@Component({
  selector: 'app-vehiculo-recibo',
  templateUrl: './vehiculo-recibo.component.html',
  styleUrls: ['./vehiculo-recibo.component.css']
})
export class VehiculoReciboComponent implements OnInit {
  @Input() recibo: Recibo;

  constructor() {

  }

  ngOnInit() {
  }

}
