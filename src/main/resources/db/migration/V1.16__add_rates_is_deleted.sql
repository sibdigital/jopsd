alter table rates
    add if not exists is_deleted boolean default false;