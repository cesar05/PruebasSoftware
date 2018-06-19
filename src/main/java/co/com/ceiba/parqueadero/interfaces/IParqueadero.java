package co.com.ceiba.parqueadero.interfaces;

import co.com.ceiba.parqueadero.dominio.Parqueadero;

/**
 * Interfaz disponible para Parqueadero
 * @author cesar.munoz
 *
 */

public interface IParqueadero {	
	boolean nuevo(Parqueadero p);	
	Parqueadero obtener(int id);
}
