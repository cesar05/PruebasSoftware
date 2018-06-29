import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { AppComponent } from './app.component';
import { VehiculosComponent } from './vehiculos/vehiculos.component';
import { VehiculosService } from './servicios/vehiculos.service';
import { VehiculoIngresarComponent } from './vehiculo-ingresar/vehiculo-ingresar.component';
import { VehiculoReciboComponent } from './vehiculo-recibo/vehiculo-recibo.component';
import { FilterVehiculo } from './pipe/filter-vehiculos.pipe';

@NgModule({
  declarations: [
    AppComponent,
    VehiculosComponent,
    VehiculoIngresarComponent,
    VehiculoReciboComponent,
    FilterVehiculo
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [VehiculosService],
  bootstrap: [AppComponent]
})
export class AppModule { }
