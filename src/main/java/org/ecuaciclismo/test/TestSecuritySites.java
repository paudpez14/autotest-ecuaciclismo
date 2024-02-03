package org.ecuaciclismo.test;//package <set your test package>;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.time.Duration;


public class TestSecuritySites {
    private String reportDirectory = "reports";
    private String reportFormat = "xml";
    private String testName = "Pruebas Ecuaciclismo";
    protected AndroidDriver<AndroidElement> driver = null;
    private static ExtentReports extentReports;
    private ExtentTest extentTest;

    DesiredCapabilities dc = new DesiredCapabilities();

    @BeforeAll
    public static void setUpClass() {
        // Configuración del informe ExtentReports (se ejecuta una vez al principio)
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("reports/extent-report-security-site.html");
        extentReports = new ExtentReports();
        extentReports.attachReporter(htmlReporter);
    }

    @BeforeEach
    public void setUp() throws MalformedURLException {
        dc.setCapability("appium:automationName", "uiautomator2");
        dc.setCapability("appium:deviceName", "sdk_gphone64_x86_64");
        dc.setCapability("appium:reportDirectory", reportDirectory);
        dc.setCapability("appium:reportFormat", reportFormat);
        dc.setCapability("appium:testName", testName);
        dc.setCapability("appium:udid", "emulator-5554");
        dc.setCapability("autoGrantPermissions", true);
        dc.setCapability("appium:appPackage", "com.ecuaciclismo.ecuaciclismo");
        dc.setCapability("appium:appActivity", ".MainActivity");
        driver = new AndroidDriver<>(new URL("http://192.168.1.3:4723/"), dc);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.setLogLevel(Level.INFO);

        // Configuración del informe ExtentReports


    }

    @Test
    public void CreateSecuritySite() {
        extentTest = extentReports.createTest("Crear Sitio Seguro");
        // Ingresa el correo electrónico
        MobileElement emailInput = driver.findElementByXPath("//*[@text='Correo electrónico']");
        emailInput.sendKeys("paudpez@hotmail.com");
        // Ingresa la contraseña
        MobileElement passwordInput = driver.findElementByXPath("//*[@text='Contraseña']");
        passwordInput.sendKeys("paudpez140799P");
        // Haz clic en el botón "Iniciar Sesión"
        MobileElement loginButton = driver.findElementByXPath("//*[@text='Iniciar sesión']");
        loginButton.click();
        WebDriverWait wait = new WebDriverWait(driver, 30); // Espera hasta 30 segundos
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("ActivityIndicator")));
        extentTest.log(Status.PASS, "Inicio de Sesión Correcto");
        MobileElement sideMenu = driver.findElementByXPath("//android.widget.Button/android.widget.ImageView");
        sideMenu.click();
        try {
            // Identifica el elemento Picker (usando XPath, ajusta según la estructura real de tu app)
            driver.findElementByXPath("//android.widget.TextView[@text=\"Lugares\"]").click();
            extentTest.log(Status.PASS, "Se ha ingresado al Módulo de Lugares Seguros");
        } catch (Exception e) {
            extentTest.log(Status.FAIL, "No se ha podido ingresar al Módulo de Lugares Seguros. Detalles: " + e.getMessage());
        }
        try {
            MobileElement mapElement = driver.findElementByAccessibilityId("Google Map");
            int centerX = mapElement.getLocation().getX() + mapElement.getSize().getWidth() / 2;
            int centerY = mapElement.getLocation().getY() + mapElement.getSize().getHeight() / 2;
            driver.performTouchAction(new io.appium.java_client.TouchAction<>(driver).press(PointOption.point(centerX, centerY)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(500))).release());
            extentTest.log(Status.PASS, "Se ha seleccionado un sitio ");
        } catch (Exception e) {
            extentTest.log(Status.FAIL, "No se ha podido seleccionar un sitio. Detalles: " + e.getMessage());
        }
        try {
            driver.findElementByXPath("//android.widget.TextView[@text=\"Recomendar\"]").click();
            extentTest.log(Status.PASS, "Se ha presionado el botón de recomendar");
        } catch (Exception e) {
            extentTest.log(Status.FAIL, "No se ha podido presionar el botón de recomendar. Detalles: " + e.getMessage());
        }
        try {
            MobileElement nameSite = driver.findElementByXPath("//android.widget.EditText[@text=\"Escribe el nombre del lugar...\"]");
            nameSite.sendKeys("Prueba de Sitio Seguro - Appium ");
            extentTest.log(Status.PASS, "Se ha especificado el nombre del sitio seguro");
        } catch (Exception e) {
            extentTest.log(Status.FAIL, "No se ha especificado el nombre del sitio seguro. Detalles: " + e.getMessage());
        }
        try {
            driver.findElementByXPath("//android.widget.Spinner").click();
            driver.findElementByXPath("//android.widget.CheckedTextView[@resource-id=\"android:id/text1\" and @text=\"Local\"]").click();

            extentTest.log(Status.PASS, "Se ha seleccionado el tipo de sitio seguro");
        } catch (Exception e) {
            extentTest.log(Status.FAIL, "No se ha seleccionado el tipo de sitio seguro. Detalles: " + e.getMessage());
        }
        try {
            MobileElement description = driver.findElementByXPath("//android.widget.EditText[@text=\"Agrega una descripción...\"]");
            description.sendKeys("Descripción de Prueba de Sitio Seguro");
            extentTest.log(Status.PASS, "Se ha especificado la descripción del sitio seguro");
        } catch (Exception e) {
            extentTest.log(Status.FAIL, "No se ha especificado la descripción del sitio seguro. Detalles: " + e.getMessage());
        }
        try {
            MobileElement city = driver.findElementByXPath("//android.widget.EditText[@text=\"Ciudad del lugar...\"]");
            city.sendKeys("Guayaquil - Appium");
            extentTest.log(Status.PASS, "Se ha especificado el ciudad del sitio seguro");
        } catch (Exception e) {
            extentTest.log(Status.FAIL, "No se ha especificado el ciudad del sitio seguro. Detalles: " + e.getMessage());
        }
        try {
            driver.findElementByXPath("//*[@text='Servicio que ofrece...']").click();
            driver.findElementByXPath("//android.widget.CheckedTextView[@resource-id=\"android:id/text1\" and @text=\"Tienda\"]").click();
            extentTest.log(Status.PASS, "Se ha seleccionado tipo de servicio");
        } catch (Exception e) {
            extentTest.log(Status.FAIL, "No se ha seleccionado tipo de servicio. Detalles: " + e.getMessage());
        }
        try {
            driver.findElementByXPath("//android.widget.TextView[@text=\"Recomendar Lugar\"]").click();
            extentTest.log(Status.PASS, "Se ha creado el sitio seguro");
        } catch (Exception e) {
            extentTest.log(Status.FAIL, "No se ha creado el sitio seguro. Detalles: " + e.getMessage());
        }
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void ToAprobeSecuritySite() {
        extentTest = extentReports.createTest("Aprobar Solicitud");
        // Ingresa el correo electrónico
        MobileElement emailInput = driver.findElementByXPath("//*[@text='Correo electrónico']");
        emailInput.sendKeys("xavmb98@gmail.com");
        // Ingresa la contraseña
        MobileElement passwordInput = driver.findElementByXPath("//*[@text='Contraseña']");
        passwordInput.sendKeys("123456789");
        // Haz clic en el botón "Iniciar Sesión"
        MobileElement loginButton = driver.findElementByXPath("//*[@text='Iniciar sesión']");
        loginButton.click();
        WebDriverWait wait = new WebDriverWait(driver, 30); // Espera hasta 30 segundos
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("ActivityIndicator")));
        extentTest.log(Status.PASS, "Inicio de Sesión Correcto");
        MobileElement sideMenu = driver.findElementByXPath("//android.widget.Button/android.widget.ImageView");
        sideMenu.click();
        try {
            // Identifica el elemento Picker (usando XPath, ajusta según la estructura real de tu app)
            driver.findElementByXPath("//android.widget.TextView[@text=\"Solicitudes\"]").click();
            extentTest.log(Status.PASS, "Se ha ingresado al Módulo de Solicitudes");
        } catch (Exception e) {
            extentTest.log(Status.FAIL, "No se ha podido ingresar al Módulo de Solicitudes. Detalles: " + e.getMessage());
        }
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        try {
            // Identifica el elemento Picker (usando XPath, ajusta según la estructura real de tu app)
            driver.findElementByXPath("//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[5]/android.view.ViewGroup").click();
            extentTest.log(Status.PASS, "Se ha ingresado a una Solicitud por Aprobar");
        } catch (Exception e) {
            extentTest.log(Status.FAIL, "No se ha podido ingresar a una Solicitud por Aprobar. Detalles: " + e.getMessage());
        }
        try {
            // Identifica el elemento Picker (usando XPath, ajusta según la estructura real de tu app)
            driver.findElementByXPath("//android.widget.TextView[@text=\"Aprobar\"]").click();
            extentTest.log(Status.PASS, "Se ha hecho click en el botón de Aprobar");
        } catch (Exception e) {
            extentTest.log(Status.FAIL, "No se hacer click en el botón de Aprobar'. Detalles: " + e.getMessage());
        }

        try {
            driver.findElementByXPath("//android.widget.TextView[@text=\"Confirmar\"]").click();
            Thread.sleep(5000);
            extentTest.log(Status.PASS, "Se ha confirmado la solicitud");
        } catch (Exception e) {
            extentTest.log(Status.FAIL, "No se ha podido confirmar la solicitud'. Detalles: " + e.getMessage());
        }

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    @AfterEach
    public void tearDown() {
        driver.quit();
        extentReports.flush();
    }

}