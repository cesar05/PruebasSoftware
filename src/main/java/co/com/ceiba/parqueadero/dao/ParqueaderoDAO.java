package co.com.ceiba.parqueadero.dao;

import org.springframework.stereotype.Component;

import co.com.ceiba.parqueadero.dominio.Parqueadero;
import co.com.ceiba.parqueadero.interfaces.IParqueadero;

@Component
public class ParqueaderoDAO implements IParqueadero{

	@Override
	public boolean nuevo(Parqueadero p) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Parqueadero obtener(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
