# Servicio Mutant.


Este servicio cuenta con dos operaciones expuesta al usuario:
 ##### Operación /muntant: 
La cual tiene como función  recibir como parámetro un array de Strings que representan cada fila de una tabla de (NxN) con la secuencia del ADN. Las letras de los Strings solo pueden ser: (A,T,C,G), las cuales
representan cada base nitrogenada del ADN.Esta operación es un metodo POST que se expone por el puerto 8080.
```
    url: localhost:8080/mutant
    
    Body request: {"dna":["ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"] }
    
    Body response: { "message": "true"}
    
```

##### Operación /stats:
La cual se encarga de exponer las estadisticas de los analisis realizados, este operación es un metodo GET expuesto por el puerto 8080.


```
    url: localhost:8080/stats
        
    Body response: {
    				"count_mutant_dna": 6,
   				    "count_human_dna": 0,
  				    "ratio": 0.0
				   }
    
```

## PASOS PARA EJECUTAR EL SERVICIO.

- Clonar el repositorio y abrir los fuentes desde un IDE compatible con JAVA (Recomendado Intellij).
- Ejecutar el servicio.
- Usar la aplicación Postman para lanzar las peticiones.
