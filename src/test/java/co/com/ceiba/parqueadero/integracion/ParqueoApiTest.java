package co.com.ceiba.parqueadero.integracion;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import co.com.ceiba.parqueadero.api.ParqueoApi;
import co.com.ceiba.parqueadero.dominio.Respuesta;
import co.com.ceiba.parqueadero.exception.ParqueaderoException;
import co.com.ceiba.parqueadero.interfaces.IParqueo;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ParqueoApiTest {
	
	private static final Respuesta RESPUESTA=new Respuesta("Entrada registrada!");
	private static final String PLACA_NO_VALIDA="Valor de placa no valido";
	private static final String CILINDRAJE_NO_VALIDO="Valor del Cilindraje no valido";
	
	private ParqueoApi api;
	
	@Autowired
	IParqueo iParqueo;
	
	@Before
	public void inicializacion(){
		this.api=new ParqueoApi(iParqueo); 
	}
	
	@Test
	public void getVehiculoTest() throws Exception{
		//Arrange			
		//Act
			List<Map<String,String>> lista=api.obtenerVehiculosParqueados();		
		//Assert
			assertNotNull(lista);		
	}
	
	@Test
	public void ingresarVehiculo1Test(){
		//Arrange			
			Map<String,String> payload=new HashMap<>();
			payload.put("placa","BBC");
		//Act
			Respuesta res=api.ingresarVehiculo(payload);
		//Assert
			assertEquals(res.getMsj(),RESPUESTA.getMsj());
	}
	
	/**
	 * Se valida cuando no se ingresa la placa
	 */
	@Test
	public void ingresarVehiculo2Test(){
		//Arrange			
			Map<String,String> payload=new HashMap<>();			
		//Act
			try{
				Respuesta res=api.ingresarVehiculo(payload);
				fail();
			}
			catch(ParqueaderoException e){
		//Assert
				assertEquals(PLACA_NO_VALIDA,e.getMessage());
			}
	}
	
	/**
	 * Se valida cuando la placa esta vacia
	 */
	@Test
	public void ingresarVehiculo3Test(){
		//Arrange			
			Map<String,String> payload=new HashMap<>();	
			payload.put("placa", "");
		//Act
			try{
				Respuesta res=api.ingresarVehiculo(payload);
				fail();
			}
			catch(ParqueaderoException e){
		//Assert
				assertEquals(PLACA_NO_VALIDA,e.getMessage());
			}
	}
	
	/**
	 * Se valida cuando se ingresa placa y cilindraje deberia crear una Moto
	 */
	@Test
	public void ingresarVehiculo4Test(){
		//Arrange	
			boolean flag=false;
			String placa="bbc-123";
			String cilindraje="500";
			Map<String,String> payload=new HashMap<>();	
			payload.put("placa", placa);
			payload.put("cilindraje", cilindraje);
		//Act
			Respuesta res=api.ingresarVehiculo(payload);
			List<Map<String,String>> listVehiculosParqueados=api.obtenerVehiculosParqueados();
		//Assert
			if(!res.getMsj().equals(RESPUESTA.getMsj()))
				fail();
			
			for(Map<String,String> vehiculo:listVehiculosParqueados){
				if(vehiculo.get("placa").equalsIgnoreCase(placa) && 
						vehiculo.get("cilindraje").equals(cilindraje) && 
						vehiculo.get("tipo").equalsIgnoreCase("Moto")){
					flag=true;
					break;
				}
					
			}
			assertTrue(flag);			
	}
	
	/**
	 * Se valida cuando se ingresa placa bien escrita y un cilindraje no valido 
	 */
	@Test
	public void ingresarVehiculo5Test(){
		//Arrange
			String placa="bbc-123";
			String cilindraje="NO VALIDO";
			Map<String,String> payload=new HashMap<>();	
			payload.put("placa", placa);
			payload.put("cilindraje", cilindraje);
		//Act
			try{
				api.ingresarVehiculo(payload);
				fail();
			}
			catch(ParqueaderoException e){
		//Assert
				assertEquals(CILINDRAJE_NO_VALIDO, e.getMessage());
			}
	}
	
	/**
	 * Se valida cuando se realiza la salida de un Carro
	 */
	@Test
	public void registrarSalida1Test(){
		//Arrange
			Map<String,String> payload=new HashMap<>();	
			payload.put("placa", "bbc-123");
			payload.put("tipo","carro");
		//Act
			try{
				api.ingresarVehiculo(payload);
				api.registrarSalidaVehiculo(payload);
		//Assert
				assertTrue(true);
			}
			catch(Exception e){
				fail();
			}
	}
	
	/**
	 * Se valida cuando se realiza la salida de una Moto
	 */
	@Test
	public void registrarSalida2Test(){
		//Arrange
			Map<String,String> payload=new HashMap<>();	
			payload.put("placa", "qwe-123");
			payload.put("tipo","moto");
		//Act
			try{
				api.ingresarVehiculo(payload);
				api.registrarSalidaVehiculo(payload);
		//Assert
				assertTrue(true);
			}
			catch(Exception e){
				fail();
			}
	}
}
