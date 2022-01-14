package wniemiec.app.nforum;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;


public class UITests {

	//-------------------------------------------------------------------------
	//		Attributes
	//-------------------------------------------------------------------------
	private WebDriver driver;
	private Map<String, Object> vars;
	JavascriptExecutor js;


	//-------------------------------------------------------------------------
	//		Test hooks
	//-------------------------------------------------------------------------
	@Before
	public void setUp() {
		driver = new ChromeDriver();
		js = (JavascriptExecutor) driver;
		vars = new HashMap<String, Object>();
	}

	@After
	public void tearDown() {
		driver.quit();
	}


	//-------------------------------------------------------------------------
	//		Tests
	//-------------------------------------------------------------------------
	@Test
	public void signInTest() {
		driver.get("http://localhost:8080/auth/signin");
		driver.manage().window().setSize(new Dimension(1382, 744));
		driver.findElement(By.id("login")).click();
		driver.findElement(By.id("login")).sendKeys("william");
		driver.findElement(By.cssSelector(".content")).click();
		driver.findElement(By.id("password")).click();
		driver.findElement(By.id("password")).sendKeys("123");
		driver.findElement(By.cssSelector(".content")).click();
		driver.findElement(By.cssSelector(".btn:nth-child(3)")).click();
		driver.findElement(By.cssSelector(".container:nth-child(1)")).click();

		assertThat(
			driver.findElement(By.cssSelector(".container:nth-child(1)")).getText(), 
			is("Topics")
		);
	}

	@Test
	public void newTopicTest() {
		driver.get("http://localhost:8080/auth/signin");
		driver.manage().window().setSize(new Dimension(1382, 744));
		driver.findElement(By.id("login")).click();
		driver.findElement(By.id("login")).sendKeys("william");
		driver.findElement(By.cssSelector(".content")).click();
		driver.findElement(By.id("password")).click();
		driver.findElement(By.id("password")).sendKeys("123");
		driver.findElement(By.cssSelector(".btn:nth-child(3)")).click();
		driver.findElement(By.cssSelector(".btn:nth-child(1)")).click();
		driver.findElement(By.cssSelector(".container:nth-child(1)")).click();
		
		assertThat(
			driver.findElement(By.cssSelector(".container:nth-child(1)")).getText(), 
			is("New topic")
		);
		{
			List<WebElement> elements = driver.findElements(By.cssSelector(".btn"));
			assert (elements.size() > 0);
		}
	}

	@Test
	public void rankingTest() {
		driver.get("http://localhost:8080/auth/signin");
		driver.manage().window().setSize(new Dimension(1382, 744));
		driver.findElement(By.id("login")).click();
		driver.findElement(By.id("login")).sendKeys("william");
		driver.findElement(By.cssSelector(".content")).click();
		driver.findElement(By.id("password")).click();
		driver.findElement(By.id("password")).sendKeys("123");
		driver.findElement(By.cssSelector(".btn:nth-child(3)")).click();
		driver.findElement(By.linkText("Ranking")).click();
		driver.findElement(By.cssSelector(".container:nth-child(1)")).click();
		
		assertThat(
			driver.findElement(By.cssSelector(".container:nth-child(1)")).getText(), 
			is("Ranking")
		);
	}

	@Test
	public void openTopicTest() {
		driver.get("http://localhost:8080/auth/signin");
		driver.manage().window().setSize(new Dimension(1382, 744));
		driver.findElement(By.id("login")).sendKeys("william");
		driver.findElement(By.cssSelector(".content")).click();
		driver.findElement(By.id("password")).click();
		driver.findElement(By.id("password")).sendKeys("123");
		driver.findElement(By.cssSelector(".btn:nth-child(3)")).click();
		driver.findElement(By.linkText("topic1")).click();
		driver.findElement(By.cssSelector("h1:nth-child(2)")).click();
		driver.findElement(By.cssSelector("h1:nth-child(2)")).click();
		{
			WebElement element = driver.findElement(By.cssSelector("h1:nth-child(2)"));
			Actions builder = new Actions(driver);
			builder.doubleClick(element).perform();
		}
		
		assertThat(
			driver.findElement(By.cssSelector("h1:nth-child(2)")).getText(), 
			is("Answers")
		);
		driver.findElement(By.cssSelector("label")).click();
		driver.findElement(By.cssSelector("label")).click();
		driver.findElement(By.cssSelector("label")).click();
		{
			WebElement element = driver.findElement(By.cssSelector("label"));
			Actions builder = new Actions(driver);
			builder.doubleClick(element).perform();
		}
		assertThat(
			driver.findElement(By.cssSelector("label")).getText(), 
			is("Message")
		);
		{
			List<WebElement> elements = driver.findElements(By.cssSelector(".btn"));
			assert (elements.size() > 0);
		}
	}
}