ALTER TABLE public.material_budget_items
    ADD COLUMN IF NOT EXISTS sys_period tstzrange NOT NULL DEFAULT tstzrange(current_timestamp, null);

CREATE TABLE IF NOT EXISTS material_budget_items_history
(
    LIKE public.material_budget_items
);

DO
$$ BEGIN
    IF NOT EXISTS(
            SELECT * FROM information_schema.triggers
            WHERE event_object_table = 'material_budget_items' AND trigger_name='versioning_trigger'
        )
    THEN

        CREATE TRIGGER versioning_trigger
            BEFORE INSERT OR UPDATE OR DELETE
            ON material_budget_items
            FOR EACH ROW
        EXECUTE PROCEDURE versioning(
                'sys_period', 'material_budget_items_history', true
            );
    END IF;
END $$;
