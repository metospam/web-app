create schema if not exists edu_schema;

create table edu_schema.authors
(
    author_id bigserial
        constraint authors_pk
            primary key,
    firstname varchar,
    lastname  varchar
);

create table edu_schema.books
(
    book_id     bigserial
        constraint books_pk
            primary key,
    rating      double precision,
    description varchar,
    title       varchar,
    authors     varchar,
    genres      varchar
);

create table edu_schema.books_authors
(
    book_id   bigint
        constraint books_authors_books_book_id_fk
            references edu_schema.books,
    author_id bigint
        constraint books_authors_authors_author_id_fk
            references edu_schema.authors
);

create table edu_schema.books_genres
(
    book_id  bigint
        constraint books_genres_books_book_id_fk
            references edu_schema.books,
    genre_id bigint
        constraint books_genres_genres_genre_id_fk
            references edu_schema.genres
);

create table edu_schema.comments
(
    comment_id bigserial
        constraint comments_pk
            primary key,
    book_id    bigint
        constraint comments_books_book_id_fk
            references edu_schema.books,
    user_id    bigint,
    message    varchar,
    likes      integer,
    dislikes   integer
);

create table edu_schema.genres
(
    genre_id  bigserial
        constraint genres_pk
            primary key,
    genrename varchar
);

create table edu_schema.roles
(
    id   bigserial
        constraint roles_pk
            primary key,
    name varchar
);

create table edu_schema.users
(
    id       bigserial
        constraint table_name_pk
            primary key,
    password varchar,
    username varchar
);

create table edu_schema.users_roles
(
    user_id bigint
        constraint users_roles_users_id_fk
            references edu_schema.users,
    role_id bigint
        constraint users_roles_roles_id_fk
            references edu_schema.roles
);



















