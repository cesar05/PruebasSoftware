package co.com.ceiba.parqueadero.interfaces;

import co.com.ceiba.parqueadero.dominio.Vehiculo;

public interface IParqueo {
	boolean ingresar(Vehiculo v);
	boolean salir(Vehiculo v);
	boolean conRestricciones(Vehiculo v);
	boolean disponible(Vehiculo v);
}
