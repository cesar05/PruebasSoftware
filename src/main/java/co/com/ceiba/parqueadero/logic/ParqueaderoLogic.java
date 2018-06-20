package co.com.ceiba.parqueadero.logic;

import org.springframework.stereotype.Component;

import co.com.ceiba.parqueadero.dominio.Parqueadero;
import co.com.ceiba.parqueadero.interfaces.IParqueadero;

@Component
public class ParqueaderoLogic implements IParqueadero{

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
