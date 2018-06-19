package co.com.ceiba.parqueadero.dao;

import org.springframework.stereotype.Component;

import co.com.ceiba.parqueadero.dominio.Vehiculo;
import co.com.ceiba.parqueadero.interfaces.IParqueo;

@Component
public class ParqueoDAO implements IParqueo{

	@Override
	public boolean ingresar(Vehiculo v) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean salir(Vehiculo v) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean conRestricciones(Vehiculo v) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean disponible(Vehiculo v) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
