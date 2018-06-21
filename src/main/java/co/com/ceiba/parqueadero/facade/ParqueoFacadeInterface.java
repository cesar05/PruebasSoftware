package co.com.ceiba.parqueadero.facade;

import java.util.List;

import co.com.ceiba.parqueadero.dominio.Parqueo;

public interface ParqueoFacadeInterface {
	public List<Parqueo> celdasOcupadas();
	public void grabar(Parqueo p);
}
