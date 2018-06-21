package co.com.ceiba.parqueadero.facade;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import co.com.ceiba.parqueadero.dominio.Parqueo;
import co.com.ceiba.parqueadero.persistencia.builder.ParqueoBuilder;
import co.com.ceiba.parqueadero.persistencia.entidad.ParqueoEntity;
import co.com.ceiba.parqueadero.repositorio.RespositorioParqueo;

@Component
public class ParqueoFacade implements ParqueoFacadeInterface{
	
	@Autowired
	RespositorioParqueo respositorioParqueo;
	
	@Override
	public List<Parqueo> celdasOcupadas(){
		List<ParqueoEntity> parqueados=respositorioParqueo.celdasOcupadas();
		List<Parqueo> listParqueoDominio=new ArrayList<>();
		for(ParqueoEntity p:parqueados){
			listParqueoDominio.add(ParqueoBuilder.aDominio(p));
		}
		return listParqueoDominio;
	}

	@Override
	public void grabar(Parqueo p) {
		respositorioParqueo.save(ParqueoBuilder.aEntity(p));		
	}

}
