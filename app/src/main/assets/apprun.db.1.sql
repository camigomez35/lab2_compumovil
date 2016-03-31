CREATE TABLE usuario(
    nombre TEXT PRIMARY KEY,
    contrasena TEXT,
    correo TEXT,
    sesion  INTEGER
);

CREATE TABLE carrera(
    nombre TEXT PRIMARY KEY,
    descripcion TEXT,
    distancia DOUBLE,
    lugar TEXT,
    fecha TEXT,
    telefono TEXT,
    email TEXT,
    imagen TEXT
);

CREATE TABLE usuarioporcarrera(
    nombre_usuario TEXT,
    nombre_carrera TEXT
);
