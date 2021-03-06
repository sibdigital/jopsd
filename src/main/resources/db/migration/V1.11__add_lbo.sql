create table if not exists lbo
(
    id integer generated by default as identity not null
        constraint lbo_pk
            primary key,
    project_id integer
        constraint fk_project
            references projects,
    year integer,
    sum numeric(15, 4)
);

