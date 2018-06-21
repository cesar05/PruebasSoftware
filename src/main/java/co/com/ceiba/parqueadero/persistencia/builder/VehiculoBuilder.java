package co.com.ceiba.parqueadero.persistencia.builder;

import co.com.ceiba.parqueadero.dominio.Carro;
import co.com.ceiba.parqueadero.dominio.Moto;
import co.com.ceiba.parqueadero.dominio.Vehiculo;
import co.com.ceiba.parqueadero.persistencia.entidad.VehiculoEntity;

public class VehiculoBuilder {
	
	private VehiculoBuilder(){}
	
	public static Vehiculo aDominio(VehiculoEntity entity){
		Vehiculo vehiculo=null;
		if(entity!=null){
			if(entity.getTipo()=="Moto"){
				vehiculo=new Moto(entity.getPlaca(),entity.getCilindraje());
			}
			else{
				vehiculo=new Carro(entity.getPlaca(),entity.getCilindraje());				
			}
		}
		return vehiculo;		
	}
	public static VehiculoEntity aEntity(Vehiculo vehiculo){
		VehiculoEntity entity=new VehiculoEntity();
		entity.setCilindraje(vehiculo.getCilindraje());		
		entity.setPlaca(vehiculo.getPlaca());
		if(vehiculo instanceof Moto){
			entity.setTipo("Moto");
		}
		else{
			entity.setTipo("Carro");
		}
		return entity;
	}
	
}
