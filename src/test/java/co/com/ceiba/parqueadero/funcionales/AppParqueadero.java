package co.com.ceiba.parqueadero.funcionales;

import static org.junit.Assert.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.SessionNotCreatedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import co.com.ceiba.parqueadero.dominio.Moto;
import co.com.ceiba.parqueadero.repositorio.RespositorioParqueo;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.DEFINED_PORT)
public class AppParqueadero {
	
	private static final String REGISTRO="Entrada registrada!";
	
	private static WebDriver driver=null;	
	/*
	@LocalServerPort
	private static String PUERTO;	
	*/
	private static final String URL="http://localhost:8080/";
	
	@Autowired
	private RespositorioParqueo repositorioParqueo;
	
	@BeforeClass
	public static void inicializarDriver(){
		try{
			if(System.getProperty("os.name").equalsIgnoreCase("Linux")){
				System.setProperty("webdriver.chrome.driver","libs/chromedriver");
			}
			else{
				System.setProperty("webdriver.chrome.driver","libs/chromedriver.exe");
			}
			ChromeOptions options=new ChromeOptions();
			options.addArguments("--headless");
			driver = new ChromeDriver(options);
			//driver = new FirefoxDriver();			
		}
		catch(SessionNotCreatedException e){
			System.err.println(e);
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	
	@AfterClass
	public static void liberarDriver(){
		if(driver!=null){
			driver.quit();
		}
	}
	
	@Before
	public void inicializarPrueba(){
		this.repositorioParqueo.deleteAll();
	}
	
	/**
	 * Se ingresa una vehiculo
	 */
	@Test
	public void appIngresarVehiculoTest(){
		//Arrange		
			driver.get(URL);		
			WebElement webPlaca=driver.findElement(By.id("placa"));
			webPlaca.sendKeys("BCD787");
			WebElement webBtnRegistrar=driver.findElement(By.id("btnRegistrar"));
		//Act
			webBtnRegistrar.click();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			WebElement msjIngreso=driver.findElement(By.id("msjIngreso"));
		//Assert
			assertEquals(REGISTRO,msjIngreso.getText());		
	}
	
	/**
	 * Se registran 20 vehiculos
	 */
	@Test
	public void appIngresar100VehiculoTest(){
		//Arrange
			driver.get(URL);
			WebElement webPlaca=driver.findElement(By.id("placa"));			
			WebElement webBtnRegistrar=driver.findElement(By.id("btnRegistrar"));
			WebDriverWait wait=new WebDriverWait(driver, 10);
			WebElement msjIngreso;
		//Act
			for(int i=0;i<20;i++){
				webPlaca.sendKeys("BCD"+i);
				webBtnRegistrar.click();
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				msjIngreso=driver.findElement(By.id("msjIngreso"));
		//Assert
				assertEquals(REGISTRO,msjIngreso.getText());
			}
					
	}
	
	/**
	 * Se ingresa una vehiculo y sacarlo
	 * @throws InterruptedException 
	 */
	@Test
	public void appRegistrarSalidaVehiculoTest() throws InterruptedException{
		//Arrange
			Long tiempo=1000l;
			String placa="BCD789";
			driver.get(URL);
			WebElement webPlaca=driver.findElement(By.id("placa"));
			webPlaca.sendKeys(placa);
			WebElement webBtnRegistrar=driver.findElement(By.id("btnRegistrar"));
			WebDriverWait wait=new WebDriverWait(driver, 5);			
		//Act
			webBtnRegistrar.click();			
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			WebElement btnVehiculo=driver.findElement(By.id("btnVehiculo"+placa));			
			btnVehiculo.click();				
			wait.until(ExpectedConditions.alertIsPresent()).accept();
			Thread.sleep(tiempo);
			try{
				btnVehiculo=driver.findElement(By.id("btnVehiculo"+placa));				
				fail();
			}
			catch(NoSuchElementException e){
		//Assert
				assertTrue(true);
			}
	}
	
	/**
	 * Valida que despues de un minuto cobre lo correcto a una moto 600cc, debe ser 2500
	 * @throws InterruptedException
	 */
	/*@Test
	public void validarReciboMoto() throws InterruptedException{
		//Arrange
			int valorEsperado=2500; 
			String placa="BCD-123";
			int cilindraje=600;
			driver.get(URL);
			WebElement webPlaca=driver.findElement(By.id("placa"));
			WebElement webCilindraje=driver.findElement(By.id("cilindraje"));			
			WebElement webBtnRegistrar=driver.findElement(By.id("btnRegistrar"));
			WebElement webRecPlaca=driver.findElement(By.id("recPlaca"));
			WebElement webRecCilindraje=driver.findElement(By.id("recCilindraje"));
			WebElement webRecValorPagar=driver.findElement(By.id("recValorPagar"));
			WebDriverWait wait=new WebDriverWait(driver, 5);
		//Act
			webPlaca.sendKeys(placa);
			webCilindraje.sendKeys(Integer.toString(cilindraje));
			webBtnRegistrar.click();
			Thread.sleep(61000);
			WebElement webBtnSalir=driver.findElement(By.id("btnVehiculo"+placa));
			webBtnSalir.click();
			wait.until(ExpectedConditions.alertIsPresent()).accept();
		//Assert
			Thread.sleep(1500);
			assertEquals(placa,webRecPlaca.getText().trim());
			assertEquals(cilindraje,Integer.parseInt(webRecCilindraje.getText().trim()));
			assertEquals(valorEsperado, Integer.parseInt(webRecValorPagar.getText().trim()));
	}*/
	
}
