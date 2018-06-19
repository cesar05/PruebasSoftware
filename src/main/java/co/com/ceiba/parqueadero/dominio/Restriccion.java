package co.com.ceiba.parqueadero.dominio;

public class Restriccion {
	private int id;
	private int dia;
	private char letra;
	private char estado;
	
	public Restriccion(int id, int dia, char letra, char estado) {
		super();
		this.id = id;
		this.dia = dia;
		this.letra = letra;
		this.estado = estado;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getDia() {
		return dia;
	}
	public void setDia(int dia) {
		this.dia = dia;
	}
	public char getLetra() {
		return letra;
	}
	public void setLetra(char letra) {
		this.letra = letra;
	}
	public char getEstado() {
		return estado;
	}
	public void setEstado(char estado) {
		this.estado = estado;
	}
	
	
}
