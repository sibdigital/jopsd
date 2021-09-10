ALTER TABLE work_packages
    ADD IF NOT EXISTS meta_id bigint;

ALTER TABLE cost_objects
    ADD IF NOT EXISTS meta_id bigint;

ALTER TABLE targets
    ADD IF NOT EXISTS meta_id bigint;

ALTER TABLE projects
    ADD IF NOT EXISTS meta_id bigint;