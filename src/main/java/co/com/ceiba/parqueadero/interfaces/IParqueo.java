package co.com.ceiba.parqueadero.interfaces;

import org.joda.time.DateTime;

import co.com.ceiba.parqueadero.dominio.Vehiculo;

public interface IParqueo {
	boolean ingresar(Vehiculo v);
	double registrarSalida(Vehiculo v);
	boolean sinRestricciones(Vehiculo v,int dia);
	boolean disponible(Vehiculo v);
	void fechaActul(DateTime fechaActual);
}