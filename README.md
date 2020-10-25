## Código de Honor
Debes seguir el Código de honor del ingeniero de sistemas para defender el estándar de integridad académica de la ECI:

Tus respuestas a tareas, cuestionarios y exámenes deben ser tu propio trabajo (excepto para las tareas que permiten explícitamente la colaboración).

No puedes compartir tus soluciones de tareas, cuestionarios o exámenes con otra persona a menos que el instructor lo permita explícitamente. Esto incluye cualquier cosa escrita por ti, como también cualquier solución oficial proporcionada por el docente o el monitor del curso.

No puedes participar en otras actividades que mejorarán de manera deshonesta tus resultados o que mejorarán de manera deshonesta o dañarán los resultados de otras personas.


# JPA with MongoDB

### Punto 6 

- How many customers were created in the database?

   Se crearon 5 clientes en la base de datos
   
  ![Customers](https://user-images.githubusercontent.com/48154086/97116759-567be900-16cd-11eb-9a79-4d2ba5ec7fa9.PNG)
  
- Where is the *findAll* method implemented?

  El metodo findAll esta implementado en MongoRepository y la interfaz CustomerRepository puede hacer uso de este por que extiende de MongoRepository.
  
- Suppose you have more than 1000 products in your database. How would you implement a method for supporting pagination and return pages of 50 products to your frontend?
- How many products contain the "plus" word in their description?

  Hay 4 productos que contienen la palabra plus en su descripción 
  
  ![products](https://user-images.githubusercontent.com/48154086/97116827-e457d400-16cd-11eb-9121-01050f54e089.PNG)

- How many products are returned by the *findByDescriptionContaining* query? Why?

- Which are the collection names where the objects are stored? Where are those names assigned?

Las colecciones donde los objetos se estan guardando son "customer" y "product".

![collections](https://user-images.githubusercontent.com/48154086/97116888-4e707900-16ce-11eb-8553-08f763211175.PNG)

