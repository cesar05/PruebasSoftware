package co.com.ceiba.parqueadero.api;

import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.ceiba.parqueadero.dominio.Carro;
import co.com.ceiba.parqueadero.dominio.Moto;
import co.com.ceiba.parqueadero.dominio.Parqueo;
import co.com.ceiba.parqueadero.dominio.Vehiculo;
import co.com.ceiba.parqueadero.exception.ParqueaderoException;
import co.com.ceiba.parqueadero.interfaces.IParqueo;

@RestController
@RequestMapping(value = "/parqueo", headers = {
	"accept=application/json",
    "content-type=application/json"
})
public class ParqueoApi {
	
	@Autowired
	private IParqueo iParqueo;
	
	@PostMapping(value="/vehiculo")
	public String ingresarVehiculo(@RequestBody Map<String, String> payload){
		Vehiculo v;		
		String placa=payload.get("placa");
		int cilindraje=Integer.parseInt(payload.get("cilindraje"));
		int tipo=Integer.parseInt(payload.get("tipo"));
		if(tipo==1){		
			v=new Moto(placa, cilindraje);
		}
		else{
			v=new Carro(placa, cilindraje);
		}		
		
		if(iParqueo.ingresar(v)){
			return "registro correcto";
		}
		else{
			throw new ParqueaderoException("no fue posible registrar el ingreso");
		}				
	}
	
	/**
	 * Servicio para registrar la salida del vehiculo
	 * @param payload
	 * @return
	 */
	@PutMapping("/vehiculo")
	public double registrarSalidaVehiculo(@RequestBody Map<String, String> payload){
		Vehiculo v;		
		String placa=payload.get("placa");
		int cilindraje=Integer.parseInt(payload.get("cilindraje"));
		int tipo=Integer.parseInt(payload.get("tipo"));
		if(tipo==1){		
			v=new Moto(placa, cilindraje);
		}
		else{
			v=new Carro(placa, cilindraje);
		}		
		iParqueo.fechaActul(new DateTime().plusMinutes(2));
		return iParqueo.registrarSalida(v);
	}
	
	@GetMapping("/vehiculo")
	public List<Parqueo> prueba(){
		return iParqueo.vehiculosParqueados();
	}
}
