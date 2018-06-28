package co.com.ceiba.parqueadero.api;

import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import co.com.ceiba.parqueadero.dominio.Carro;
import co.com.ceiba.parqueadero.dominio.Moto;
import co.com.ceiba.parqueadero.dominio.Respuesta;
import co.com.ceiba.parqueadero.dominio.Vehiculo;
import co.com.ceiba.parqueadero.exception.ParqueaderoException;
import co.com.ceiba.parqueadero.interfaces.IParqueo;
import co.com.ceiba.parqueadero.logic.ParqueoLogic;

@RestController
@CrossOrigin(origins="*")
@RequestMapping(value = "/parqueo", headers = {
	"accept=application/json",
    "content-type=application/json"
})
public class ParqueoApi {
	
	@Autowired
	private IParqueo iParqueo;
		
	public ParqueoApi(){
		super();
	}
	
	public ParqueoApi(IParqueo parqueoLogic){
		super();
		this.iParqueo=parqueoLogic;
	}
    
	@PostMapping(value="/vehiculo")
	public Respuesta ingresarVehiculo(@RequestBody Map<String, String> payload){
		Vehiculo v;	
		String placa;
		int cilindraje=0;
		String tipo;
		
		placa=payload.get("placa");
		if(placa==null || placa.trim().length()==0)
			throw new ParqueaderoException("Valor de placa no valido");
		
		if(payload.get("cilindraje")==null){
			tipo="Carro";
		}
		else{
			try{
				cilindraje=Integer.parseInt(payload.get("cilindraje"));
				tipo="Moto";
			}
			catch(NumberFormatException e){
				throw new ParqueaderoException("Valor del Cilindraje no valido");
			}
			
		}
		
		if(tipo.equalsIgnoreCase("Moto")){		
			v=new Moto(placa, cilindraje);
		}
		else{
			v=new Carro(placa, cilindraje);
		}		
		iParqueo.fechaActul(new DateTime());
		iParqueo.ingresar(v);
		return new Respuesta("Entrada registrada!");			
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
		String tipo=payload.get("tipo");
		
		if(tipo.equalsIgnoreCase("Moto")){		
			v=new Moto(placa, 0);
		}
		else{
			v=new Carro(placa, 0);
		}
		iParqueo.fechaActul(new DateTime());
		return iParqueo.registrarSalida(v);
	}
	
	@GetMapping("/vehiculo")
	public List<Map<String,String>> obtenerVehiculosParqueados(){
		return iParqueo.vehiculosParqueados();
	}
}
