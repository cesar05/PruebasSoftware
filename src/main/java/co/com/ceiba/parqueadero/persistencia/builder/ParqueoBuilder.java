package co.com.ceiba.parqueadero.persistencia.builder;

import org.joda.time.DateTime;

import co.com.ceiba.parqueadero.dominio.Parqueo;
import co.com.ceiba.parqueadero.persistencia.entidad.ParqueoEntity;

public class ParqueoBuilder {
	
	private ParqueoBuilder(){}
	
	public static Parqueo aDominio(ParqueoEntity entity){
		Parqueo parqueo=null;
		
		if(entity!=null){
			parqueo=new Parqueo(new DateTime(entity.getFechaIngreso()), new DateTime(entity.getFechaSalida()), entity.getValorPagar(),VehiculoBuilder.aDominio(entity.getVehiculo()));
		}
		return parqueo;
	}
	
	public static ParqueoEntity aEntity(Parqueo parqueo){
		ParqueoEntity entity=new ParqueoEntity();
		entity.setFechaIngreso(parqueo.getFechaIngreso()!=null?parqueo.getFechaIngreso().toDate():null);		
		entity.setFechaSalida(parqueo.getFechaSalida()!=null?parqueo.getFechaSalida().toDate():null);
		entity.setValorPagar(parqueo.getValorPagar());
		entity.setVehiculo(VehiculoBuilder.aEntity(parqueo.getVehiculo()));
		return entity;
	}
	
}
