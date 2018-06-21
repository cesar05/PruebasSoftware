package co.com.ceiba.parqueadero.logic;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import co.com.ceiba.parqueadero.dominio.Vehiculo;
import co.com.ceiba.parqueadero.testdatabuilder.VehiculoTestDataBuilder;

public class ParqueoLogicTest {
	
	private static final int LUNES=1;
	private static final int DOMINGO=7;
	
	@Test
	public void sinRestriccionesTest(){
		//Arrange
			Vehiculo v=new VehiculoTestDataBuilder().buildCarro();
			ParqueoLogic parqueoLogic=new ParqueoLogic();
		//Act
			boolean flag=parqueoLogic.sinRestricciones(v, LUNES);
		//Assert
			assertTrue(flag);		
	}
}
