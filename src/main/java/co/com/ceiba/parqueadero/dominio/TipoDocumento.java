package co.com.ceiba.parqueadero.dominio;

public class TipoDocumento {
	private char id;
	private String tipo;
	
	public TipoDocumento(char id, String tipo) {
		super();
		this.id = id;
		this.tipo = tipo;
	}
	
	public char getId() {
		return id;
	}
	public void setId(char id) {
		this.id = id;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
}
