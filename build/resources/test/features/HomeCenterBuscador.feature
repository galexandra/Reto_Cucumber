Feature: HU-001 Buscador Home Center
  Yo como usuario de HC
  Quiero buscar varios productos en la plataforma
  Para ver el nombre del producto en pantalla

  @
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

