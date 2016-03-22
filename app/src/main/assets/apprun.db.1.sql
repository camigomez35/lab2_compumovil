CREATE TABLE usuario(
    nombre TEXT PRIMARY KEY,
    contrasena TEXT,
    correo TEXT,
);

CREATE TABLE carrera(
    nombre TEXT PRIMARY KEY,
    descripcion TEXT,
    distancia DOUBLE,
    lugar TEXT,
    fecha TEXT,
    telefono TEXT,
    email TEXT,
);

CREATE TABLE usuarioporcarrera(
    nombre_usuario TEXT,
    nombre_carrera TEXT,
);
