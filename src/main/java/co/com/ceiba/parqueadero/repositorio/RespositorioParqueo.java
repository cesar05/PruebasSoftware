package co.com.ceiba.parqueadero.repositorio;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import co.com.ceiba.parqueadero.persistencia.entidad.ParqueoEntity;

@Repository
public interface RespositorioParqueo extends CrudRepository<ParqueoEntity, Long>{
	
	@Query("SELECT p FROM Parqueo p WHERE p.fechaSalida IS NULL")
	List<ParqueoEntity> celdasOcupadas();
	
	//@Modifying(clearAutomatically = true)
	@Modifying()
	@Query("UPDATE Parqueo p set p.fechaSalida=:fechaSalida WHERE p.vehiculo.id=:placa AND p.fechaSalida IS NULL")
	void salir(@Param("fechaSalida") Date fechaSalida,@Param("placa")String placa);
	
	
	//@Modifying(clearAutomatically = true)
	/*@Modifying()
	@Query("UPDATE Parqueo p set p.fechaSalida=:fechaSalida WHERE p=:parqueo and p.fechaSalida IS NULL")
	void salir(@Param("parqueo") ParqueoEntity parqueoEntity,@Param("fechaSalida") Date fechaSalida);
	*/
	
	@Query("SELECT p FROM Parqueo p WHERE p.vehiculo.id=:placa")
	ParqueoEntity findByPlaca(@Param("placa") String placa);
	
}
