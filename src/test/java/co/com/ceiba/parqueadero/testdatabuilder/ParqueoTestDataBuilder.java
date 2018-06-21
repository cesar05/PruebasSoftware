package co.com.ceiba.parqueadero.testdatabuilder;

import org.joda.time.DateTime;

import co.com.ceiba.parqueadero.dominio.Carro;
import co.com.ceiba.parqueadero.dominio.Moto;
import co.com.ceiba.parqueadero.dominio.Parqueo;
import co.com.ceiba.parqueadero.dominio.Vehiculo;

public class ParqueoTestDataBuilder {

	private static final DateTime FECHAINGRESO=new DateTime("20181206 14:00:00");
	private static final DateTime FECHASALIDA=new DateTime("20181206 19:00:00");
	private static final double VALORPAGAR=0;
	private static final Vehiculo VEHICULOMOTO=new Moto("ABC-123",500);
	private static final Vehiculo VEHICULOCARRO=new Carro("CCC-123",1600);
	
	private DateTime fechaIngreso;
	private DateTime fechaSalida;
	private double valorPagar;
	private Vehiculo vehiculo;
	
	public ParqueoTestDataBuilder(){
		this.fechaIngreso=FECHAINGRESO;
		this.fechaSalida=FECHASALIDA;
		this.valorPagar=VALORPAGAR;
		this.vehiculo=VEHICULOCARRO;
	}
	
	public ParqueoTestDataBuilder conFechaIngreso(DateTime fechaIngreso){
		this.fechaIngreso=fechaIngreso;
		return this;
	}
	public ParqueoTestDataBuilder conFechaSalida(DateTime fechaSalida){
		this.fechaSalida=fechaSalida;
		return this;
	}
	public ParqueoTestDataBuilder conValorAPagar(double valorPagar){
		this.valorPagar=valorPagar;
		return this;
	}
	
	public ParqueoTestDataBuilder conVehiculo(Vehiculo v){
		this.vehiculo=v;
		return this;
	}
	
	public Parqueo build(){
		return new Parqueo(this.fechaIngreso, this.fechaSalida, this.valorPagar, this.vehiculo);
	}
}
