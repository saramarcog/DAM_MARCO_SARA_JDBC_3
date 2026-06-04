PREGUNTA 1:
Los socs tienen muchos incidentes, asi que en cada incidente debemos guardar
el id en base de datos o el objeto en java del soc al que pertenece.

PREGUNTA 2:
Porque queremos guardar el objeto, no el id. El id es en la base de datos,
en Java queremos acceder al objeto cuando accedemos aL soc, sus metodos,...
no queremos ver el id simplemente.


PREGUNTA 3:

Tiene una ventaja de seguridad principalmente, porque al hacer la consulta
le pasamos un ?, y ya en le pasamos el valor. Porque sino podrian inyectarle a
la consulta sql un 1=1, un true o algo, y ya tendrian acceso a la BD.

link EXCALIDRAW: https://excalidraw.com/#json=qY2vhQCHBsoVrjLItsP2h,d0OWagzyqtTkju05x30ezQ