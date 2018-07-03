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
	private static String url;
	
	@Autowired
	private RespositorioParqueo repositorioParqueo;
	
	@BeforeClass
	public static void inicializarDriver(){
		try{
			String path = System.getProperty("user.dir");
			//System.setProperty("webdriver.gecko.driver",path+"/driver/geckodriver");
			System.setProperty("webdriver.chrome.driver",path+"/driver/chromedriver");
			ChromeOptions options=new ChromeOptions();
			options.addArguments("--headless");
			driver = new ChromeDriver(options);
			//driver = new FirefoxDriver();
			
			url="http://localhost:8080/";
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
			driver.get(url);
			WebElement webPlaca=driver.findElement(By.id("placa"));
			webPlaca.sendKeys("BCD787");
			WebElement webBtnRegistrar=driver.findElement(By.id("btnRegistrar"));
			WebDriverWait wait=new WebDriverWait(driver, 10);			
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
			driver.get(url);
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
			Long tiempo=2000l;
			String placa="BCD789";
			driver.get(url);
			WebElement webPlaca=driver.findElement(By.id("placa"));
			webPlaca.sendKeys(placa);
			WebElement webBtnRegistrar=driver.findElement(By.id("btnRegistrar"));
			WebDriverWait wait=new WebDriverWait(driver, 5);			
		//Act
			webBtnRegistrar.click();
			Thread.sleep(tiempo);
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			WebElement btnVehiculo=driver.findElement(By.id("btnVehiculo"+placa));
			Thread.sleep(tiempo);
			btnVehiculo.click();
			Thread.sleep(tiempo);			
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
}
