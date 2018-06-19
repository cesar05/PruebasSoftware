package co.com.ceiba.parqueadero.dominio;

import org.joda.time.DateTime;

public class Vigilante {
	private DateTime fechaRegistro;
	private Horario horario;	
	
	public Vigilante(DateTime fechaRegistro, Horario horario) {
		super();
		this.fechaRegistro = fechaRegistro;
		this.horario = horario;
	}
	
	public DateTime getFechaRegistro() {
		return fechaRegistro;
	}
	public void setFechaRegistro(DateTime fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	public Horario getHorario() {
		return horario;
	}
	public void setHorario(Horario horario) {
		this.horario = horario;
	}
	
}
