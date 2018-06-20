package co.com.ceiba.parqueadero.persistencia.entidad;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

import org.joda.time.DateTime;

@Entity(name = "Parqueo")
@NamedQuery(name = "Parqueo.findByIsbn", query = "SELECT parqueo FROM Parqueo parqueo WHERE parqueo.id = :id")
public class ParqueoEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private DateTime fechaIngreso;	
	private DateTime fechaSalida;
	private double valorPagar;
	
	@ManyToOne
	@JoinColumn(name="PLACA_VEHICULO",referencedColumnName="placa")
	private VehiculoEntity vehiculo;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public VehiculoEntity getVehiculo() {
		return vehiculo;
	}
	public void setVehiculo(VehiculoEntity vehiculo) {
		this.vehiculo = vehiculo;
	}
}
