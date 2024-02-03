package org.ecuaciclismo.test;//package <set your test package>;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.By;
import org.junit.jupiter.api.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.net.MalformedURLException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

public class TestsAlerts {
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
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("reports/extent-report.html");
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
    public void ToAprobeAlert() {
        extentTest = extentReports.createTest("Aprobar Alerta");
        // Ingresa el correo electrónico
        MobileElement emailInput = driver.findElementByXPath("//*[@text='Correo electrónico']");
        emailInput.sendKeys("paudpez@hotmail.com");
        // Ingresa la contraseña
        MobileElement passwordInput = driver.findElementByXPath("//*[@text='Contraseña']");
        passwordInput.sendKeys("archlot140799P");
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
            driver.findElementByXPath("//*[@text='Seguridad']").click();
            extentTest.log(Status.PASS, "Se ha ingresado al Módulo de Seguridad");
        } catch (Exception e) {
            extentTest.log(Status.FAIL, "No se ha ha podido ingresar al Módulo de Seguridad. Detalles: " + e.getMessage());
        }
        try {
            // Identifica el elemento Picker (usando XPath, ajusta según la estructura real de tu app)
            driver.findElementByXPath("//*[@text='Alertas']").click();
            extentTest.log(Status.PASS, "Se ha ingresado al Módulo de Alertas");
        } catch (Exception e) {
            extentTest.log(Status.FAIL, "No se ha podido ingresar al Módulo de Alertas. Detalles: " + e.getMessage());
        }
        try {
            // Identifica el elemento Picker (usando XPath, ajusta según la estructura real de tu app)
            driver.findElementByXPath("//*[@text='Alertas enviadas']").click();
            extentTest.log(Status.PASS, "Se ha seleccionado las alertas que haya enviado");
        } catch (Exception e) {
            extentTest.log(Status.FAIL, "No se ha ha podido especificar el filtro de 'Alertas Enviadas'. Detalles: " + e.getMessage());
        }
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        try {
            // Identifica el elemento Picker (usando XPath, ajusta según la estructura real de tu app)
            driver.findElementByXPath("//android.view.ViewGroup[@content-desc=\"listCardAlerts\"]/android.view.ViewGroup[1]").click();
            extentTest.log(Status.PASS, "Se ha seleccionado una alerta");
        } catch (Exception e) {
            extentTest.log(Status.FAIL, "No se ha podido seleccionar la alerta'. Detalles: " + e.getMessage());
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        driver.findElementByXPath("//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup/android.widget.ImageView").click();
        try {
            // Identifica el elemento Picker (usando XPath, ajusta según la estructura real de tu app)
            driver.findElementByXPath("//android.widget.TextView[@text=\"Robo\"]").click();
            extentTest.log(Status.PASS, "Se ha seleccionado la opción 'Marcar como atendida'");
        } catch (Exception e) {
            extentTest.log(Status.FAIL, "No se ha podido seleccionar la opción de 'Marcar como atendida'. Detalles: " + e.getMessage());
        }
        try {
            // Identifica el elemento Picker (usando XPath, ajusta según la estructura real de tu app)
            driver.findElementByXPath("//android.widget.TextView[@text=\"Confirmar\"]").click();
            extentTest.log(Status.PASS, "Se ha confirmado que la alerta ha sido atendida");
        } catch (Exception e) {
            extentTest.log(Status.FAIL, "No se ha podido confirmar que la alerta ha sido atentidida'. Detalles: " + e.getMessage());
        }

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    public void CancelAlert() {
        extentTest = extentReports.createTest("Cancelar Alerta");
        // Ingresa el correo electrónico
        MobileElement emailInput = driver.findElementByXPath("//*[@text='Correo electrónico']");
        emailInput.sendKeys("paudpez@hotmail.com");
        // Ingresa la contraseña
        MobileElement passwordInput = driver.findElementByXPath("//*[@text='Contraseña']");
        passwordInput.sendKeys("archlot140799P");
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
            driver.findElementByXPath("//*[@text='Seguridad']").click();
            extentTest.log(Status.PASS, "Se ha ingresado al Módulo de Seguridad");
        } catch (Exception e) {
            extentTest.log(Status.FAIL, "No se ha ha podido ingresar al Módulo de Seguridad. Detalles: " + e.getMessage());
        }
        try {
            // Identifica el elemento Picker (usando XPath, ajusta según la estructura real de tu app)
            driver.findElementByXPath("//*[@text='Alertas']").click();
            extentTest.log(Status.PASS, "Se ha ingresado al Módulo de Alertas");
        } catch (Exception e) {
            extentTest.log(Status.FAIL, "No se ha podido ingresar al Módulo de Alertas. Detalles: " + e.getMessage());
        }
        try {
            // Identifica el elemento Picker (usando XPath, ajusta según la estructura real de tu app)
            driver.findElementByXPath("//*[@text='Alertas enviadas']").click();
            extentTest.log(Status.PASS, "Se ha seleccionado las alertas que haya enviado");
        } catch (Exception e) {
            extentTest.log(Status.FAIL, "No se ha ha podido especificar el filtro de 'Alertas Enviadas'. Detalles: " + e.getMessage());
        }
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        try {
            // Identifica el elemento Picker (usando XPath, ajusta según la estructura real de tu app)
            driver.findElementByXPath("//android.view.ViewGroup[@content-desc=\"listCardAlerts\"]/android.view.ViewGroup[1]").click();
            Thread.sleep(5000);
            extentTest.log(Status.PASS, "Se ha seleccionado una alerta");
        } catch (Exception e) {
            extentTest.log(Status.FAIL, "No se ha podido seleccionar la alerta'. Detalles: " + e.getMessage());
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        driver.findElementByXPath("//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup/android.view.ViewGroup[2]").click();
        try {
            // Identifica el elemento Picker (usando XPath, ajusta según la estructura real de tu app)
            Thread.sleep(8000);
            extentTest.log(Status.PASS, "Se ha seleccionado la opción 'Cancelar Alerta'");
        } catch (Exception e) {
            extentTest.log(Status.FAIL, "No se ha podido seleccionar la opción de 'Cancelar Alerta'. Detalles: " + e.getMessage());
        }
        try {
            // Identifica el elemento Picker (usando XPath, ajusta según la estructura real de tu app)
            driver.findElementByXPath("//android.widget.TextView[@text=\"Confirmar\"]").click();
            extentTest.log(Status.PASS, "Se ha confirmado que la alerta ha sido cancelada");
        } catch (Exception e) {
            extentTest.log(Status.FAIL, "No se ha podido confirmar que la alerta ha sido cancelada'. Detalles: " + e.getMessage());
        }
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }


    @Test
    public void CommentAlert(){
        extentTest = extentReports.createTest("Comentar Alerta");
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
            driver.findElementByXPath("//*[@text='Seguridad']").click();
            extentTest.log(Status.PASS, "Se ha ingresado al Módulo de Seguridad");
        } catch (Exception e) {
            extentTest.log(Status.FAIL, "No se ha ha podido ingresar al Módulo de Seguridad. Detalles: " + e.getMessage());
        }
        try {
            // Identifica el elemento Picker (usando XPath, ajusta según la estructura real de tu app)
            driver.findElementByXPath("//*[@text='Alertas']").click();
            extentTest.log(Status.PASS, "Se ha ingresado al Módulo de Alertas");
        } catch (Exception e) {
            extentTest.log(Status.FAIL, "No se ha podido ingresar al Módulo de Alertas. Detalles: " + e.getMessage());
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        try {
            // Identifica el elemento Picker (usando XPath, ajusta según la estructura real de tu app)
            List<AndroidElement> childs = driver.findElementsByAccessibilityId("TarjetaTemplate");
            childs.get(0).click();
            extentTest.log(Status.PASS, "Se ha ingresado a una Alerta");
        } catch (Exception e) {
            extentTest.log(Status.FAIL, "No se ha podido ingresar a una Alerta. Detalles: " + e.getMessage());
        }
        try {
            driver.findElementByXPath("//*[@text='Agregar comentario']").click();
            extentTest.log(Status.PASS, "Click en Botón para gregar comentario");
        } catch (Exception e) {
            extentTest.log(Status.FAIL, "No se ha podido dar click en botón para agregar comentario. Detalles: " + e.getMessage());
        }
        try {
            MobileElement commentInput = driver.findElementByAccessibilityId("inputCommentAlert");
            commentInput.sendKeys("Comentario de Prueba Appium");
            extentTest.log(Status.PASS, "Se ha ingresado a un comentario");
        } catch (Exception e) {
            extentTest.log(Status.FAIL, "No se ha podido ingresar un comentario. Detalles: " + e.getMessage());
        }
        try {
            driver.findElementByAccessibilityId("buttonSendCommentAlert").click();
            extentTest.log(Status.PASS, "Se ha registrado un comentario");
        } catch (Exception e) {
            extentTest.log(Status.FAIL, "No se ha podido registrar un comentario. Detalles: " + e.getMessage());
        }
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    public void CreateAlert() {

        extentTest = extentReports.createTest("Crear Alerta");
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

        MobileElement createAlertBottomNav = driver.findElementByXPath("//*[@text='Crear Alerta']");
        createAlertBottomNav.click();
        extentTest.log(Status.INFO, "Pantalla de Cargar Alerta Creada");
        try {
            // Identifica el elemento Picker (usando XPath, ajusta según la estructura real de tu app)
            MobileElement picker = driver.findElementByAccessibilityId("visibilidad");
            picker.click();
            driver.findElementByXPath("//android.widget.CheckedTextView[@resource-id=\"android:id/text1\" and @text=\"Verificados\"]").click();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            extentTest.log(Status.PASS, "Se ha seleccionado la visibilidad");
        } catch (Exception e) {
            extentTest.log(Status.FAIL, "No se pudo escoger la visibilidad. Detalles: " + e.getMessage());
        }
        try {
            // Identifica el elemento Picker (usando XPath, ajusta según la estructura real de tu app)
            MobileElement picker = driver.findElementByAccessibilityId("tipo");
            picker.click();
            driver.findElementByXPath("//android.widget.CheckedTextView[@resource-id=\"android:id/text1\" and @text=\"Accidente\"]").click();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            extentTest.log(Status.PASS, "Se ha seleccionado el tipo");
        } catch (Exception e) {
            extentTest.log(Status.FAIL, "No se pudo escoger el tipo. Detalles: " + e.getMessage());
        }
        try {
            MobileElement createAlert = driver.findElementByXPath("//*[@text='Enviar Alerta']");
            createAlert.click();
            WebDriverWait waitAlert = new WebDriverWait(driver, 30); // Espera hasta 30 segundos
            waitAlert.until(ExpectedConditions.invisibilityOfElementLocated(By.className("ActivityIndicator")));
            extentTest.log(Status.PASS, "Alerta Creada");
        }catch (Exception e){
            extentTest.log(Status.FAIL, "Alerta no creada. Detalles: " + e.getMessage());
        }
        try {
            Thread.sleep(5000);
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