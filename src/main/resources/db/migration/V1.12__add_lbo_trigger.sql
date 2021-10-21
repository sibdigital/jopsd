ALTER TABLE public.lbo
    ADD COLUMN IF NOT EXISTS sys_period tstzrange NOT NULL DEFAULT tstzrange(current_timestamp, null);

CREATE TABLE IF NOT EXISTS lbo_history
(
    LIKE public.lbo
);

DO
$$ BEGIN
    IF NOT EXISTS(
            SELECT * FROM information_schema.triggers
            WHERE event_object_table = 'lbo' AND trigger_name='versioning_trigger'
        )
    THEN

        CREATE TRIGGER versioning_trigger
            BEFORE INSERT OR UPDATE OR DELETE
            ON lbo
            FOR EACH ROW
        EXECUTE PROCEDURE versioning(
                'sys_period', 'lbo_history', true
            );
    END IF;
END $$;

