package co.com.ceiba.parqueadero.persistencia.entidad;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

@Entity(name="Vehiculo")
public class VehiculoEntity {
	
	@Id
	@NotNull
	private String placa;	
	private int cilindraje;
	private String tipo;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "vehiculo")
	private Collection<ParqueoEntity> ParqueoEntityCollection;
	
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}	
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public int getCilindraje() {
		return cilindraje;
	}
	public void setCilindraje(int cilindraje) {
		this.cilindraje = cilindraje;
	}
	
}
