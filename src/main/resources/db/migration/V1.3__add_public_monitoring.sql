
create table pages -- таблица со страницами для вывода
(
    id  serial  not null
        constraint pages_pkey
            primary key,
    is_deleted boolean default false,
    is_publicated boolean default false, -- опубликована или нет т.е достпна ли для просмотра
    is_group boolean default false, --это группа не страница, а узел иерархии
    project_id integer
        constraint fk_projects_pages
            references projects,
    work_package_id   integer
        constraint fk_work_packages_pages
            references work_packages,
    parent_id integer
        constraint fk_pages_pages
            references pages,
    author_id integer not null
        constraint fk_author_pages
            references users,
    title text,
    content text,
    created_on timestamp default CURRENT_TIMESTAMP not null,
    updated_on timestamp
)
;

create table page_files -- файлы страницы
(
    id  serial  not null
        constraint page_files_pkey
            primary key,
    is_deleted boolean default false,
    page_id integer
        constraint fk_pages_files
            references pages,
    attachment_path text,
    file_name text,
    view_file_name text,
    original_file_name text,
    file_extension varchar(16),
    hash text,
    file_size integer,
    created_on timestamp default CURRENT_TIMESTAMP not null,
    updated_on timestamp
)
;
create table page_maps -- карты страницы
(
    id  serial  not null
        constraint page_maps_pkey
            primary key,
    is_deleted boolean default false,
    page_id integer
        constraint fk_pages_maps
            references pages,
    source text, -- ссылка на карту
    created_on timestamp default CURRENT_TIMESTAMP not null,
    updated_on timestamp
)
;