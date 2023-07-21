INSERT INTO films (title, director, year, rate, duration, subscription_type_required) VALUES
    ('The Shawshank Redemption', 'Frank Darabont', '1994', 9, '0 days 02:22:00', 'Premium'),
    ('The Godfather', 'Francis Ford Coppola', '1972', 9, '0 days 02:55:00', 'Basic,Premium'),
    ('Pulp Fiction', 'Quentin Tarantino', '1994', 8, '0 days 02:34:00', 'Premium'),
    ('The Dark Knight', 'Christopher Nolan', '2008', 9, '0 days 02:32:00', 'Basic,Premium'),
    ('Fight Club', 'David Fincher', '1999', 8, '0 days 02:19:00', 'Premium'),
    ('Inception', 'Christopher Nolan', '2010', 9, '3 days 02:28:00', NULL),
    ('The Shawshank', 'Frank Darabont', '1994', 9, '3 days 00:00:00', NULL),
    ('The Godfather 2', 'Francis Ford Coppola', '1972', 9, '0 days 02:55:00', NULL),
    ('Fight Club 3', 'David Fincher', '1999', 8, '0 days 02:19:00', NULL);

INSERT INTO series (title, director, year, rate, chapters, subscription_type_required) VALUES
    ('Breaking Bad', 'Vince Gilligan', '2008', 9, 62, 'Premium'),
    ('Game of Thrones', 'David Benioff, D.B. Weiss', '2011', 8, 73, 'Basic,Premium'),
    ('Stranger Things', 'The Duffer Brothers', '2016', 8, 34, 'Premium'),
    ('Friends', 'David Crane, Marta Kauffman', '1994', 9, 236, 'Basic,Premium'),
    ('The Crown', 'Peter Morgan', '2016', 8, 40, 'Premium'),
    ('Seven Deadly Sins', 'Vince Gilligan', '2008', 9, 62, NULL),
    ('Stranger Any', 'The Duffer Brothers', '2016', 8, 34, NULL),
    ('El Gato Con Botas', 'David Crane, Marta Kauffman', '1994', 9, 236, NULL),
    ('The Office', 'Greg Daniels, Ricky Gervais, Stephen Merchant', '2005', 8, 201, NULL),
    ('Sherlock', 'Mark Gatiss, Steven Moffat', '2010', 9, 13, NULL);

INSERT INTO customers (name, user_id) VALUES
    ('John Smith', 'johnsmith@example.com'),
    ('Maria Garcia', 'mariagarcia@example.com'),
    ('Andrea Rossi', 'andrearossi@example.com'),
    ('Liu Wei', 'liuwei@example.com'),
    ('Sophie Dupont', 'sophiedupont@example.com');

INSERT INTO subscriptions_types (name, price, duration)
VALUES
  ('basic', 9.99, '1 month'),
  ('premium', 19.99, '3 months'),
  ('familiar', 29.99, '6 months');
