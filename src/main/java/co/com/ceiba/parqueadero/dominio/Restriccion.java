package co.com.ceiba.parqueadero.dominio;

public class Restriccion {	
	private int dia;
	private char letra;	
	
	public Restriccion(int dia, char letra) {
		super();		
		this.dia = dia;
		this.letra = letra;		
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
	
}
