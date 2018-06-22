package co.com.ceiba.parqueadero.logic;

import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.com.ceiba.parqueadero.dominio.Moto;
import co.com.ceiba.parqueadero.dominio.Parqueo;
import co.com.ceiba.parqueadero.dominio.Vehiculo;
import co.com.ceiba.parqueadero.exception.ParqueaderoException;
import co.com.ceiba.parqueadero.facade.ParqueoFacadeInterface;
import co.com.ceiba.parqueadero.facade.VehiculoFacadeInterface;
import co.com.ceiba.parqueadero.interfaces.IParqueo;


@Component
public class ParqueoLogic implements IParqueo{

	@Autowired
	VehiculoFacadeInterface vehiculoFacadeInterface;
	
	@Autowired
	ParqueoFacadeInterface parqueoFacadeInterface;
	
	public static final String CON_RESTRICCIONES="Tiene restricciones: No es un dia habil para su vehiculo";
	public static final String CUPO_NO_DISPONIBLE="Cupo no disponible";
	
	public ParqueoLogic() {
		super();
	}
	
	public ParqueoLogic(ParqueoFacadeInterface parqueoFacadeInterface){
		super();
		this.parqueoFacadeInterface=parqueoFacadeInterface;
	}

	@Override
	public boolean ingresar(Vehiculo v) {		
		this.disponible(v);
		DateTime fecha=DateTime.now();
		this.sinRestricciones(v,fecha.getDayOfWeek());		
		Parqueo parqueo=new Parqueo(fecha, null, 0, v);
		vehiculoFacadeInterface.grabar(v);		
		parqueoFacadeInterface.grabar(parqueo);				
		return true;		
	}

	@Override
	public boolean salir(Vehiculo v) {
		DateTime fechaSalida=DateTime.now().plus(5l);
		Parqueo p=parqueoFacadeInterface.findByPlaca(v.getPlaca());
		p.setValorPagar(this.calcularValorPagar(p.getFechaIngreso(), fechaSalida, v));
		parqueoFacadeInterface.salir(p);
		return false;
	}

	@Override
	public boolean sinRestricciones(Vehiculo v,int dia) {
		if(v!=null){
			String letra=v.getPlaca().substring(0,1);
			if(letra.equalsIgnoreCase("A")){
				if(dia!=1 && dia!=7){
					throw new ParqueaderoException(CON_RESTRICCIONES);					
				}				
			}
			return true;
		}
		else{
			throw new ParqueaderoException(CON_RESTRICCIONES);
		}
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
	
	public double calcularValorPagar(DateTime fechaIngreso,DateTime fechaSalida,Vehiculo v){
		double valorPagar=0;
		int horas=0;
		double valorHoraMoto=500;
		double valorHoraCarro=1000;
		double valorDiaMoto=4000;
		double valorDiaCarro=8000;
		Duration tiempoDeParqueo=new Duration(fechaIngreso,fechaSalida);
		if(tiempoDeParqueo.getStandardMinutes()<540){
			horas=(int)(Math.ceil((float)tiempoDeParqueo.getStandardMinutes()/60));
			if(v instanceof Moto){								
				valorPagar=horas*valorHoraMoto;
			}
			else{
				valorPagar=horas*valorHoraCarro;
			}
		}
		else{	
			Long dias=tiempoDeParqueo.getStandardDays();
			horas=(int)(Math.ceil((float)(tiempoDeParqueo.getStandardMinutes()-dias*1440)/60));
			if(v instanceof Moto){								
				valorPagar=dias*valorDiaMoto+horas*valorHoraMoto;
			}
			else{
				valorPagar=dias*valorDiaCarro+horas*valorHoraCarro;
			}
		}
		return valorPagar;
	}
}
