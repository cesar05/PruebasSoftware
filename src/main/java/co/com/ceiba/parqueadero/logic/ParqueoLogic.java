package co.com.ceiba.parqueadero.logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.com.ceiba.parqueadero.dominio.Moto;
import co.com.ceiba.parqueadero.dominio.Parqueo;
import co.com.ceiba.parqueadero.dominio.Precio;
import co.com.ceiba.parqueadero.dominio.Vehiculo;
import co.com.ceiba.parqueadero.exception.ParqueaderoException;
import co.com.ceiba.parqueadero.facade.ParqueoFacadeInterface;
import co.com.ceiba.parqueadero.facade.VehiculoFacadeInterface;
import co.com.ceiba.parqueadero.interfaces.IParqueo;


@Component
public class ParqueoLogic implements IParqueo{

	private static final int MINUTOS_EN_UN_DIA = 1440;

	private static final int UNA_HORA_EN_MINUTOS = 60;

	private static final int NUEVE_HORAS_EN_MINUTOS = 540;

	@Autowired
	VehiculoFacadeInterface vehiculoFacadeInterface;
	
	@Autowired
	ParqueoFacadeInterface parqueoFacadeInterface;
	
	public static final String CON_RESTRICCIONES="Tiene restricciones: No es un dia habil para su vehiculo";
	public static final String CUPO_NO_DISPONIBLE="Cupo no disponible";
	public static final String VEHICULO_PARQUEADO="El vehiculo ya se encuentra parqueado en Ceiba Software House";
	public static final String VEHICULO_NO_ESTA_PARQUEADO="El vehiculo no esta parqueadeo en Ceiba Software House";
	
	private DateTime fechaActual;
	
	private Precio precio;
	
			
	public ParqueoLogic() {
		super();
		this.fechaActual=new DateTime();
	}
	
	public ParqueoLogic(ParqueoFacadeInterface parqueoFacadeInterface){
		super();
		this.parqueoFacadeInterface=parqueoFacadeInterface;
		this.fechaActual=new DateTime();
	}
	
	public ParqueoLogic(ParqueoFacadeInterface parqueoFacadeInterface,VehiculoFacadeInterface vehiculoFacadeInterface,DateTime fechaActual){
		super();
		this.parqueoFacadeInterface=parqueoFacadeInterface;
		this.vehiculoFacadeInterface=vehiculoFacadeInterface;
		this.fechaActual=fechaActual;
	}

	
	@Override
	public boolean ingresar(Vehiculo vehiculo) {		
		this.disponible(vehiculo);
		this.sinRestricciones(vehiculo,fechaActual.getDayOfWeek());		
		Parqueo parqueo=new Parqueo(fechaActual, null, 0, vehiculo);
		//Valido que el vehiculo no este en el parqueado
		Parqueo parqueado=parqueoFacadeInterface.findByPlaca(vehiculo.getPlaca());
		if(parqueado!=null){
			throw new ParqueaderoException(VEHICULO_PARQUEADO);
		}
		vehiculoFacadeInterface.grabar(vehiculo);		
		parqueoFacadeInterface.grabar(parqueo);				
		return true;		
	}

	@Override
	public double registrarSalida(Vehiculo vehiculo) {		
		Parqueo p=parqueoFacadeInterface.findByPlaca(vehiculo.getPlaca());
		if(p==null){
			throw new ParqueaderoException(VEHICULO_NO_ESTA_PARQUEADO);
		}				
		vehiculo.setCilindraje(p.getVehiculo().getCilindraje());
		p.setFechaSalida(this.fechaActual);		
		p.setValorPagar(this.calcularValorPagar(p.getFechaIngreso(), p.getFechaSalida(), vehiculo));		
		parqueoFacadeInterface.salir(p);
		return p.getValorPagar();
	}

	@Override
	public boolean sinRestricciones(Vehiculo vehiculo,int dia) {
		
		if( vehiculo==null ){
			throw new ParqueaderoException(CON_RESTRICCIONES);
		}
		
		String letra=vehiculo.getPlaca().substring(0,1);
		if(letra.equalsIgnoreCase("A") && dia!=1 && dia!=7){
			throw new ParqueaderoException(CON_RESTRICCIONES);					
		}
		return true;
		
	}

	@Override
	public boolean disponible(Vehiculo v) {	
		List<Parqueo> vehiculosParqueados=parqueoFacadeInterface.celdasOcupadas();
		int celdasCarrosOcupadas=0;
		int celdasMotosOcupadas=0;
		for(Parqueo p:vehiculosParqueados){		
			if(p.getVehiculo() instanceof Moto){
				celdasMotosOcupadas++;
			}
			else{
				celdasCarrosOcupadas++;
			}
		}				
		if(v instanceof Moto){
			if(celdasMotosOcupadas>=10)
				throw new ParqueaderoException(CUPO_NO_DISPONIBLE);
		}
		else{			
			if(celdasCarrosOcupadas>=20)
				throw new ParqueaderoException(CUPO_NO_DISPONIBLE);
		}		
		return true;
	}
	
	public double calcularValorPagar(DateTime fechaIngreso,DateTime fechaSalida, Vehiculo v){
		double valorPagar=0;
		Duration tiempoDeParqueo=new Duration(fechaIngreso,fechaSalida);
		this.precio=new Precio().getPrecio(v instanceof Moto?0:1);	
				
		Long dias=tiempoDeParqueo.getStandardDays();
		Long minutosUltimoDia=tiempoDeParqueo.getStandardMinutes()-dias*MINUTOS_EN_UN_DIA;
		int horas=0;	
		
		if(minutosUltimoDia>=NUEVE_HORAS_EN_MINUTOS) {
			dias++;	
			minutosUltimoDia=0l;
		}
		
		horas=(int)Math.ceil((float)minutosUltimoDia/(float)UNA_HORA_EN_MINUTOS);
		
		valorPagar=dias*this.precio.getValorDia()+horas*this.precio.getValorHora();
		
		valorPagar +=v.getCilindraje()>500?precio.getValorAdicional():0;
		
		return valorPagar;
	}
	
	@Override
	public void fechaActul(DateTime fechaActual){
		this.fechaActual=fechaActual;
	}

	@Override
	public List<Map<String,String>> vehiculosParqueados() {
		List<Parqueo> vehiculosParqueados=parqueoFacadeInterface.celdasOcupadas();
		List<Map<String,String>> vehiculosParqueadosFormat=new ArrayList<>();
		for(Parqueo p:vehiculosParqueados){
			Map<String,String> json=new HashMap<>();
			json.put("placa",p.getVehiculo().getPlaca());
			json.put("tipo",p.getVehiculo() instanceof Moto?"Moto":"Carro");
			json.put("fechaIngreso",p.getFechaIngreso().toString("yyyy/MM/dd hh:mm:ss"));
			json.put("cilindraje",String.valueOf(p.getVehiculo().getCilindraje()));
			vehiculosParqueadosFormat.add(json);
		}
		return vehiculosParqueadosFormat;
	}
}
