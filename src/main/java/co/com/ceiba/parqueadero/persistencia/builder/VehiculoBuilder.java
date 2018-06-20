package co.com.ceiba.parqueadero.persistencia.builder;

import co.com.ceiba.parqueadero.dominio.Moto;
import co.com.ceiba.parqueadero.dominio.Vehiculo;
import co.com.ceiba.parqueadero.persistencia.entidad.VehiculoEntity;

public class VehiculoBuilder {
	
	private VehiculoBuilder(){}
	
	public static Vehiculo aDominio(VehiculoEntity entity){
		Vehiculo vehiculo=null;
		if(entity!=null){
			vehiculo=new Moto(entity.getPlaca(),entity.getColor(),entity.getCilindraje());
		}
		return vehiculo;		
	}
	public static VehiculoEntity aEntity(Vehiculo vehiculo){
		VehiculoEntity entity=new VehiculoEntity();
		entity.setCilindraje(vehiculo.getCilindraje());
		entity.setColor(vehiculo.getColor());
		entity.setPlaca(vehiculo.getPlaca());
		return entity;
	}
	
}
