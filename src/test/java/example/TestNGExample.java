package example;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.List;

public class TestNGExample {
    private WebDriver driver;

    @BeforeSuite
    public void initDriver() throws Exception {

        System.setProperty("webdriver.chrome.driver", "/home/yendry/chromedriver");
        this.driver = new ChromeDriver();
        this.driver.manage().window().maximize();
    }

    @Test
    public void searchTestNGInGoogle() throws InterruptedException {
        // Entre en paginasamarillas.com.co
        this.driver.get("https://www.paginasamarillas.com.co");

        // En el input donde dice: <Que Buscas?> ingresa “Numero de teléfono”.
        this.driver.findElement(By.id("keyword")).sendKeys("Numero de teléfono");

        // En el input donde dice: <Donde?> ingresa “Bogotá”
        this.driver.findElement(By.id("locality")).sendKeys("Bogotá");

        //
        this.driver.findElement(By.id("buscar")).click();

        Thread.sleep(2000);

        List<WebElement> listOfElements = this.driver.findElements(By.xpath("//div[contains(@class, 'col-sm-12 figBox')]"));
        // Imprime el numero de resultado de la búsqueda.
        System.out.println("Number of elements: " + listOfElements.size());

        for (int i = 0; i < listOfElements.size(); i++) {
            // Crea un ciclo “For” donde vas a buscar de la lista el elemento que
            if (listOfElements.get(i).getText().contains("Textiles Jj Telas S.A.S.")) {
                // trae como texto “Textiles Jj Telas S.A.S.”
                System.out.println(this.driver.findElement(By.xpath("//span[contains(.,'Textiles Jj Telas S.A.S.')]")).getText());

                // Imprime la dirección que aparece en la caja de información.
                System.out.println(this.driver.findElement(By.xpath("//div[6]/div/div/div[2]/div[2]/div/span")).getText());
            }

        }

        JavascriptExecutor js = (JavascriptExecutor) this.driver;
        js.executeScript("window.scrollBy(0,1000)");

        Thread.sleep(2000);
        // Luego de imprimir la dirección haz clic en el link
        ClickTextiles();
        Thread.sleep(2000);

        // Llena la información de contacto con “Textiles Jj Telas S.A.S.”
        // (Nombre, ingresa tu email de contacto, ingresa tu teléfono de
        // contacto, escribe un mensaje de razón)
        this.driver.findElement(By.id("bipName")).sendKeys("bipName");
        this.driver.findElement(By.id("bipEmail")).sendKeys("email@grr.la");
        this.driver.findElement(By.id("bipPhone")).sendKeys("1234567");
        this.driver.findElement(By.id("bipMessage")).sendKeys("bipMessage bipMessage bipMessage bipMessage");
        this.driver.findElement(By.name("terms")).submit();

        // En vez de darle clic al botón de enviar solo vas a imprimir el
        // nombre del botón, en este caso va hacer “Enviar”
        System.out.println(this.driver.findElement(By.id("contactar")).getText());
        Thread.sleep(2000);

    }

    public void ClickTextiles() {
        ((JavascriptExecutor) driver).executeScript("document.getElementsByClassName('semibold')[15].click()");
    }

    @AfterSuite
    public void quitDriver() throws Exception {
        driver.quit();
    }
}
