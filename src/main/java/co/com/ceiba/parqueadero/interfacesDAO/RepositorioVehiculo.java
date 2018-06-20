package co.com.ceiba.parqueadero.interfacesDAO;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import co.com.ceiba.parqueadero.persistencia.entidad.VehiculoEntity;

@Repository
public interface RepositorioVehiculo extends CrudRepository<VehiculoEntity, Long>{
}
