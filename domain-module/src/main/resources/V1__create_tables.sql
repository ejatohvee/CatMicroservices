CREATE SCHEMA IF NOT EXISTS CatsSchema;

CREATE TABLE IF NOT EXISTS cats_schema.persons (
    id UUID PRIMARY KEY,
    name VARCHAR(30) NOT NULL,
    birthday_date DATE
);

CREATE TABLE IF NOT EXISTS cats_schema.cats (
    id UUID PRIMARY KEY,
    name VARCHAR(30) NOT NULL,
    color VARCHAR(30),
    breed VARCHAR(30),
    birthday_date DATE,
    owner_id UUID,
    FOREIGN KEY (owner_id) REFERENCES cats_schema.persons(id)
);

CREATE TABLE IF NOT EXISTS cats_schema.cat_friends (
    cat_id UUID,
    friend_cat_id UUID,
    FOREIGN KEY (cat_id) REFERENCES cats_schema.cats(id),
    FOREIGN KEY (friend_cat_id) REFERENCES cats_schema.cats(id),
    PRIMARY KEY (cat_id, friend_cat_id)
);

INSERT INTO cats_schema.roles (id, name)
VALUES (1, 'user'),
       (2, 'admin');