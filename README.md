<p align="center">
  <a href="https://www.sofka.com.co/es/inicio/" target="blank"><img src="https://media.glassdoor.com/sqll/3788784/sofka-technologies-squareLogo-1668095593025.png" width="200" alt="Sofka Logo" /></a>
</p>

# Reto Sofka API

# Pasos para Ejecutar el Proyecto

1.  Clonar el proyecto

2. Tener instalado sdk 17 para el proyecto

3.Se puede realizar peticiones tanto desde postman como en la siguiente ruta del proyecto {raiz}/swagger-ui.html 

# Ejecutar Pruebas Unitarias.

1. Si estamos en IntelliJ nos dirigimos a la ruta /src/test con click derecho la opción de run "All tests"


    Nota: El Coverage de las pruebas unitarias se adjunta una captura de pantalla.

# Validaciones Realizadas.

1.  La cantidad de productos en inventario, no supere la cantidad de productos solicitados en la compra.
2.  La cantidad de productos minimo que se permite comprar, no sea menor a la cantidad de productos solicitados en la compra.
3.  La cantidad de productos maximo que se permite comprar, no sea mayor a la cantidad de productos soliciatados en la compra.
4.  Disponibilidad del producto, es decir la cantidad de productos en inventario sea mayor a 0.
