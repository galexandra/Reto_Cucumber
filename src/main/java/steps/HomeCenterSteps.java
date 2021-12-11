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
        GoogleChromeDriver.chromeWebDriver("https://www.homecenter.com.co/");
           }


    public void buscarElementoEnHomeCenter(String producto) {
        GoogleChromeDriver.driver.findElement(homeCenterPage.getTxtBuscador()).sendKeys(producto);
        GoogleChromeDriver.driver.findElement(homeCenterPage.getBtnBuscador()).click();
        homeCenterPage.setBtnElementoBusqueda(producto);
        GoogleChromeDriver.driver.findElement(homeCenterPage.getBtnElementoBusqueda()).click();

    }


    public void validarElementoEnPantalla(String producto) {
        homeCenterPage.setTxtElementoBusqueda(producto);
        Assert.assertEquals(producto, GoogleChromeDriver.driver.findElement(homeCenterPage.getTxtElementoBusqueda()).getText());
        GoogleChromeDriver.driver.quit();
    }
}




