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
        homeCenterSteps.abrirPagina();
    }


    @When("^busque el producto en la hoja  \"([^\"]*)\" del excel en la fila (\\d+)$")
    public void busqueElProductoEnLaHojaDelExcelEnLaFila(String hojaDeExcel, int fila) throws IOException {
        List<Map<String, String>> test = excel.leerDatosDeHojaDeExcel("Productos.xlsx", hojaDeExcel);
        String producto = test.get(fila).get("Productos");
        homeCenterSteps.buscarElementoEnHomeCenter(producto);
    }

    @Then("^podre ver el producto de la\"([^\"]*)\" en la (\\d+) en pantalla$")
    public void podreVerElProductoDeLaEnLaEnPantalla(String hojaDeExcel, int fila) throws IOException {
        List<Map<String, String>> test = excel.leerDatosDeHojaDeExcel("Productos.xlsx", hojaDeExcel);
        String producto = test.get(fila).get("Productos");
        homeCenterSteps.validarElementoEnPantalla(producto);
    }




}
