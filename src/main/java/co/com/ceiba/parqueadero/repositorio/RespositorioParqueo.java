package co.com.ceiba.parqueadero.repositorio;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import co.com.ceiba.parqueadero.persistencia.entidad.ParqueoEntity;

@Repository
public interface RespositorioParqueo extends CrudRepository<ParqueoEntity, Long>{
	
	@Query("SELECT p FROM Parqueo p WHERE p.fechaSalida IS NULL")
	List<ParqueoEntity> celdasOcupadas();
		
	@Query(value="update parqueo set fecha_salida=?,valor_pagar=? where placa_vehiculo=?",nativeQuery=true)
	@Modifying
	@Transactional
	int salir(Date fechaSalida,double valorPagar,String placa);
		
	@Query("SELECT p FROM Parqueo p WHERE p.vehiculo.id=:placa AND p.fechaSalida IS NULL")
	ParqueoEntity findByPlaca(@Param("placa") String placa);
	
}
