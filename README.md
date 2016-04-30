# lab2_compumovil

Objetivo general: Construir una aplicación que permita visualizar información de maratones

Objetivos específicos:
Utilizar algunos componentes que ofrece Android para crear vistas, activities, fragments e intents. 
Usar SQLite como motor de base de datos local
Uso de Navigation Drawer

Actividad:

Cree un nuevo proyecto (new project with empty activity) con el nombre Lab2AppRun, siendo X el número de su grupo de trabajo. Nombre del paquete debe ser co.edu.udea.compumovil.grX.lab2apprun, donde X es el número de su grupo.
	
Implemente una actividad que permita logearse (user + password) ó lanzar una nueva actividad para registrarse. Si el usuario ya se encuentra logeado previamente, la aplicación no debe pedir el logueo.

Implemente una actividad que permita registrarse (user, password y email), la información debe estar almacenada en un base de datos local.

Implemente una base de datos local usando SQLite que contenga la tablas: users y events. El diseño de la base de datos es responsabilidad del diseñador.

Implemente una actividad que:
Implemente un navigation drawer con 3 ítems: 
Carreras:  la lista de las carreras. 
Perfil: Nombre de usuario y foto.
Acerca de: Información de la materia y del curso.
Permita visualizar una lista de carreras, con la siguiente información:
Nombre, distancia, lugar y fecha, foto(opcional).
Permita visualizar el detalle de cada carrera, con la siguiente información:
Nombre, descripción, distancia, lugar, fecha y datos de contacto: teléfono y email.
Permita agregar una nueva carrera.
Permita desloguearse.

Notas importantes:

La aplicación debe realizarse usando material design, brindando soporte a versiones anteriores de Android 5.0.
El APK de la aplicación debe estar firmado y montado en el drive (en su respectivo directorio) a más tardar el próximo viernes 19 de marzo.
