package co.com.ceiba.parqueadero.dominio;

public abstract class Vehiculo {
	private String placa;	
	private int cilindraje;
	private int tipo;
	
	public Vehiculo(String placa, int cilindraje) {
		super();
		this.placa = placa;		
		this.cilindraje = cilindraje;
	}
	
	public Vehiculo(String placa, int cilindraje, int tipo) {
		super();
		this.placa = placa;		
		this.cilindraje = cilindraje;
		this.tipo = tipo;
	}
	
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}	
	public int getCilindraje() {
		return cilindraje;
	}
	public void setCilindraje(int cilindraje) {
		this.cilindraje = cilindraje;
	}
	public int getTipo() {
		return tipo;
	}

	
}
