package co.com.ceiba.parqueadero.dominio;

public abstract class Vehiculo {
	private String placa;	
	private int cilindraje;
	
	public Vehiculo(String placa, int cilindraje) {
		super();
		this.placa = placa;		
		this.cilindraje = cilindraje;
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
	
}
