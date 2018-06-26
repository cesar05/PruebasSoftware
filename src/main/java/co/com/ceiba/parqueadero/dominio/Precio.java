package co.com.ceiba.parqueadero.dominio;

public final class Precio {
	
	private double valorHoraMoto;
	private double valorHoraCarro;
	private double valorDiaMoto;
	private double valorDiaCarro;
	private double valorAdicionalMotos;
	
	public Precio(double valorHoraMoto, double valorHoraCarro, double valorDiaMoto, double valorDiaCarro,
			double valorAdicionalMotos) {
		super();
		this.valorHoraMoto = valorHoraMoto;
		this.valorHoraCarro = valorHoraCarro;
		this.valorDiaMoto = valorDiaMoto;
		this.valorDiaCarro = valorDiaCarro;
		this.valorAdicionalMotos = valorAdicionalMotos;
	}
	
	public double getValorHoraMoto() {
		return valorHoraMoto;
	}
	public void setValorHoraMoto(double valorHoraMoto) {
		this.valorHoraMoto = valorHoraMoto;
	}
	public double getValorHoraCarro() {
		return valorHoraCarro;
	}
	public void setValorHoraCarro(double valorHoraCarro) {
		this.valorHoraCarro = valorHoraCarro;
	}
	public double getValorDiaMoto() {
		return valorDiaMoto;
	}
	public void setValorDiaMoto(double valorDiaMoto) {
		this.valorDiaMoto = valorDiaMoto;
	}
	public double getValorDiaCarro() {
		return valorDiaCarro;
	}
	public void setValorDiaCarro(double valorDiaCarro) {
		this.valorDiaCarro = valorDiaCarro;
	}
	public double getValorAdicionalMotos() {
		return valorAdicionalMotos;
	}
	public void setValorAdicionalMotos(double valorAdicionalMotos) {
		this.valorAdicionalMotos = valorAdicionalMotos;
	}
	
	
	
}
