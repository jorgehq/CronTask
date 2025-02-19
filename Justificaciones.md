# Justificaciones
 
## Entrega 1 

## Validador de Contrasenias 

Se crearon los 5 metodos principales

* No este dentro 10k contrasenias mas comunes
* Cargar Contrasenias inseguras 
* Que no contenga el nombre/apellido de la persona 
* No haya repeticiones de caracteres (apartir de 3)
* Validar (donde se realiza todo) 

## 10k contrasenias mas comunes 

Se verifica que la contrasenia escrita no este dentro de las contrasenias mas comunes (10 000).

## Cargar Contrasenias inseguras 

Se lee un archivo anteriormente creado de las 10000 contrasenias mas usadas apartir del cual leemos para verificar. Utilizando un scanner para agregarlos a un set de Strings. 

## Nombre del usuario no este en Contrasenia 

Esta funcion se utiliza tanto para el nombre como el apellido, para evitar logica repetida. 

Tanto la contrasenia como al nombre se la pasa a mayusculas para normalizarla y poder verificar que la contrasenia no contenga la palabra (el nombre como el apellido)

## No haya Repeticiones de Caracteres 

No puede haber caracteres iguales contiguos (apartir de la tercera aparicion), para asi poder acceptar nombres que contengan la letra repetida (Ej Connor). 

## Validar 

1. Verificar 10k contrasenias (Evitar leer archivo mas de una vez)
2. Se lleva a cabo la comprobacion de que la contrasenia contiene una:
    * Mayuscula
    * Minuscula 
    * Digito 
    * Caracter Especial 
    * Esto se realiza en el mismo metodo para evitar Recorrer mas de una vez el string (Contrasenia) 
3. Tambien se verifica la longitud de la misma. 
4. En caso de ser rechazada la contrasenia se notifica las caracteristicas incumplidas.


## Colaboradores 

* Se decidio realizar un implementacion del patron Command.
* Unificar el mensaje para colaborar() para permitir expandir a nuevas formas de colaboración respetando el mismo mensaje.
* Mismo mensaje entre distintos objetos (polimorfismo)

## Personas 

* Dividimos en dos clases
    * Persona Juridica 
    * Persona Fisica 
Lo que tendran en comun es la interfaz medio de contacto el cual son 3 (Email, WhatsApp,teleforno)

## Heladeras y viandas 

Heladeras es una clase que contiene viandas, el cual sera una list. Lo cual indica que sabe cuales son las viandas que tiene. 


## Direccion 

Consideramos que era parte de la abstraccion del problema que se nos habia planteado y creimos conveniente modelalarla en una clase 

Uno de los motivos es por lo cual lo modelamos en una clase fue que se pedia abarcar direcciones de toda la Argentina, pudiendose repetir nombres de las calles. 


* Composicion de la clase 
  * Provincia 
  * Localidad 
  * Calle

Las cuales Provincia y localidades son enums que contienen todas las provincias y calles 


