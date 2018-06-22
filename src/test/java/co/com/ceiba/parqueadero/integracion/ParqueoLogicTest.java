package co.com.ceiba.parqueadero.integracion;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import co.com.ceiba.parqueadero.dominio.Carro;
import co.com.ceiba.parqueadero.dominio.Parqueo;
import co.com.ceiba.parqueadero.dominio.Vehiculo;
import co.com.ceiba.parqueadero.exception.ParqueaderoException;
import co.com.ceiba.parqueadero.facade.ParqueoFacadeInterface;
import co.com.ceiba.parqueadero.facade.VehiculoFacadeInterface;
import co.com.ceiba.parqueadero.logic.ParqueoLogic;
import co.com.ceiba.parqueadero.testdatabuilder.ParqueoTestDataBuilder;
import co.com.ceiba.parqueadero.testdatabuilder.VehiculoTestDataBuilder;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ParqueoLogicTest {
	
	public static final String CON_RESTRICCIONES="Tiene restricciones: No es un dia habil para su vehiculo";
	public static final String CUPO_NO_DISPONIBLE="Cupo no disponible";	
	
	private ParqueoLogic parqueologic;
	
	@Autowired
	private VehiculoFacadeInterface vehiculoFacadeInterface;
	
	@Mock
	private ParqueoFacadeInterface parqueoFacadeInterface; 
	
	@Mock
	private VehiculoFacadeInterface vehiculoFacadeInterfaceMock;
	
	@Before
	public void inicializacion(){
		parqueologic=new ParqueoLogic(parqueoFacadeInterface);
	}
	
	/**
	 * Se prueba cuando hay cupo pero tiene restriccion por placa que inicia con la letra "A".
	 */
	@Test
	public void ingresarMoto1Test(){
		//Arrange
			DateTime fecha=new DateTime(2018,6,20,10,0);
			parqueologic=new ParqueoLogic(parqueoFacadeInterface,fecha);
			Vehiculo moto=new VehiculoTestDataBuilder().conPlaca("ABC-123").buildMoto();			
			List<Parqueo> lista=new ArrayList<>();			
			Mockito.when(parqueoFacadeInterface.celdasOcupadas()).thenReturn(lista);			
		//Act
			try{
				parqueologic.ingresar(moto);
				fail();
			}
			catch(ParqueaderoException e){
		//Assert
				assertEquals(CON_RESTRICCIONES,e.getMessage());
			}
	}
	
	/**
	 * Se prueba cuando hay cupo y no tiene restriccion.
	 */
	@Test
	public void ingresarMoto2Test(){
		//Arrange
			DateTime fecha=new DateTime(2018,6,18,10,0);
			parqueologic=new ParqueoLogic(parqueoFacadeInterface,vehiculoFacadeInterface,fecha);
			Vehiculo moto=new VehiculoTestDataBuilder().conPlaca("BBC-123").buildMoto();			
			List<Parqueo> lista=new ArrayList<>();			
			Mockito.when(parqueoFacadeInterface.celdasOcupadas()).thenReturn(lista);
		//Act
			boolean flag=parqueologic.ingresar(moto);
		//Assert
			assertTrue(flag);
	}
	
	/**
	 * Se prueba cuando no hay cupo para carro.
	 */
	@Test
	public void ingresarCarroTest(){
		//Arrange
			DateTime fecha=new DateTime(2018,6,20,10,0);
			parqueologic=new ParqueoLogic(parqueoFacadeInterface,fecha);
			Vehiculo moto=new VehiculoTestDataBuilder().conPlaca("ABC-123").buildCarro();			
			List<Parqueo> lista=new ArrayList<>();
			int numPlaca=100;
			for(int i=0;i<22;i++){
				lista.add(new Parqueo(new DateTime(), new DateTime(), 5000, new Carro("BBC-"+numPlaca, 500)));
				numPlaca++;
			}
			Mockito.when(parqueoFacadeInterface.celdasOcupadas()).thenReturn(lista);			
		//Act
			try{
				parqueologic.ingresar(moto);
			}
			catch(ParqueaderoException e){
		//Assert
				assertEquals(CUPO_NO_DISPONIBLE,e.getMessage());
			}
	}
	
	/**
	 * Se valida que si cobre lo correcto al registrar la salida
	 */
	@Test
	public void registrarSalidaCarroTest(){
		//Arrange
			Vehiculo v=new VehiculoTestDataBuilder().buildCarro();
			DateTime fechaIngreso=new DateTime(2018,6,20,0,0,0);
			DateTime fechaSalida=new DateTime(2018,6,22,8,40,0);
			Parqueo p=new ParqueoTestDataBuilder().conFechaIngreso(fechaIngreso).buildConCarro();		
			Mockito.when(parqueoFacadeInterface.findByPlaca(v.getPlaca())).thenReturn(p);
			ParqueoLogic parqueoLogic=new ParqueoLogic(parqueoFacadeInterface,fechaSalida);
			double valorEsperado=25000;
		//Act
			double valorPagar=parqueoLogic.registrarSalida(v);
		//Assert
			assertTrue(valorEsperado==valorPagar);
	}

}
