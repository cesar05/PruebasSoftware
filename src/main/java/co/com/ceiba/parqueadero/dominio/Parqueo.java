package co.com.ceiba.parqueadero.dominio;

import org.joda.time.DateTime;

public class Parqueo{
	
	private DateTime fechaIngreso;
	private DateTime fechaSalida;
	private double valorPagar;
	private Vehiculo vehiculo;
	
	public Parqueo(DateTime fechaIngreso, DateTime fechaSalida, double valorPagar, Vehiculo vehiculo) {
		super();
		this.fechaIngreso = fechaIngreso;
		this.fechaSalida = fechaSalida;
		this.valorPagar = valorPagar;
		this.vehiculo = vehiculo;
	}
	public DateTime getFechaIngreso() {
		return fechaIngreso;
	}
	public void setFechaIngreso(DateTime fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}
	public DateTime getFechaSalida() {
		return fechaSalida;
	}
	public void setFechaSalida(DateTime fechaSalida) {
		this.fechaSalida = fechaSalida;
	}
	public double getValorPagar() {
		return valorPagar;
	}
	public void setValorPagar(double valorPagar) {
		this.valorPagar = valorPagar;
	}
	public Vehiculo getVehiculo() {
		return vehiculo;
	}
	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}
	
	
}
