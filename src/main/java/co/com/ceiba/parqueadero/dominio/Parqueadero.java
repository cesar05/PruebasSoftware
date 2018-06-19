package co.com.ceiba.parqueadero.dominio;

public class Parqueadero {
	private int id;
	private String sucursal;
	private int nroCeldasCarros;
	private int nroCeldasMotos;
	private Horario horario;
	
	
	public Parqueadero(int id, String sucursal, int nroCeldasCarros, int nroCeldasMotos, Horario horario) {
		super();
		this.id = id;
		this.sucursal = sucursal;
		this.nroCeldasCarros = nroCeldasCarros;
		this.nroCeldasMotos = nroCeldasMotos;
		this.horario = horario;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSucursal() {
		return sucursal;
	}
	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}
	public int getNroCeldasCarros() {
		return nroCeldasCarros;
	}
	public void setNroCeldasCarros(int nroCeldasCarros) {
		this.nroCeldasCarros = nroCeldasCarros;
	}
	public int getNroCeldasMotos() {
		return nroCeldasMotos;
	}
	public void setNroCeldasMotos(int nroCeldasMotos) {
		this.nroCeldasMotos = nroCeldasMotos;
	}
	public Horario getHorario() {
		return horario;
	}
	public void setHorario(Horario horario) {
		this.horario = horario;
	}
	
	
}
