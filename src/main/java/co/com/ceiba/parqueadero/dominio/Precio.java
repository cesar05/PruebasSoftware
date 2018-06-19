package co.com.ceiba.parqueadero.dominio;

public class Precio {
	private int id;
	private double valorHora;
	private double valorDia;
	private Vehiculo vehiculo;
	private boolean vigente;
	
	public Precio(int id, double valorHora, double valorDia, Vehiculo vehiculo, boolean vigente) {
		super();
		this.id = id;
		this.valorHora = valorHora;
		this.valorDia = valorDia;
		this.vehiculo = vehiculo;
		this.vigente = vigente;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getValorHora() {
		return valorHora;
	}

	public void setValorHora(double valorHora) {
		this.valorHora = valorHora;
	}

	public double getValorDia() {
		return valorDia;
	}

	public void setValorDia(double valorDia) {
		this.valorDia = valorDia;
	}

	public Vehiculo getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}

	public boolean isVigente() {
		return vigente;
	}

	public void setVigente(boolean vigente) {
		this.vigente = vigente;
	}
	
}
