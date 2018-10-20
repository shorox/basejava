create table if not exists resume
(
  uuid      char(36) not null
    constraint resume_pkey
    primary key,
  full_name varchar  not null,
  image varchar
);

create table if not exists category
(
  id          serial   not null
    constraint category_pkey
    primary key,
  resume_uuid char(36) not null
    constraint contact_resume_uuid_fk
    references resume
    on delete cascade,
  itype       varchar  not null,
  ivalue      varchar  not null
);

create unique index if not exists category_uuid_type_index
  on category (resume_uuid, itype);

create table if not exists contact
(
  id          serial   not null
    constraint contact_pkey
    primary key,
  resume_uuid char(36) not null
    constraint contact_resume_uuid_fk
    references resume
    on delete cascade,
  type        varchar  not null,
  value       varchar  not null
);

create unique index if not exists contact_uuid_type_index
  on contact (resume_uuid, type);