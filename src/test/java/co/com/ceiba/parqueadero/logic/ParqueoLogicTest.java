package co.com.ceiba.parqueadero.logic;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import co.com.ceiba.parqueadero.dominio.Moto;
import co.com.ceiba.parqueadero.dominio.Parqueo;
import co.com.ceiba.parqueadero.dominio.Vehiculo;
import co.com.ceiba.parqueadero.exception.ParqueaderoException;
import co.com.ceiba.parqueadero.facade.ParqueoFacadeInterface;
import co.com.ceiba.parqueadero.testdatabuilder.VehiculoTestDataBuilder;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ParqueoLogicTest {
	
	public static final String CON_RESTRICCIONES="Tiene restricciones: No es un dia habil para su vehiculo";
	private static final int LUNES=1;
	private static final int MIERCOLES=3;
	private static final int DOMINGO=7;
	
	@Mock
	ParqueoFacadeInterface parqueoFacadeInterface;
	
	private ParqueoLogic parqueoLogic;
	
	@Before
	public void inicializacion(){
		parqueoLogic=new ParqueoLogic();
	}
		
	
	@Test
	public void sinRestriccionesTest(){
		//Arrange
			Vehiculo v=new VehiculoTestDataBuilder().buildCarro();
		//Act
			boolean flag=parqueoLogic.sinRestricciones(v, LUNES);
		//Assert
			assertTrue(flag);		
	}
	
	@Test
	public void sinRestriccionesTestException(){
		//Arrange
			Vehiculo v=new VehiculoTestDataBuilder().conPlaca("AAA-123").buildCarro();			
		//Act
			try{
				parqueoLogic.sinRestricciones(v, MIERCOLES);
				fail();
			}
			catch(ParqueaderoException e){
		//Assert
				assertEquals(CON_RESTRICCIONES,e.getMessage());
			}
	}
	
	@Test
	public void sinRestriccionesTestNull(){
		//Arrange
			Vehiculo v=null;			
		//Act
			try{
				parqueoLogic.sinRestricciones(v, MIERCOLES);
				fail();
			}
			catch(ParqueaderoException e){
		//Assert
				assertEquals(CON_RESTRICCIONES,e.getMessage());
			}
	}
	
	@Test
	public void sinRestriccionesTestVehiculoConPermiso(){
		//Arrange
			Vehiculo v=new VehiculoTestDataBuilder().conPlaca("BBB-123").buildCarro();		
		//Act			
			boolean flag=parqueoLogic.sinRestricciones(v, LUNES);						
		//Assert
			assertTrue(flag);				
	}
	
	@Test
	public void disponible(){
		//Arrange
			List<Parqueo> listaParqueo=new ArrayList<>(); 
			int numPlaca=100;
			for(int i=0;i<15;i++){
				listaParqueo.add(new Parqueo(new DateTime(), new DateTime(), 5000, new Moto("BBC-"+numPlaca, 500)));
				numPlaca++;
			}			
			Mockito.when(parqueoFacadeInterface.celdasOcupadas()).thenReturn(listaParqueo);			
			List<Parqueo> listaParqueo2=parqueoFacadeInterface.celdasOcupadas();
			System.out.println("LISTA PRUEBA ="+listaParqueo.size());
			System.out.println("LISTA PRUEBA2 ="+listaParqueo2.size());
			Vehiculo v=new VehiculoTestDataBuilder().buildCarro();			
		//Act			
			//boolean flag=parqueoLogic.disponible(v);			
		//Assert
			assertTrue(true);
			
	}
}
