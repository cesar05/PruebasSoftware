package co.com.ceiba.parqueadero.interfacesDAO;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import co.com.ceiba.parqueadero.persistencia.entidad.ParqueoEntity;

@Repository
public interface RespositorioParqueo extends CrudRepository<ParqueoEntity, Long>{
}
