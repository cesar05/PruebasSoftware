package co.com.ceiba.parqueadero.dominio;

public enum TipoVehiculo {
	
	MOTO(0),
	CARRO(1);
	
	private final int tipo;
	
	
	public int getTipoVehiculo() {
		return tipo;
	}

	private TipoVehiculo(int tipoVehiculo){
		this.tipo = tipoVehiculo;
	}	

}
