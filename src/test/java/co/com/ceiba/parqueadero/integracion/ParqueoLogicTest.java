package co.com.ceiba.parqueadero.integracion;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import co.com.ceiba.parqueadero.dominio.Carro;
import co.com.ceiba.parqueadero.dominio.Moto;
import co.com.ceiba.parqueadero.dominio.Parqueo;
import co.com.ceiba.parqueadero.dominio.Vehiculo;
import co.com.ceiba.parqueadero.exception.ParqueaderoException;
import co.com.ceiba.parqueadero.facade.ParqueoFacadeInterface;
import co.com.ceiba.parqueadero.facade.VehiculoFacadeInterface;
import co.com.ceiba.parqueadero.logic.ParqueoLogic;
import co.com.ceiba.parqueadero.repositorio.RespositorioParqueo;
import co.com.ceiba.parqueadero.testdatabuilder.ParqueoTestDataBuilder;
import co.com.ceiba.parqueadero.testdatabuilder.VehiculoTestDataBuilder;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ParqueoLogicTest {
	
	private static final String CON_RESTRICCIONES="Tiene restricciones: No es un dia habil para su vehiculo";
	private static final String CUPO_NO_DISPONIBLE="Cupo no disponible";	
	private static final String VEHICULO_PARQUEADO="El vehiculo ya se encuentra parqueado en Ceiba Software House";
	private static final String VEHICULO_NO_ESTA_PARQUEADO="El vehiculo no esta parqueadeo en Ceiba Software House";
	private static final double DELTA=0.01;
	
	private ParqueoLogic parqueoLogic;
	
	@Autowired
	private VehiculoFacadeInterface vehiculoFacadeInterface;
	
	@Autowired
	private ParqueoFacadeInterface parqueoFacadeInterface;
	
	@Autowired
	private RespositorioParqueo repositorioParqueo;
	
	@Before
	public void inicializacion(){
		repositorioParqueo.deleteAll();
	}
	@After
	public void finalizacion(){
		repositorioParqueo.deleteAll();
	}
	
	/**
	 * Moto.
	 * Se prueba cuando hay "CUPO" pero tiene "RESTRICCION" por placa que inicia con la letra "A".
	 * dia: Miercoles
	 * placa: ABC-123
	 */
	@Test
	public void ingresarMoto1Test(){
		//Arrange
			DateTime fecha=new DateTime(2018,6,20,10,0);
			this.parqueoLogic=new ParqueoLogic(parqueoFacadeInterface,vehiculoFacadeInterface,fecha);
			Vehiculo moto=new VehiculoTestDataBuilder().conPlaca("ABC-123").buildMoto();									
		//Act
			try{
				this.parqueoLogic.ingresar(moto);
				fail();
			}
			catch(ParqueaderoException e){
		//Assert
				assertEquals(CON_RESTRICCIONES,e.getMessage());
			}
	}
	
	/**
	 * Moto.
	 * Se prueba cuando hay "CUPO" y no tiene "RESTRICCIONES".
	 * Dia: Martes
	 * Placa: BBC-123
	 */
	@Test
	public void ingresarMoto2Test(){
		//Arrange
			DateTime fecha=new DateTime(2018,6,19,10,0);
			this.parqueoLogic=new ParqueoLogic(parqueoFacadeInterface,vehiculoFacadeInterface,fecha);
			Vehiculo moto=new VehiculoTestDataBuilder().conPlaca("BBC-123").buildMoto();
		//Act
			boolean flag=this.parqueoLogic.ingresar(moto);
		//Assert
			assertTrue(flag);
	}
	
	/**
	 * Moto.
	 * Se prueba cuando hay "CUPO" y no tiene "RESTRICCIONES".
	 * Dia: Domingo
	 * Placa: ABC-123
	 */
	@Test
	public void ingresarMoto3Test(){
		//Arrange
			DateTime fecha=new DateTime(2018,6,24,0,0,0);
			this.parqueoLogic=new ParqueoLogic(parqueoFacadeInterface,vehiculoFacadeInterface,fecha);
			Vehiculo moto=new VehiculoTestDataBuilder().conPlaca("ABC-123").buildMoto();
		//Act
			boolean flag=this.parqueoLogic.ingresar(moto);
		//Assert
			assertTrue(flag);
	}
	
	/**
	 * Moto.
	 * Se prueba cuando hay "CUPO" y no tiene "RESTRICCIONES".
	 * Dia: Lunes
	 * Placa: ABC-123
	 */
	@Test
	public void ingresarMoto4Test(){
		//Arrange
			DateTime fecha=new DateTime(2018,6,25,0,0,0);
			this.parqueoLogic=new ParqueoLogic(parqueoFacadeInterface,vehiculoFacadeInterface,fecha);
			Vehiculo moto=new VehiculoTestDataBuilder().conPlaca("ABC-123").buildMoto();
		//Act
			boolean flag=this.parqueoLogic.ingresar(moto);
		//Assert
			assertTrue(flag);
	}
	
	/**
	 * Moto.
	 * Se prueba cuando no hay "CUPO"
	 * dia: Viernes
	 * placa: BCD-123
	 */
	@Test
	public void ingresarMoto5Test(){
		//Arrange
			DateTime fecha=new DateTime(2018,6,22,0,0);
			this.parqueoLogic=new ParqueoLogic(parqueoFacadeInterface,vehiculoFacadeInterface,fecha);
			Vehiculo moto=new VehiculoTestDataBuilder().conPlaca("BCD-123").buildMoto();									
		//Act
			try{
				for(int i=1;i<=10;i++){
					this.parqueoLogic.ingresar(new Moto("BCD-"+i, i*20));
				}
				this.parqueoLogic.ingresar(moto);
				fail();
			}
			catch(ParqueaderoException e){
		//Assert
				assertEquals(CUPO_NO_DISPONIBLE,e.getMessage());
			}
	}
	
	/**
	 * Moto.
	 * Se prueba cuando se intenta ingresar otra vez cuando ya esta parqueado
	 * dia: Viernes
	 * placa: BCD-123
	 */
	@Test
	public void ingresarMoto6Test(){
		//Arrange
			DateTime fecha=new DateTime(2018,6,22,0,0);
			this.parqueoLogic=new ParqueoLogic(parqueoFacadeInterface,vehiculoFacadeInterface,fecha);
			Vehiculo moto=new VehiculoTestDataBuilder().conPlaca("BCD-123").buildMoto();									
		//Act
			try{				
				this.parqueoLogic.ingresar(moto);				
				this.parqueoLogic.ingresar(moto);
				fail();
			}
			catch(ParqueaderoException e){
		//Assert
				assertEquals(VEHICULO_PARQUEADO,e.getMessage());
			}
	}
	
	/**
	 * Moto.
	 * Se prueba cuando se intenta registrar la salida cuando "NO" esta parqueado
	 * dia: Viernes
	 * placa: BCD-123
	 */
	@Test
	public void ingresarMoto7Test(){
		//Arrange			
			this.parqueoLogic=new ParqueoLogic(parqueoFacadeInterface);
			Vehiculo moto=new VehiculoTestDataBuilder().conPlaca("BCD-123").buildMoto();									
		//Act
			try{				
				this.parqueoLogic.registrarSalida(moto);			
				fail();
			}
			catch(ParqueaderoException e){
		//Assert
				assertEquals(VEHICULO_NO_ESTA_PARQUEADO,e.getMessage());
			}
	}
	
	/**
	 * Moto.
	 * Se valida que si cobre lo correcto al registrar la salida 
	 * ingreso: 2018/06/25 10:00:00 ; salida:2018/06/25 19:00:00; 9 horas 
	 * Valor esperado: 4000
	 */
	@Test
	public void ingresarMoto8Test(){
		//Arrange
			Vehiculo v=new VehiculoTestDataBuilder().conPlaca("BBC-1").buildMoto();
			DateTime fechaIngreso=new DateTime(2018,6,25,10,0,0);
			DateTime fechaSalida=new DateTime(2018,6,25,19,0,0);
			this.parqueoLogic=new ParqueoLogic(parqueoFacadeInterface,vehiculoFacadeInterface,fechaIngreso);
			double valorEsperado=4000;
		//Act
			this.parqueoLogic.ingresar(v);			
			this.parqueoLogic=new ParqueoLogic(parqueoFacadeInterface,vehiculoFacadeInterface,fechaSalida);
			double valorPagar=parqueoLogic.registrarSalida(v);
		//Assert
			assertEquals(valorEsperado,valorPagar,DELTA);
	}
	
	/**
	 * Moto.
	 * Se valida que si cobre lo correcto al registrar la salida 
	 * ingreso: 2018/06/25 00:00:00 ; salida:2018/06/25 00:00:00; tiempo 0
	 * Valor esperado: 0
	 */
	@Test
	public void ingresarMoto9Test(){
		//Arrange
			Vehiculo v=new VehiculoTestDataBuilder().conPlaca("BBC-1").buildMoto();
			DateTime fechaIngreso=new DateTime(2018,6,25,0,0,0);
			DateTime fechaSalida=new DateTime(2018,6,25,0,0,0);
			this.parqueoLogic=new ParqueoLogic(parqueoFacadeInterface,vehiculoFacadeInterface,fechaIngreso);
			double valorEsperado=0;
		//Act
			this.parqueoLogic.ingresar(v);			
			this.parqueoLogic=new ParqueoLogic(parqueoFacadeInterface,vehiculoFacadeInterface,fechaSalida);
			double valorPagar=parqueoLogic.registrarSalida(v);
		//Assert
			assertEquals(valorEsperado,valorPagar,DELTA);
	}
	
	/**
	 * Moto (501cc)
	 * Se valida que si cobre lo correcto al registrar la salida 
	 * ingreso: 2018/06/25 10:00:00 ; salida:2018/06/25 18:59:00; 8 horas; 59 minutos
	 * Valor esperado: 6500
	 */
	@Test
	public void ingresarMoto10Test(){
		//Arrange
			Vehiculo v=new VehiculoTestDataBuilder().conPlaca("BBC-1").conCilindraje(501).buildMoto();
			DateTime fechaIngreso=new DateTime(2018,6,25,10,0,0);
			DateTime fechaSalida=new DateTime(2018,6,25,18,59,0);
			this.parqueoLogic=new ParqueoLogic(parqueoFacadeInterface,vehiculoFacadeInterface,fechaIngreso);
			double valorEsperado=6500;
		//Act
			this.parqueoLogic.ingresar(v);			
			this.parqueoLogic=new ParqueoLogic(parqueoFacadeInterface,vehiculoFacadeInterface,fechaSalida);
			double valorPagar=parqueoLogic.registrarSalida(v);
		//Assert
			assertEquals(valorEsperado,valorPagar,DELTA);
	}
	
	/**
	 * Carro.
	 * Se prueba cuando no hay "CUPO"
	 * dia: Miercoles
	 * placa: CBC-123
	 */
	@Test
	public void ingresarCarroTest(){
		//Arrange
			DateTime fecha=new DateTime(2018,6,20,10,0);
			this.parqueoLogic=new ParqueoLogic(parqueoFacadeInterface,vehiculoFacadeInterface,fecha);
			Vehiculo carro=new VehiculoTestDataBuilder().conPlaca("CBC-123").buildCarro();	
		//Act
			try{				
				for(int i=1;i<=20;i++){
					this.parqueoLogic.ingresar(new Carro("BBC-"+i, 500));					
				}
				this.parqueoLogic.ingresar(carro);
				fail();
			}
			catch(ParqueaderoException e){
		//Assert
				assertEquals(CUPO_NO_DISPONIBLE,e.getMessage());
			}
	}
	
	/**
	 * Carro.
	 * Se valida que si cobre lo correcto al registrar la salida de un "Carro". 
	 * ingreso:2018/06/20 00:00:00 ; salida:2018/06/22 8:40:00 ; 2 dias, 8 horas, 40 minutos 
	 * Valor esperado=25.000
	 */
	@Test
	public void registrarSalidaCarroTest(){
		//Arrange
			Vehiculo v=new VehiculoTestDataBuilder().conPlaca("BBC-1").buildCarro();
			DateTime fechaIngreso=new DateTime(2018,6,20,0,0,0);
			DateTime fechaSalida=new DateTime(2018,6,22,8,40,0);
			this.parqueoLogic=new ParqueoLogic(parqueoFacadeInterface,vehiculoFacadeInterface,fechaIngreso);
			double valorEsperado=25000;
		//Act
			for(int i=1;i<=20;i++){
				this.parqueoLogic.ingresar(new Carro("BBC-"+i, 500));
			}
			this.parqueoLogic=new ParqueoLogic(parqueoFacadeInterface,vehiculoFacadeInterface,fechaSalida);
			double valorPagar=parqueoLogic.registrarSalida(v);
		//Assert
			assertEquals(valorEsperado,valorPagar,DELTA);
	}
	
	/**
	 * Carro.
	 * Se valida que si cobre lo correcto al registrar la salida en 1 minutos
	 * Valor esperado=1.000
	 */
	@Test
	public void registrarSalidaCarro2Test(){
		//Arrange
			Vehiculo v=new VehiculoTestDataBuilder().conPlaca("BBC-1").buildCarro();
			DateTime fechaIngreso=new DateTime(2018,6,22,16,20,0);
			DateTime fechaSalida=new DateTime(2018,6,22,16,21,0);
			this.parqueoLogic=new ParqueoLogic(parqueoFacadeInterface,vehiculoFacadeInterface,fechaIngreso);
			double valorEsperado=1000;
		//Act
			for(int i=1;i<=20;i++){
				this.parqueoLogic.ingresar(new Carro("BBC-"+i, 500));
			}
			this.parqueoLogic=new ParqueoLogic(parqueoFacadeInterface,vehiculoFacadeInterface,fechaSalida);
			double valorPagar=parqueoLogic.registrarSalida(v);
		//Assert
			assertEquals(valorEsperado,valorPagar,DELTA);
	}
	
	/**
	 * Validar datos de parqueo
	 */
	@Test
	public void validarDatosMoto(){
		//Arrange
			Vehiculo moto=new VehiculoTestDataBuilder().buildMoto();
			DateTime fechaIngreso=new DateTime(2018,6,24,16,20,0);
			this.parqueoLogic=new ParqueoLogic(parqueoFacadeInterface,vehiculoFacadeInterface,fechaIngreso);			
		//Act
			this.parqueoLogic.ingresar(moto);
			Parqueo p=parqueoFacadeInterface.findByPlaca(moto.getPlaca());			
		//Assert
			assertTrue(fechaIngreso.equals(p.getFechaIngreso()));
			assertEquals(p.getVehiculo().getCilindraje(), moto.getCilindraje());
			assertTrue(p.getVehiculo().getPlaca().equals(moto.getPlaca()));
	}
	
	/**
	 * Se valida que la lista de datos parqueados coincida con la lista retornada debidamente formateada para el servicio
	 */
	@Test
	public void validarListaDatosParqueados(){
		//Arrange
			List<Parqueo> listaParqueo=null;
			List<Map<String,String>> listaParqueoFormat=null;
			this.parqueoLogic=new ParqueoLogic(this.parqueoFacadeInterface);
		//Act
			listaParqueo=this.parqueoFacadeInterface.celdasOcupadas();
			listaParqueoFormat=this.parqueoLogic.vehiculosParqueados();
		//Assert
			assertEquals(listaParqueo.size(), listaParqueoFormat.size());
	}

}
