package co.com.ceiba.parqueadero.logic;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import co.com.ceiba.parqueadero.dominio.Vehiculo;
import co.com.ceiba.parqueadero.exception.ParqueaderoException;
import co.com.ceiba.parqueadero.testdatabuilder.VehiculoTestDataBuilder;

public class ParqueoLogicTest {
	
	public static final String CON_RESTRICCIONES="Tiene restricciones: No es un dia habil para su vehiculo";
	private static final int LUNES=1;
	private static final int MIERCOLES=3;
	private static final int DOMINGO=7;
	
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
	public void sinRestriccionesTestVehiculoCulquierDia(){
		//Arrange
			Vehiculo v=new VehiculoTestDataBuilder().conPlaca("BBB-123").buildCarro();		
		//Act			
			boolean flag=parqueoLogic.sinRestricciones(v, LUNES);						
		//Assert
			assertTrue(flag);				
	}
	
}
