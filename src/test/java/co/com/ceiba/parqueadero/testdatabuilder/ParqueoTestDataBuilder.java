package co.com.ceiba.parqueadero.testdatabuilder;

import org.joda.time.DateTime;

import co.com.ceiba.parqueadero.dominio.Carro;
import co.com.ceiba.parqueadero.dominio.Moto;
import co.com.ceiba.parqueadero.dominio.Parqueo;
import co.com.ceiba.parqueadero.dominio.Vehiculo;

public class ParqueoTestDataBuilder {

	private static final DateTime FECHAINGRESO=new DateTime(2018,12,6,14,0,0);
	private static final DateTime FECHASALIDA=new DateTime(2018,12,6,19,0,0);
	private static final double VALORPAGAR=0;
	private static final Vehiculo MOTO=new Moto("ABC-123",500);
	private static final Vehiculo CARRO=new Carro("CCC-123",1600);
	
	private DateTime fechaIngreso;
	private DateTime fechaSalida;
	private double valorPagar;
	private Vehiculo vehiculo;
	
	public ParqueoTestDataBuilder(){
		this.fechaIngreso=FECHAINGRESO;
		this.fechaSalida=FECHASALIDA;
		this.valorPagar=VALORPAGAR;		
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
	public Parqueo buildConMoto(){
		this.vehiculo=MOTO;
		return new Parqueo(this.fechaIngreso, this.fechaSalida, this.valorPagar, this.vehiculo);
	}
	public Parqueo buildConCarro(){
		this.vehiculo=CARRO;
		return new Parqueo(this.fechaIngreso, this.fechaSalida, this.valorPagar, this.vehiculo);
	}
}
