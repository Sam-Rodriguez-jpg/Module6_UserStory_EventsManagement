CREATE TABLE venues (
    id_venue INT AUTO_INCREMENT PRIMARY KEY,
    name_venue VARCHAR(150) NOT NULL UNIQUE,
    city_venue VARCHAR(100) NOT NULL,
    capacity_venue INT NOT NULL
);

CREATE TABLE events (
    id_event INT AUTO_INCREMENT PRIMARY KEY,
    name_event VARCHAR(150) NOT NULL,
    description_event VARCHAR(255),
    duration_event INT,
    datetime_event TIMESTAMP NOT NULL,
    price_event DOUBLE,
    status_event VARCHAR(10),
    id_venue INT,
    CONSTRAINT fk_venue FOREIGN KEY (id_venue) REFERENCES venues(id_venue)
);
