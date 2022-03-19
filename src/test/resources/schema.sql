CREATE USER postgres;

---
--- Temperatures
---
CREATE TABLE IF NOT EXISTS public.temperatures
(
    process_value numeric(4, 1) NOT NULL,
    batch_id uuid NOT NULL,
    pid_id smallint NOT NULL,
    date timestamp without time zone
)

TABLESPACE pg_default;

CREATE INDEX batch_temperatures_idx ON public.temperatures (batch_id, pid_id);

ALTER TABLE IF EXISTS public.temperatures
    OWNER to postgres;

---
--- Setpoint
---
CREATE TABLE IF NOT EXISTS public.setpoint
(
    value numeric(4, 1) NOT NULL,
    batch_id uuid NOT NULL,
    pid_id smallint NOT NULL,
    date timestamp without time zone
)

TABLESPACE pg_default;

CREATE INDEX batch_setpoint_idx ON public.setpoint (batch_id, pid_id);

ALTER TABLE IF EXISTS public.setpoint
    OWNER to postgres;

---
--- Status
---
CREATE TABLE IF NOT EXISTS public.status
(
    value VARCHAR NOT NULL,
    batch_id uuid NOT NULL,
    pid_id smallint NOT NULL,
    date timestamp without time zone
)

TABLESPACE pg_default;

CREATE INDEX batch_status_idx ON public.status(batch_id, pid_id);

ALTER TABLE IF EXISTS public.status
    OWNER to postgres;



