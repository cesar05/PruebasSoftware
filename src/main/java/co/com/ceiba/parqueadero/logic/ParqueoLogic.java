package co.com.ceiba.parqueadero.logic;

import java.util.List;

import org.apache.logging.log4j.CloseableThreadContext.Instance;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.com.ceiba.parqueadero.dominio.Moto;
import co.com.ceiba.parqueadero.dominio.Parqueo;
import co.com.ceiba.parqueadero.dominio.Vehiculo;
import co.com.ceiba.parqueadero.exception.ParqueaderoException;
import co.com.ceiba.parqueadero.facade.ParqueoFacadeInterface;
import co.com.ceiba.parqueadero.facade.VehiculoFacadeInterface;
import co.com.ceiba.parqueadero.interfaces.IParqueo;
import co.com.ceiba.parqueadero.interfacesDAO.RepositorioVehiculo;
import co.com.ceiba.parqueadero.interfacesDAO.RespositorioParqueo;
import co.com.ceiba.parqueadero.persistencia.builder.ParqueoBuilder;
import co.com.ceiba.parqueadero.persistencia.builder.VehiculoBuilder;
import co.com.ceiba.parqueadero.persistencia.entidad.ParqueoEntity;

@Component
public class ParqueoLogic implements IParqueo{

	@Autowired
	VehiculoFacadeInterface vehiculoFacadeInterface;
	
	@Autowired
	ParqueoFacadeInterface parqueoFacadeInterface;
	
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
				vehiculoFacadeInterface.grabar(v);		
				parqueoFacadeInterface.grabar(parqueo);				
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
		List<Parqueo> parqueados=parqueoFacadeInterface.celdasOcupadas();
		int celdasCarrosOcupadas=0;
		int celdasMotosOcupadas=0;
		for(Parqueo p:parqueados){		
			if(p.getVehiculo() instanceof Moto){
				celdasMotosOcupadas++;
			}
			else{
				celdasCarrosOcupadas++;
			}
		}				
		if(v instanceof Moto){
			return celdasMotosOcupadas<10;
		}
		else{
			return celdasCarrosOcupadas<20;
		}		
	}
	
}
