import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { RegistroParqueo } from '../modelos/registro-parqueo.modelo';
import { Vehiculo } from '../modelos/vehiculo-modelo';

@Injectable({
  providedIn: 'root'
})
export class VehiculosService {

  constructor(private http: HttpClient) { }

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  }

  uri = 'http://localhost:8080/parqueo/vehiculo';

  getVehiculosParqueados(): Observable<RegistroParqueo[]> {
    return this.http.get<RegistroParqueo[]>(this.uri, this.httpOptions);
  }

  postVehiculoParqueo(vehiculo: Vehiculo): Observable<any> {
    return this.http.post(this.uri, vehiculo, this.httpOptions);
  }

  putVehiculoParqueado(vehiculo: Vehiculo): Observable<any> {
    return this.http.put(this.uri, vehiculo, this.httpOptions);
  }

}
