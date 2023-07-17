DROP TABLE IF EXISTS films_watched;
DROP TABLE IF EXISTS series_watched;
DROP TABLE IF EXISTS customers;
DROP TABLE IF EXISTS subscriptions_types;
DROP TABLE IF EXISTS subscriptions;
DROP TABLE IF EXISTS films;
DROP TABLE IF EXISTS series;

CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE films (
    id UUID default uuid_generate_v4() PRIMARY KEY,
    title VARCHAR(50) NOT NULL,
    director VARCHAR(50) NOT NULL,
    year VARCHAR(4) NOT NULL,
    url varchar(255),
    rate INTEGER,
    duration VARCHAR(20),
    subscription_type_required VARCHAR(20) DEFAULT NULL
);

CREATE TABLE series (
    id UUID default uuid_generate_v4() PRIMARY KEY,
    title VARCHAR(50) NOT NULL,
    director VARCHAR(50) NOT NULL,
    year VARCHAR(4) NOT NULL,
    url varchar(255),
    rate INTEGER,
    chapters INTEGER,
    subscription_type_required VARCHAR(20)
);

CREATE TABLE customers (
    id UUID default uuid_generate_v4() PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL UNIQUE,
    phone VARCHAR(12) NOT NULL,
    country VARCHAR(20)
);

CREATE TABLE films_watched (
    id UUID default uuid_generate_v4() PRIMARY KEY,
    customer_id UUID NOT NULL,
    film_id UUID NOT NULL,
    date DATE NOT NULL,
    FOREIGN KEY (customer_id) REFERENCES customers (id) ON DELETE CASCADE,
    FOREIGN KEY (film_id) REFERENCES films (id) ON DELETE CASCADE
);

CREATE TABLE series_watched (
    id UUID default uuid_generate_v4() PRIMARY KEY,
    customer_id UUID NOT NULL,
    serie_id UUID NOT NULL,
    date DATE NOT NULL,
    FOREIGN KEY (customer_id) REFERENCES customers (id) ON DELETE CASCADE,
    FOREIGN KEY (serie_id) REFERENCES series (id) ON DELETE CASCADE
);

CREATE TABLE subscriptions_types (
    id UUID default uuid_generate_v4() PRIMARY KEY,
    name VARCHAR(20) UNIQUE NOT NULL,
    price DOUBLE PRECISION NOT NULL,
    duration VARCHAR(20) NOT NULL
);

CREATE TABLE subscriptions (
    id UUID DEFAULT uuid_generate_v4() PRIMARY KEY,
    customer_id UUID NOT NULL,
    date_start DATE NOT NULL,
    date_end DATE NOT NULL,
    type_id UUID NOT NULL,
    FOREIGN KEY (customer_id) REFERENCES customers (id) ON DELETE CASCADE,
    FOREIGN KEY (type_id) REFERENCES subscriptions_types (id) ON DELETE CASCADE
);