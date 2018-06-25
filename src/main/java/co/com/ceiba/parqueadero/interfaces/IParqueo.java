package co.com.ceiba.parqueadero.interfaces;

import java.util.List;

import org.joda.time.DateTime;

import co.com.ceiba.parqueadero.dominio.Parqueo;
import co.com.ceiba.parqueadero.dominio.Vehiculo;

public interface IParqueo {
	boolean ingresar(Vehiculo v);
	double registrarSalida(Vehiculo v);
	boolean sinRestricciones(Vehiculo v,int dia);
	boolean disponible(Vehiculo v);
	void fechaActul(DateTime fechaActual);
	List<Parqueo> vehiculosParqueados();
}