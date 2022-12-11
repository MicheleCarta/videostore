create table if not exists movies
(
    id      bigint primary key,
    title     varchar(128) not null,
    description varchar(500) not null,
    category varchar(64) not null,
    year_production int not null,
    year_released int,
    month_released varchar(20),
    rating numeric(19,5),
    casting varchar(1000),
    created_at timestamp not null,
    updated_at timestamp
);

create table if not exists directors
(
    id      bigint primary key,
    name varchar(500),
    surname varchar(500),
    b_day   timestamp
);

create table if not exists movies_directors
(
    movie_id bigint not null,
    director_id bigint not null,
    primary key (movie_id, director_id),
    constraint fk_movie_id foreign key (movie_id) references movies (id),
    constraint fk_directors_id foreign key (director_id) references directors (id)
);

CREATE SEQUENCE movie_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE SEQUENCE director_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;