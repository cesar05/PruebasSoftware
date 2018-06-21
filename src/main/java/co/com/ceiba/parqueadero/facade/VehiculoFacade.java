package co.com.ceiba.parqueadero.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.com.ceiba.parqueadero.dominio.Vehiculo;
import co.com.ceiba.parqueadero.interfacesDAO.RepositorioVehiculo;
import co.com.ceiba.parqueadero.persistencia.builder.VehiculoBuilder;

@Component
public class VehiculoFacade implements VehiculoFacadeInterface {

	@Autowired
	RepositorioVehiculo repositorioVehiculo;
	
	@Override
	public void grabar(Vehiculo v) {
		repositorioVehiculo.save(VehiculoBuilder.aEntity(v));		
	}

}
