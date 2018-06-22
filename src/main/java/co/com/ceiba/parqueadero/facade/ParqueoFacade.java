package co.com.ceiba.parqueadero.facade;

import java.util.ArrayList;
import java.util.List;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import co.com.ceiba.parqueadero.dominio.Parqueo;
import co.com.ceiba.parqueadero.persistencia.builder.ParqueoBuilder;
import co.com.ceiba.parqueadero.persistencia.entidad.ParqueoEntity;
import co.com.ceiba.parqueadero.repositorio.RespositorioParqueo;

@Component
public class ParqueoFacade implements ParqueoFacadeInterface{
	
	@Autowired
	RespositorioParqueo repositorioParqueo;
	
	@Override
	public List<Parqueo> celdasOcupadas(){
		List<ParqueoEntity> parqueados=repositorioParqueo.celdasOcupadas();
		List<Parqueo> listParqueoDominio=new ArrayList<>();
		for(ParqueoEntity p:parqueados){
			listParqueoDominio.add(ParqueoBuilder.aDominio(p));
		}
		return listParqueoDominio;
	}

	@Override
	public void grabar(Parqueo p) {
		repositorioParqueo.save(ParqueoBuilder.aEntity(p));	
	}

	@Override
	public void salir(Parqueo p) {
		repositorioParqueo.salir(DateTime.now().toDate(),p.getValorPagar(),p.getVehiculo().getPlaca());
	}

	@Override
	public Parqueo findByPlaca(String placa) {
		return ParqueoBuilder.aDominio(repositorioParqueo.findByPlaca(placa));
	}
	
	

}
