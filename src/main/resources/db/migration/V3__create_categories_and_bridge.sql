-- Hacer datetime_event obligatorio
ALTER TABLE events ALTER COLUMN datetime_event SET NOT NULL;

-- Tabla categorías
CREATE TABLE categories (
    id_category INT AUTO_INCREMENT PRIMARY KEY,
    name_category VARCHAR(100) UNIQUE NOT NULL
);

-- Tabla puente
CREATE TABLE events_categories (
    id_event INT NOT NULL,
    id_category INT NOT NULL,
    PRIMARY KEY (id_event, id_category),
    FOREIGN KEY (id_event) REFERENCES events(id_event) ON DELETE CASCADE,
    FOREIGN KEY (id_category) REFERENCES categories(id_category) ON DELETE CASCADE
);

-- Índices
CREATE INDEX idx_event_category_event ON events_categories(id_event);
CREATE INDEX idx_event_category_category ON events_categories(id_category);
