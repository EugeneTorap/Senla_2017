USE book_shop;

INSERT INTO book(title, isTheBookInStore, requestAmount, dateReceipted, datePublished, price)
VALUES ('Pride and Prejudice', true, 3, '2005-01-11', '1985-04-10', 10);
INSERT INTO book(title, isTheBookInStore, requestAmount, dateReceipted, datePublished, price)
VALUES ('The Lord of the Rings', true, 4, '2014-02-13', '1986-03-16', 15);
INSERT INTO book(title, isTheBookInStore, requestAmount, dateReceipted, datePublished, price)
VALUES ('Jane Eyre', false, 5, '2016-07-28', '1999-08-24', 19);
INSERT INTO book(title, isTheBookInStore, requestAmount, dateReceipted, datePublished, price)
VALUES ('The Bible', true, 10, '2013-05-26', '1975-10-24', 12);
INSERT INTO book(title, isTheBookInStore, requestAmount, dateReceipted, datePublished, price)
VALUES ('To Kill a Mockingbird ', false, 2, '2014-07-24', '1994-06-26', 9);
INSERT INTO book(title, isTheBookInStore, requestAmount, dateReceipted, datePublished, price)
VALUES ('Wuthering Heights', true, 8, '2009-03-23', '1997-10-25', 7);
INSERT INTO book(title, isTheBookInStore, requestAmount, dateReceipted, datePublished, price)
VALUES ('Great Expectations', true, 4, '2004-04-20', '1894-11-16', 13);
INSERT INTO book(title, isTheBookInStore, requestAmount, dateReceipted, datePublished, price)
VALUES ('Little Women', true, 4, '2005-01-25', '1986-09-05', 12);
INSERT INTO book(title, isTheBookInStore, requestAmount, dateReceipted, datePublished, price)
VALUES ('Rebecca', true, 5, '2009-09-29', '1981-08-09', 10);
INSERT INTO book(title, isTheBookInStore, requestAmount, dateReceipted, datePublished, price)
VALUES ('The Hobbit', false, 5, '2017-08-22', '1987-07-07', 11);
INSERT INTO book(title, isTheBookInStore, requestAmount, dateReceipted, datePublished, price)
VALUES ('Birdsong', false, 3, '2017-11-17', '1992-03-27', 15);
INSERT INTO book(title, isTheBookInStore, requestAmount, dateReceipted, datePublished, price)
VALUES ('The Catcher in the Rye', true, 3, '2011-07-21', '1968-02-02', 15);


INSERT INTO reader(name) VALUES ('Eugene');
INSERT INTO reader(name) VALUES ('Alla');
INSERT INTO reader(name) VALUES ('Vlad');
INSERT INTO reader(name) VALUES ('Maksim');
INSERT INTO reader(name) VALUES ('Olya');


INSERT INTO request(bookId, readerId) VALUES (1, 1);
INSERT INTO request(bookId, readerId) VALUES (5, 2);
INSERT INTO request(bookId, readerId) VALUES (4, 3);
INSERT INTO request(bookId, readerId) VALUES (6, 5);
INSERT INTO request(bookId, readerId) VALUES (1, 4);
INSERT INTO request(bookId, readerId) VALUES (2, 2);
INSERT INTO request(bookId, readerId) VALUES (3, 3);
INSERT INTO request(bookId, readerId) VALUES (4, 4);


INSERT INTO book_order(readerId, dateExecuted, price) VALUES (1, '2017-08-09', 34);
INSERT INTO book_order(readerId, dateExecuted, price) VALUES (2, '2017-09-21', 30);
INSERT INTO book_order(readerId, dateExecuted, price) VALUES (1, '2017-12-10', 57);
INSERT INTO book_order(readerId, dateExecuted, price) VALUES (1, '2017-11-01', 37);
INSERT INTO book_order(readerId, dateExecuted, price) VALUES (1, '2017-11-25', 21);


INSERT INTO order_history(orderId, bookId) VALUES (1, 4);
INSERT INTO order_history(orderId, bookId) VALUES (1, 3);
INSERT INTO order_history(orderId, bookId) VALUES (1, 9);
INSERT INTO order_history(orderId, bookId) VALUES (2, 7);
INSERT INTO order_history(orderId, bookId) VALUES (2, 6);
INSERT INTO order_history(orderId, bookId) VALUES (3, 7);
INSERT INTO order_history(orderId, bookId) VALUES (3, 8);
INSERT INTO order_history(orderId, bookId) VALUES (3, 10);
INSERT INTO order_history(orderId, bookId) VALUES (3, 9);
INSERT INTO order_history(orderId, bookId) VALUES (4, 1);
INSERT INTO order_history(orderId, bookId) VALUES (4, 2);
INSERT INTO order_history(orderId, bookId) VALUES (4, 3);
INSERT INTO order_history(orderId, bookId) VALUES (5, 11);
INSERT INTO order_history(orderId, bookId) VALUES (5, 5);