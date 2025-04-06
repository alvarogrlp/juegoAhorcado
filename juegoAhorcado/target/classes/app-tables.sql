-- Eliminar la tabla palabra si existe
DROP TABLE IF EXISTS nivel;
DROP TABLE IF EXISTS palabra;
DROP TABLE IF EXISTS usuario;

-- Crear tabla de palabras
CREATE TABLE palabra (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    palabra TEXT NOT NULL,
    id_nivel INTEGER NOT NULL,
    FOREIGN KEY (id_nivel) REFERENCES niveles(id)
);

-- Insertar palabras de nivel 'facil'
INSERT INTO palabra (palabra, id_nivel) VALUES
('guerra', 1),
('circuito', 1),
('sombra', 1),
('relieve', 1),
('pescado', 1),
('pintura', 1),
('planta', 1),
('viento', 1),
('raton', 1),
('flore', 1);

-- Insertar palabras de nivel 'medio'
INSERT INTO palabra (palabra, id_nivel) VALUES
('murcielago', 2),
('ciudad', 2),
('camino', 2),
('repollo', 2),
('corredor', 2),
('banco', 2),
('granja', 2),
('tierra', 2),
('pelota', 2),
('parque', 2);

-- Insertar palabras de nivel 'dificil'
INSERT INTO palabra (palabra, id_nivel) VALUES
('anticonstitucionalmente', 3),
('hipopotomonstrosesquipedaliofobia', 3),
('supercalifragilisticexpialidocious', 3),
('pneumoultramicroscopicsilicovolcanoconiosis', 3),
('quimioterapia', 3),
('desoxirribonucleico', 3),
('transatlantico', 3),
('electromagnetico', 3),
('inconstitucionalidad', 3),
('historiografia', 3);


-- Crear tabla de usuarios
CREATE TABLE usuario (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    nombre_usuario TEXT NOT NULL UNIQUE,
    email TEXT NOT NULL UNIQUE,
    contrasenia TEXT NOT NULL,
    nivel_actual INTEGER DEFAULT 1,
    victorias_totales INTEGER DEFAULT 0,
    derrotas_totales INTEGER DEFAULT 0,
    victorias_nivel INTEGER DEFAULT 0,
    mayor_racha INTEGER DEFAULT 0,
    racha_actual INTEGER DEFAULT 0,
    derrotas_consecutivas INTEGER DEFAULT 0
);

-- Insertar usuarios de ejemplo
INSERT INTO usuario (nombre_usuario, email, contrasenia, nivel_actual) VALUES
    ('alvaro', 'alvaro@gmail.com', '123', 1),
    ('anita', 'ana@gmail.com', '098', 2),
    ('admin', 'admin@admin.com', 'admin', 3);


-- Crear tabla de niveles
CREATE TABLE nivel (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    nivel TEXT NOT NULL
);

INSERT INTO nivel (nivel) VALUES
('facil'),
('medio'),
('dificil');