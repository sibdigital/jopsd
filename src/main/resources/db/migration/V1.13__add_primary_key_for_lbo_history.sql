alter table lbo_history
    add primary_id serial;

alter table lbo_history
    add constraint lbo_history_pk
        primary key (primary_id);
