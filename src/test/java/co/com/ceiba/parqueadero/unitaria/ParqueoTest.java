package co.com.ceiba.parqueadero.unitaria;

import static org.junit.Assert.*;

import org.joda.time.DateTime;
import org.junit.Test;

import co.com.ceiba.parqueadero.dominio.Carro;
import co.com.ceiba.parqueadero.dominio.Moto;
import co.com.ceiba.parqueadero.dominio.Parqueo;
import co.com.ceiba.parqueadero.dominio.Vehiculo;
import co.com.ceiba.parqueadero.testdatabuilder.ParqueoTestDataBuilder;

public class ParqueoTest {
	
	private static final DateTime FECHA_INGRESO=new DateTime(2018,06,27,10,12,50);
	private static final DateTime FECHA_SALIDA=new DateTime(2018,06,29,19,25,30);	
	private static final double VALOR_PAGAR=2000;
	private static final String PLACA="ABC-123";
	private static final int CILINDRAJE=250;
	private static final Vehiculo MOTO=new Moto(PLACA,CILINDRAJE);
	
	
	@Test
	public void crearParqueo(){	
		//Arrange
		ParqueoTestDataBuilder pTest=new ParqueoTestDataBuilder().conFechaIngreso(FECHA_INGRESO)
				.conFechaSalida(FECHA_SALIDA).conValorAPagar(VALOR_PAGAR).conVehiculo(MOTO);
		//Act
			Parqueo p=pTest.build();
		//Assert
			assertTrue(FECHA_INGRESO.equals(p.getFechaIngreso()));
			assertTrue(FECHA_SALIDA.equals(p.getFechaSalida()));
			assertEquals(VALOR_PAGAR, p.getValorPagar(),0.01);
			assertEquals(PLACA,p.getVehiculo().getPlaca());
			assertEquals(CILINDRAJE,p.getVehiculo().getCilindraje());	
	}
	
	@Test
	public void crearParqueoConMoto(){	
		//Arrange
		ParqueoTestDataBuilder pTest=new ParqueoTestDataBuilder().conFechaIngreso(FECHA_INGRESO)
				.conFechaSalida(FECHA_SALIDA).conValorAPagar(VALOR_PAGAR);
		//Act
			Parqueo p=pTest.buildConMoto();
		//Assert
			assertTrue(FECHA_INGRESO.equals(p.getFechaIngreso()));
			assertTrue(FECHA_SALIDA.equals(p.getFechaSalida()));
			assertEquals(VALOR_PAGAR, p.getValorPagar(),0.01);	
			assertTrue(p.getVehiculo() instanceof Moto);
	}
	
	@Test
	public void crearParqueoConCarro(){
		//Arrange
		ParqueoTestDataBuilder pTest=new ParqueoTestDataBuilder().conFechaIngreso(FECHA_INGRESO)
				.conFechaSalida(FECHA_SALIDA).conValorAPagar(VALOR_PAGAR);
		//Act
			Parqueo p=pTest.buildConCarro();
		//Assert
			assertTrue(FECHA_INGRESO.equals(p.getFechaIngreso()));
			assertTrue(FECHA_SALIDA.equals(p.getFechaSalida()));
			assertEquals(VALOR_PAGAR, p.getValorPagar(),0.01);
			assertTrue(p.getVehiculo() instanceof Carro);
	}
}
