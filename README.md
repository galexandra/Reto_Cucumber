# Reto_Cucumber
Lo primero es crear un proyecto en Intellij para realizar la automatización de buscar un producto, se selecciona Gradle y se deja todo igual y se le da siguiente.

![image](https://user-images.githubusercontent.com/86448944/145666236-87e86a94-d8e7-47a6-9608-d9be274f2cd3.png)

Una vez cread el proyecto, en er archivo build.gradle se egragan las siguientes dependencias, que son para manejar el serenity y el excel.

    implementation 'net.serenity-bdd:serenity-junit:2.0.80'
    implementation 'net.serenity-bdd:serenity-cucumber:1.9.45'
    implementation 'net.serenity-bdd:serenity-core:2.0.80'
    implementation 'org.slf4j:slf4j-simple:1.7.7'
    implementation group: 'org.apache.poi', name: 'poi', version: '3.17'
    implementation group: 'org.apache.poi', name: 'poi-ooxml', version: '3.17'

![image](https://user-images.githubusercontent.com/86448944/145666389-ed0c783a-99b2-4ea7-a410-3bac987a07cf.png)

En la parte superior derecha sale un elefante, se le da clic para que carguen las dependencias

![image](https://user-images.githubusercontent.com/86448944/145666518-b1fb0ef0-5d5e-473e-8279-1c952006e384.png)

Una vez que haya cargado se puede ver la estructura de las carpetas asi:
![image](https://user-images.githubusercontent.com/86448944/145666536-904a9882-6e96-4bb2-a18d-477b7a4e224d.png)

Se empiezan a crear los paquetes (carpetas) con la estructura POM, dentro del paquete src, esta main y en el se encuentra java, ahí se crean los paquetes drivers, pages y steps.

![image](https://user-images.githubusercontent.com/86448944/145667245-0ca0aac0-2811-4631-93b5-e7cd0163f222.png)

Dentro del paquete test, en java,se crean los paquetes runners y stepsDefinition. Y en el paquete resource se crea el paquete feature

![image](https://user-images.githubusercontent.com/86448944/145667304-663898dc-29d4-4c57-a979-af043de4e8e5.png)

Lo primero que se va a crear es el excel con los productos que se desean buscar y se guarda en la raiz de la carpeta del proyecto

![image](https://user-images.githubusercontent.com/86448944/145667358-a2afcd9d-a0c0-456c-8f94-14c974b469ab.png)

En el paquete drivers se crea un archivo java con el driver de Google Chrome y se prepara para la automatización
       
	package drivers;
    import org.openqa.selenium.WebDriver;
    import org.openqa.selenium.chrome.ChromeDriver;
    import org.openqa.selenium.chrome.ChromeOptions;

    public class GoogleChromeDriver {

    public static WebDriver driver;

    public static void chromeWebDriver(String url) {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized"); //hace que se inicie la venta maximizada
        options.addArguments("--ignore-certificate-errors"); //Ignora los certificados 
        options.addArguments("--disable-infobars"); //deshabilita las barras informativas
        driver = new ChromeDriver(options);
        driver.get(url); //obtiene la url de la pagina que se va a automatizar
      }

    }

En el paquete pages se realiza un archivo java por cada pantalla a la que se le realizan acciones, aquí se pone una variable (con sus getter y setter) por cada acción realizada y se le asigna el Xpath con la etiqueda del campo, botón o elemento con el que se va a interactuar.

    package pages;

    import org.openqa.selenium.By;

    public class HomeCenterPage {

    By txtBuscador = By.xpath("//input[@class='DesktopSearchBar-module_searchbox-input__HXYgR']"); //xpath del buscador
    By btnBuscador = By.xpath("//div[@class='DesktopSearchBar-module_search-bar__1PiDn']//*[local-name()='svg']"); //xpath del botón para buscar
    By btnElementoBusqueda;
    By txtElementoBusqueda;

    public By getTxtBuscador() {
        return txtBuscador;
    }

    public By getBtnBuscador() {
        return btnBuscador;
    }

    public By getBtnElementoBusqueda() {
        return btnElementoBusqueda;
    }

    public By getTxtElementoBusqueda() {
        return txtElementoBusqueda;
    }

    public void setBtnElementoBusqueda(String producto) {
        this.btnElementoBusqueda = By.xpath("//a[@id='title-pdp-link']/h2[contains(text(),'" + producto + "')]");//xpath del link del producto
    }

    public void setTxtElementoBusqueda(String producto) {
        this.txtElementoBusqueda = By.xpath("//h1[contains(text(),'" + producto + "')]"); //xpath del titulo del producto para compararlo
    }
    }

En el paquete steps se van a poner los metodos por lo general por cada page hay un step. En el ExcelSteps se encuenta el metodo para hacer la lectura del excel y de los elementos que se encuentran dentro de este.

    package steps;

    import org.apache.poi.ss.usermodel.Cell;
    import org.apache.poi.ss.usermodel.Row;
    import org.apache.poi.xssf.usermodel.XSSFSheet;
    import org.apache.poi.xssf.usermodel.XSSFWorkbook;
    import java.io.File;
    import java.io.FileInputStream;
    import java.io.IOException;
    import java.util.ArrayList;
    import java.util.HashMap;
    import java.util.Iterator;
    import java.util.Map;

    public class ExcelSteps {
    public static ArrayList<Map<String, String>>leerDatosDeHojaDeExcel(String rutaDeExcel, String hojaDeExcel) throws IOException {
        ArrayList<Map<String, String>> arrayListDatoPlanTrabajo = new ArrayList<Map<String, String>>();
        Map<String, String> informacionProyecto = new HashMap<String, String>();
        File file = new File(rutaDeExcel);
        FileInputStream inputStream = new FileInputStream(file);
        XSSFWorkbook newWorkbook = new XSSFWorkbook(inputStream);
        XSSFSheet newSheet = newWorkbook.getSheet(hojaDeExcel);
        Iterator<Row> rowIterator = newSheet.iterator();
        Row titulos = rowIterator.next();
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            Iterator<Cell> cellIterator = row.cellIterator();
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                cell.getColumnIndex();
                switch (cell.getCellTypeEnum()) {
                    case STRING:
                        informacionProyecto.put(titulos.getCell(cell.getColumnIndex()).toString(), cell.getStringCellValue());
                        break;
                    case NUMERIC:
                        informacionProyecto.put(titulos.getCell(cell.getColumnIndex()).toString(), String.valueOf((long) cell.getNumericCellValue()));
                        break;
                    case BLANK:
                        informacionProyecto.put(titulos.getCell(cell.getColumnIndex()).toString(), "");
                        break;
                    default:
                }
            }
            arrayListDatoPlanTrabajo.add(informacionProyecto);
            informacionProyecto = new HashMap<String, String>();
        }
        return arrayListDatoPlanTrabajo;
    }
    }

En el HomeCenterSteps se encuentran las acciones que se realizan con los elementos dentro de la página 

    package steps;

    import drivers.GoogleChromeDriver;
    import org.junit.Assert;
    import pages.HomeCenterPage;
    import java.io.IOException;
    import java.util.ArrayList;
    import java.util.Map;


    public class HomeCenterSteps {
    HomeCenterPage homeCenterPage = new HomeCenterPage();
    public ArrayList<Map<String, String>> listaProductos;


    public void abrirPagina() {
        GoogleChromeDriver.chromeWebDriver("https://www.homecenter.com.co/");  //Se abre la página en el navegador
      
    }


    public void buscarElementoEnHomeCenter(String producto) {
        GoogleChromeDriver.driver.findElement(homeCenterPage.getTxtBuscador()).sendKeys(producto); //se busca el input y se le envia el producto 
        GoogleChromeDriver.driver.findElement(homeCenterPage.getBtnBuscador()).click(); //busca el botón buscar y le da click
        homeCenterPage.setBtnElementoBusqueda(producto); 
        GoogleChromeDriver.driver.findElement(homeCenterPage.getBtnElementoBusqueda()).click();// busca el producto que entre todos y lo selecciona

    }


    public void validarElementoEnPantalla(String producto) {
        homeCenterPage.setTxtElementoBusqueda(producto);
        Assert.assertEquals(producto, GoogleChromeDriver.driver.findElement(homeCenterPage.getTxtElementoBusqueda()).getText());//compara el nombre del producto enviado con el que se busco
        GoogleChromeDriver.driver.quit();//cierra el navegador
    }
    }
En el paquete test, en runners, se crea HomeCenterBuscadorRunner en el se va a ejecutar el proyecto

     package runners;

    import cucumber.api.CucumberOptions;
    import cucumber.api.SnippetType;
    import net.serenitybdd.cucumber.CucumberWithSerenity;
     import org.junit.runner.RunWith;

    @RunWith(CucumberWithSerenity.class)
     @CucumberOptions(
        features = "src\\test\\resources\\features\\HomeCenterBuscador.feature",
        glue = "stepsDefinitions",
        snippets = SnippetType.CAMELCASE
        )

     public class HomeCenterBuscadorRunner {

     }
en la carpeta StepsDefinitions se crea HomeCenterBuscadorStepDefinitions, aquí se pone el escenario y en cada parte se ponen los metodos que se van a realizar
     
	 package stepsDefinitions;

     import cucumber.api.java.en.Given;
    import cucumber.api.java.en.Then;
    import cucumber.api.java.en.When;
    import steps.ExcelSteps;
    import steps.HomeCenterSteps;

    import java.io.IOException;
    import java.util.List;
    import java.util.Map;

    public class HomeCenterBuscadorStepDefinitions {
    HomeCenterSteps homeCenterSteps = new HomeCenterSteps();
    ExcelSteps excel = new ExcelSteps();

    @Given("^que me encuentro en la pagina de ML$")
    public void queMeEncuentroEnLaPaginaDeML() {
        homeCenterSteps.abrirPagina(); //se ejecuta el método que inicia el navegador y la página
    }


    @When("^busque el producto en la hoja  \"([^\"]*)\" del excel en la fila (\\d+)$")
    public void busqueElProductoEnLaHojaDelExcelEnLaFila(String hojaDeExcel, int fila) throws IOException {
        List<Map<String, String>> test = excel.leerDatosDeHojaDeExcel("Productos.xlsx", hojaDeExcel); //busca el archivo excel y abre la hoja seleccionada, y lo que se obtiene del método de lectura del excel se guarda en una variable
        String producto = test.get(fila).get("Productos"); //lo que se encuentra en la fila asignada y en la columna con nombre productos, se guarda en la variable producto
        homeCenterSteps.buscarElementoEnHomeCenter(producto);//ejecuta el método de buscar el elemento y se le envia el producto que se guardo anteriormente
    }

    @Then("^podre ver el producto de la\"([^\"]*)\" en la (\\d+) en pantalla$")
    public void podreVerElProductoDeLaEnLaEnPantalla(String hojaDeExcel, int fila) throws IOException {
        List<Map<String, String>> test = excel.leerDatosDeHojaDeExcel("Productos.xlsx", hojaDeExcel);//busca el archivo excel y abre la hoja seleccionada, y lo que se obtiene del método de lectura del excel se guarda en una variable
        String producto = test.get(fila).get("Productos");//lo que se encuentra en la fila asignada y en la columna con nombre productos, se guarda en la variable producto
        homeCenterSteps.validarElementoEnPantalla(producto);//ejecuta el método de validar el elemento y se le envia el producto que se guardo anteriormente
    }

    }


En el paquete features se crea el archivo HomeCenterBuscador.feature, aquí se define el escenario y lo que se desea realizar de la prueba 

    Feature: HU-001 Buscador Home Center
    Yo como usuario de HC
    Quiero buscar varios productos en la plataforma
    Para ver el nombre del producto en pantalla

  
    Scenario Outline: Buscar Producto
    Given que me encuentro en la pagina de ML
    When busque el producto en la hoja  "<hojaDeExcel>" del excel en la fila <fila>
    Then podre ver el producto de la"<hojaDeExcel>" en la <fila> en pantalla
    Examples:
      | hojaDeExcel | fila |
      | Hoja1       | 0    |
      | Hoja1       | 1    |
      | Hoja1       | 2    |
      | Hoja1       | 3    |
      | Hoja1       | 4    |




