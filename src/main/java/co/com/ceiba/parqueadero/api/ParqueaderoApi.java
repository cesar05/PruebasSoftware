package co.com.ceiba.parqueadero.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.com.ceiba.parqueadero.dominio.Moto;
import co.com.ceiba.parqueadero.dominio.Vehiculo;
import co.com.ceiba.parqueadero.interfaces.IParqueadero;

@RestController
@RequestMapping(value = "/parqueadero", headers = {
	"accept=application/json",
    "content-type=application/json"
})
public class ParqueaderoApi {
	
	@Autowired
	IParqueadero iParqueadero;
	
	@RequestMapping(method=RequestMethod.GET)
	public List<Vehiculo> get(){
		List<Vehiculo> lista=new ArrayList<>();
		lista.add(new Moto("asd-123","get",500));		
		return lista;	
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public Vehiculo post(){
		Vehiculo a=new Moto("abc-123","post",500);
		return a;		
	}
	
	@RequestMapping(method=RequestMethod.PUT)
	public String put(){
		Vehiculo a=new Moto("abc-123","put",500);
		return a.getColor();		
	}
	
	@RequestMapping(method=RequestMethod.DELETE)
	public String delete(){
		Vehiculo a=new Moto("abc-123","delete",500);
		return a.getColor();		
	}
}
