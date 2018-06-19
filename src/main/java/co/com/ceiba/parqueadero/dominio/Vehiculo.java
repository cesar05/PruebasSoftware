package co.com.ceiba.parqueadero.dominio;

public abstract class Vehiculo {
	private String placa;
	private String color;
	private int cilindraje;
	
	public Vehiculo(String placa, String color, int cilindraje) {
		super();
		this.placa = placa;
		this.color = color;
		this.cilindraje = cilindraje;
	}
	
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public int getCilindraje() {
		return cilindraje;
	}
	public void setCilindraje(int cilindraje) {
		this.cilindraje = cilindraje;
	}
	
}
