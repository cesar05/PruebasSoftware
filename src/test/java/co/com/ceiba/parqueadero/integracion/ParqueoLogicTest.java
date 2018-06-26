package co.com.ceiba.parqueadero.integracion;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

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
	
	public static final String CON_RESTRICCIONES="Tiene restricciones: No es un dia habil para su vehiculo";
	public static final String CUPO_NO_DISPONIBLE="Cupo no disponible";	
	
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
	 * Dia: lunes
	 * Placa: BBC-123
	 */
	@Test
	public void ingresarMoto2Test(){
		//Arrange
			DateTime fecha=new DateTime(2018,6,18,10,0);
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
	 * Carro.
	 * Se prueba cuando no hay "CUPO"
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
			assertTrue(valorEsperado==valorPagar);
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
			assertTrue(valorEsperado==valorPagar);
	}

}
