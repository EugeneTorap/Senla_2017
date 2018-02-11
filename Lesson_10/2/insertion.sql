USE book_shop;

INSERT INTO book(title, isTheBookInStore, dateReceipted, datePublished, price)
VALUES 
	('Pride and Prejudice', true, '2005-01-11', '1985-04-10', 10),
	('The Lord of the Rings', true, '2014-02-13', '1986-03-16', 15),
	('Jane Eyre', false, '2016-07-28', '1999-08-24', 19),
	('The Bible', true, '2013-05-26', '1975-10-24', 12),
	('To Kill a Mockingbird ', false, '2014-07-24', '1994-06-26', 9),
	('Wuthering Heights', true, '2009-03-23', '1997-10-25', 7),
	('Great Expectations', true, '2004-04-20', '1894-11-16', 13),
	('Little Women', true, '2005-01-25', '1986-09-05', 12),
	('Rebecca', true, '2009-09-29', '1981-08-09', 10),
	('The Hobbit', false, '2017-08-22', '1987-07-07', 11),
	('Birdsong', false, '2017-11-17', '1992-03-27', 15),
	('The Catcher in the Rye', true, '2011-07-21', '1968-02-02', 15);


INSERT INTO reader(name) 
VALUES 
	('Eugene'),
	('Alla'),
	('Vlad'),
	('Maksim'),
	('Olya');


INSERT INTO request(bookId, readerId) 
VALUES 
	(1, 1),
	(5, 2),
	(4, 3),
	(6, 5),
	(1, 4),
	(2, 2),
	(3, 3),
	(4, 4);


INSERT INTO book_order(readerId, status, dateExecuted, price) 
VALUES 
	(1, 'AWAITING', '2017-08-09', 34),
	(2, 'AWAITING', '2017-09-21', 30),
	(4, 'CANCELED', '2017-12-10', 57),
	(4, 'AWAITING', '2017-11-01', 37),
	(3, 'CANCELED', '2017-11-25', 21);


INSERT INTO order_history(orderId, bookId) 
VALUES 
	(1, 4),
	(1, 3),
	(1, 9),
	(2, 7),
	(2, 6),
	(3, 7),
	(3, 8),
	(3, 10),
	(3, 9),
	(4, 1),
	(4, 2),
	(4, 3),
	(5, 11),
	(5, 5);