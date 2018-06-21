package co.com.ceiba.parqueadero.logic;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.com.ceiba.parqueadero.dominio.Moto;
import co.com.ceiba.parqueadero.dominio.Parqueo;
import co.com.ceiba.parqueadero.dominio.Vehiculo;
import co.com.ceiba.parqueadero.exception.ParqueaderoException;
import co.com.ceiba.parqueadero.interfaces.IParqueo;
import co.com.ceiba.parqueadero.interfacesDAO.RepositorioVehiculo;
import co.com.ceiba.parqueadero.interfacesDAO.RespositorioParqueo;
import co.com.ceiba.parqueadero.persistencia.builder.ParqueoBuilder;
import co.com.ceiba.parqueadero.persistencia.builder.VehiculoBuilder;
import co.com.ceiba.parqueadero.persistencia.entidad.ParqueoEntity;

@Component
public class ParqueoLogic implements IParqueo{

	@Autowired
	RepositorioVehiculo repositorioVehiculo;
	
	@Autowired
	RespositorioParqueo respositorioParqueo;
	
	public ParqueoLogic() {
		super();
	}

	@Override
	public boolean ingresar(Vehiculo v) {
		if(this.disponible(v)){
			if(this.conRestricciones(v)){
				throw new ParqueaderoException("Tiene restricciones: No es un dia habil para su vehiculo");
			}
			else{	
				Parqueo parqueo=new Parqueo(new DateTime(), null, 0, v);
				repositorioVehiculo.save(VehiculoBuilder.aEntity(v));		
				respositorioParqueo.save(ParqueoBuilder.aEntity(parqueo));				
				return true;
			}
		}
		else{
			throw new ParqueaderoException("Cupo no disponible");
		}
	}

	@Override
	public boolean salir(Vehiculo v) {
		
		return false;
	}

	@Override
	public boolean conRestricciones(Vehiculo v) {
		
		return false;
	}

	@Override
	public boolean disponible(Vehiculo v) {
		return true;
	}
	
}
