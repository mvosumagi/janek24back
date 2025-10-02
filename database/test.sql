BEGIN;
SET search_path TO janek24;

-- add the FK columns once
ALTER TABLE inbox
    ADD COLUMN IF NOT EXISTS service_id INT REFERENCES provider_service(id),
    ADD COLUMN IF NOT EXISTS order_id   INT REFERENCES "order"(id);

-- convert created_at to timestamp ONLY if it's currently time
DO $$
    DECLARE v_type text;
    BEGIN
        SELECT data_type INTO v_type
        FROM information_schema.columns
        WHERE table_schema='janek24'
          AND table_name='inbox'
          AND column_name='created_at';

        IF v_type = 'time without time zone' THEN
            EXECUTE $conv$
      ALTER TABLE inbox
        ALTER COLUMN created_at TYPE timestamp
        USING (CURRENT_DATE::timestamp + created_at::time)
    $conv$;
        END IF;
    END $$;

-- ensure sensible default and not null
ALTER TABLE inbox ALTER COLUMN created_at SET DEFAULT now();
UPDATE inbox SET created_at = COALESCE(created_at, now());
ALTER TABLE inbox ALTER COLUMN created_at SET NOT NULL;

COMMIT;

-- verify
SELECT column_name, data_type
FROM information_schema.columns
WHERE table_schema='janek24' AND table_name='inbox'
ORDER BY column_name;

SELECT id, created_at, service_id, order_id
FROM inbox
ORDER BY id DESC
LIMIT 5;