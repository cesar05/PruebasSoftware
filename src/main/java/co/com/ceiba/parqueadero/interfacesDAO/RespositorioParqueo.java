package co.com.ceiba.parqueadero.interfacesDAO;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import co.com.ceiba.parqueadero.persistencia.entidad.ParqueoEntity;

@Repository
public interface RespositorioParqueo extends CrudRepository<ParqueoEntity, Long>{
	
	@Query("SELECT p FROM Parqueo p WHERE p.fechaSalida IS NULL")
	List<ParqueoEntity> celdasOcupadas(); 
}
