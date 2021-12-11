package pages;

import org.openqa.selenium.By;

public class HomeCenterPage {

    By txtBuscador = By.xpath("//input[@class='DesktopSearchBar-module_searchbox-input__HXYgR']");
    By btnBuscador = By.xpath("//div[@class='DesktopSearchBar-module_search-bar__1PiDn']//*[local-name()='svg']");
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
        this.btnElementoBusqueda = By.xpath("//a[@id='title-pdp-link']/h2[contains(text(),'" + producto + "')]");
    }

    public void setTxtElementoBusqueda(String producto) {
        this.txtElementoBusqueda = By.xpath("//h1[contains(text(),'" + producto + "')]");
    }
}
