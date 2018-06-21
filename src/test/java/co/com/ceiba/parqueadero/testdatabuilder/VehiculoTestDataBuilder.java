package co.com.ceiba.parqueadero.testdatabuilder;

import co.com.ceiba.parqueadero.dominio.Carro;
import co.com.ceiba.parqueadero.dominio.Moto;
import co.com.ceiba.parqueadero.dominio.Vehiculo;

public class VehiculoTestDataBuilder {
	
	private static final String PLACA="ABC-123";	
	private static final int CILINDRAJE=250;
	
	private String placa;	
	private int cilindraje;
	
	public VehiculoTestDataBuilder() {		
		this.placa = PLACA;
		this.cilindraje = CILINDRAJE;
	}
	
	public VehiculoTestDataBuilder conPlaca(String placa){
		this.placa=placa;
		return this;
	}
	
	public VehiculoTestDataBuilder conCilindraje(int cilindraje){
		this.cilindraje=cilindraje;
		return this;
	}
	
	public Vehiculo buildMoto(){
		return new Moto(this.placa,this.cilindraje);
	}
	
	public Vehiculo buildCarro(){
		return new Carro(this.placa,this.cilindraje);
	}	
	
}
