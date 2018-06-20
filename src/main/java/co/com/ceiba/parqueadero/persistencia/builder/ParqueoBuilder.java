package co.com.ceiba.parqueadero.persistencia.builder;

import co.com.ceiba.parqueadero.dominio.Parqueo;
import co.com.ceiba.parqueadero.persistencia.entidad.ParqueoEntity;

public class ParqueoBuilder {
	
	private ParqueoBuilder(){}
	
	public static Parqueo aDominio(ParqueoEntity entity){
		Parqueo parqueo=null;
		
		if(entity!=null){
			parqueo=new Parqueo(entity.getFechaIngreso(), entity.getFechaSalida(), entity.getValorPagar(),VehiculoBuilder.aDominio(entity.getVehiculo()));
		}
		return parqueo;
	}
	
	public static ParqueoEntity aEntity(Parqueo parqueo){
		ParqueoEntity entity=new ParqueoEntity();
		entity.setFechaIngreso(parqueo.getFechaIngreso());
		entity.setFechaSalida(parqueo.getFechaSalida());
		entity.setValorPagar(parqueo.getValorPagar());
		entity.setVehiculo(VehiculoBuilder.aEntity(parqueo.getVehiculo()));
		return entity;
	}
	
}
