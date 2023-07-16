INSERT INTO films (title, director, year, rate, duration, subscription_type_required) VALUES
    ('The Shawshank Redemption', 'Frank Darabont', '1994', 9, '0 days 02:22:00', 'Premium'),
    ('The Godfather', 'Francis Ford Coppola', '1972', 9, '0 days 02:55:00', 'Basic'),
    ('Pulp Fiction', 'Quentin Tarantino', '1994', 8, '0 days 02:34:00', 'Premium'),
    ('The Dark Knight', 'Christopher Nolan', '2008', 9, '0 days 02:32:00', 'Basic'),
    ('Fight Club', 'David Fincher', '1999', 8, '0 days 02:19:00', 'Premium'),
    ('Inception', 'Christopher Nolan', '2010', 9, '3 days 02:28:00', NULL),
    ('The Shawshank', 'Frank Darabont', '1994', 9, '3 days 00:00:00', NULL),
    ('The Godfather 2', 'Francis Ford Coppola', '1972', 9, '0 days 02:55:00', NULL),
    ('Fight Club 3', 'David Fincher', '1999', 8, '0 days 02:19:00', NULL);

INSERT INTO series (title, director, year, rate, chapters, subscription_type_required) VALUES
    ('Breaking Bad', 'Vince Gilligan', '2008', 9, 62, 'Premium'),
    ('Game of Thrones', 'David Benioff, D.B. Weiss', '2011', 8, 73, 'Basic'),
    ('Stranger Things', 'The Duffer Brothers', '2016', 8, 34, 'Premium'),
    ('Friends', 'David Crane, Marta Kauffman', '1994', 9, 236, 'Basic'),
    ('The Crown', 'Peter Morgan', '2016', 8, 40, 'Premium'),
    ('Seven Deadly Sins', 'Vince Gilligan', '2008', 9, 62, NULL),
    ('Stranger Any', 'The Duffer Brothers', '2016', 8, 34, NULL),
    ('El Gato Con Botas', 'David Crane, Marta Kauffman', '1994', 9, 236, NULL),
    ('The Office', 'Greg Daniels, Ricky Gervais, Stephen Merchant', '2005', 8, 201, NULL),
    ('Sherlock', 'Mark Gatiss, Steven Moffat', '2010', 9, 13, NULL);

INSERT INTO customers (name, email, phone, country) VALUES
    ('John Smith', 'johnsmith@example.com', '34987654321', 'Spain'),
    ('Maria Garcia', 'mariagarcia@example.com', '34987654322', 'Spain'),
    ('Andrea Rossi', 'andrearossi@example.com', '34987654323', 'Spain'),
    ('Liu Wei', 'liuwei@example.com', '34987654324', 'Spain'),
    ('Sophie Dupont', 'sophiedupont@example.com', '34987654325', 'Spain');

INSERT INTO subscriptions_types (name, price, duration) VALUES
    ('basic', 4.99, INTERVAL '30 days'),
    ('premium', 9.99, INTERVAL '30 days'),
    ('family', 14.99, INTERVAL '30 days');
