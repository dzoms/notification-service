CREATE TABLE IF NOT EXISTS notification_entity (
    id uuid PRIMARY KEY,
    sender_id uuid NOT NULL,
    recipient_id uuid NOT NULL,
    time timestamp NOT NULL,
    room_id varchar NOT NULL
)