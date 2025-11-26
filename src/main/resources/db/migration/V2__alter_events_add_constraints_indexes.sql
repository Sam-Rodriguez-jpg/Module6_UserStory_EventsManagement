-- Hacer NOT NULL
ALTER TABLE events ALTER COLUMN duration_event SET NOT NULL;
ALTER TABLE events ALTER COLUMN price_event SET NOT NULL;
ALTER TABLE events ALTER COLUMN status_event SET NOT NULL;

-- Default
ALTER TABLE events ALTER COLUMN status_event SET DEFAULT '0';

-- Cambiar FK para ON DELETE SET NULL
ALTER TABLE events DROP CONSTRAINT fk_venue;
ALTER TABLE events ADD CONSTRAINT fk_venue
    FOREIGN KEY (id_venue)
    REFERENCES venues(id_venue)
    ON DELETE SET NULL;

-- Índices
CREATE INDEX idx_venues_name ON venues(name_venue);
CREATE INDEX idx_events_name ON events(name_event);
CREATE INDEX idx_events_status ON events(status_event);
CREATE INDEX idx_events_venue ON events(id_venue);
