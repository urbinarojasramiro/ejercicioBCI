# BCI
El WS se probó en PostMan

El WS consta de 4 métodos
/userApiRestFul/createRol
/userApiRestFul/saveUser
/userApiRestFul/login
/userApiRestFul/getUsers

Para poder crear usuarios es necesario crear el rol antes con el método:
   /userApiRestFul/createRol 
utilizando el siguiente json:
  {
      "id": 1
      , "rolName": "ROLE_USER"
  }

Solo el rol "ROLE_USER" está configurado, ya que dentro del código se asigna de manera predeterminada

Para crear el usuario es necesario ejecutar el siguiente método:
/userApiRestFul/saveUser
Utilizando el siguiente json:
{
    "name": "Juan Rodriguez"
    , "email":"juan@rodriguez.org"
    , "password":"nu123H#hh"
    , "phones" : [
        {
            "number" : "+5691234567",
            "citycode" : "1",
            "contrycode" : "57"
        }
    ]
}

Para obtener los usuarios que se han ido creando se debe utilizar el siguiente método:
/userApiRestFul/getUsers
Se debe enviar por medio del header el token entregado en el método anterior con el nombre Authorization

Se puede solicitar el token de cualquier usuario creado para consultar el método getUsers

Para la base de datos en memoria, se utilizó la siguiente configuración:
spring.datasource.url=jdbc:h2:mem:testDB;DB_CLOSE_ON_EXIT=FALSE
spring.h2.console.enabled=true
spring.datasource.username=sa
spring.datasource.password=
spring.datasource.driverClassName=org.h2.Driver

Los diagramas se encuentran en la carpeta /images


