create table "word_ending"
(
    "id"    bigserial  not null,
    "value" varchar(3) not null unique,
    primary key ("id")
);

CREATE INDEX word_ending_index ON word_ending (value);

create table "word_suffix"
(
    "id"    bigserial  not null,
    "value" varchar(5) not null unique,
    primary key ("id")
);

CREATE INDEX word_suffix_index ON word_suffix (value);

create table "document"
(
    "id"                     bigserial    not null,
    "name_file"              varchar(255) not null unique,
    "name_author"            varchar(255),
    "name_document"          varchar(255),
    "name_inspector"         varchar(255),
    "file_path"              varchar(255),
    "percentage_originality" double precision,
    "is_active"              boolean,
    "group_uid"              uuid,
    "id_file"                int8,
    "user_id"                int4,
    "hash_file"              varchar(255) not null unique,
    primary key ("id")
);

create table "service_words"
(
    "id"    bigserial  not null,
    "value" varchar(8) not null unique,
    primary key ("id")
);

CREATE INDEX service_words_index ON service_words (value);

create table "users"
(
    "id"         serial       not null,
    "login"      varchar(255) not null unique,
    "password"   varchar(255) not null,
    "first_name" varchar(255) not null,
    "last_name"  varchar(255) not null,
    "patronymic" varchar(255) not null,
    "role"       int4,
    primary key ("id")
);