DROP TABLE IF EXISTS ticket CASCADE;
CREATE TABLE ticket
(
    ticket_id               INTEGER GENERATED ALWAYS AS IDENTITY,
    slot_no                 INTEGER NOT NULL,
    vehicle_no              VARCHAR(255) NOT NULL,
    in_time                 TIMESTAMP NOT NULL,
    out_time                TIMESTAMP,
    PRIMARY KEY (ticket_id)
);