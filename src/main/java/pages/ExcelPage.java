package pages;

import org.openqa.selenium.WebDriver;

public class ExcelPage {

    private String nombreProducto;

    public ExcelPage(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public ExcelPage() {
        this.nombreProducto = null;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "nombreProducto='" + nombreProducto + '\'' +
                '}';
    }
}
