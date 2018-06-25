package co.com.ceiba.parqueadero.logic;

import java.util.ArrayList;
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
	public static final String VEHICULO_PARQUEADO="El vehiculo ya se encuentra parqueado en Ceiba Software House";
	public static final String VEHICULO_NO_ESTA_PARQUEADO="El vehiculo no esta parqueadeo en Ceiba Software House";
	
	private DateTime fechaActual;
	
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

	public ParqueoLogic(ParqueoFacadeInterface parqueoFacadeInterface,DateTime fechaActual){
		super();
		this.parqueoFacadeInterface=parqueoFacadeInterface;
		this.fechaActual=fechaActual;
	}
	
	@Override
	public boolean ingresar(Vehiculo v) {		
		this.disponible(v);
		this.sinRestricciones(v,fechaActual.getDayOfWeek());		
		Parqueo parqueo=new Parqueo(fechaActual, null, 0, v);
		//Valido que el vehiculo no este en el parqueado
		Parqueo parqueado=parqueoFacadeInterface.findByPlaca(v.getPlaca());
		if(parqueado!=null){
			throw new ParqueaderoException(VEHICULO_PARQUEADO);
		}
		vehiculoFacadeInterface.grabar(v);		
		parqueoFacadeInterface.grabar(parqueo);				
		return true;		
	}

	@Override
	public double registrarSalida(Vehiculo v) {		
		Parqueo p=parqueoFacadeInterface.findByPlaca(v.getPlaca());
		if(p==null){
			throw new ParqueaderoException(VEHICULO_NO_ESTA_PARQUEADO);
		}		
		p.setValorPagar(this.calcularValorPagar(p.getFechaIngreso(), this.fechaActual, v));		
		parqueoFacadeInterface.salir(p);
		return p.getValorPagar();
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
		double valorHoraMoto=500;
		double valorHoraCarro=1000;
		double valorDiaMoto=4000;
		double valorDiaCarro=8000;
		double valorAdicionalMotos=2000;
		Duration tiempoDeParqueo=new Duration(fechaIngreso,fechaSalida);
		if(tiempoDeParqueo.getStandardMinutes()<540){
			int horas=(int)(Math.ceil((float)tiempoDeParqueo.getStandardMinutes()/60));
			valorPagar=v instanceof Moto?horas*valorHoraMoto:horas*valorHoraCarro;
		}
		else{	
			Long dias=tiempoDeParqueo.getStandardDays();
			if(dias==0){
				valorPagar=v instanceof Moto?valorDiaMoto:valorDiaCarro;
			}
			else{
				Long minutosUltimoDia=tiempoDeParqueo.getStandardMinutes()-dias*1440;
				int horasUlitmoDia=(int)(Math.ceil((float)(tiempoDeParqueo.getStandardMinutes()-dias*1440)/60));
				if(minutosUltimoDia>=540){
					dias+=1;
					horasUlitmoDia=0;
				}	
				valorPagar=v instanceof Moto?dias*valorDiaMoto+horasUlitmoDia*valorHoraMoto
											:dias*valorDiaCarro+horasUlitmoDia*valorHoraCarro;
			}			
		}
		
		valorPagar +=v instanceof Moto && v.getCilindraje()>500?valorAdicionalMotos:0;
		
		return valorPagar;
	}
	
	@Override
	public void fechaActul(DateTime fechaActual){
		this.fechaActual=fechaActual;
	}

	@Override
	public List<Parqueo> vehiculosParqueados() {
		List<Parqueo> vehiculosParqueados=parqueoFacadeInterface.celdasOcupadas();
		return vehiculosParqueados;
	}
}
