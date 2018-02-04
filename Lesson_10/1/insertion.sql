INSERT INTO product(maker, model, type) VALUES ('A', 'B-15', 'pc');
INSERT INTO product(maker, model, type) VALUES ('B', 'B-16', 'pc');
INSERT INTO product(maker, model, type) VALUES ('B', 'B-17', 'pc');
INSERT INTO product(maker, model, type) VALUES ('B', 'B-18', 'pc');
INSERT INTO product(maker, model, type) VALUES ('C', 'B-19', 'pc');
INSERT INTO product(maker, model, type) VALUES ('C', 'L-15', 'laptop');
INSERT INTO product(maker, model, type) VALUES ('C', 'L-17', 'laptop');
INSERT INTO product(maker, model, type) VALUES ('B', 'L-16', 'laptop');
INSERT INTO product(maker, model, type) VALUES ('C', 'P-15', 'printer');
INSERT INTO product(maker, model, type) VALUES ('A', 'P-16', 'printer');

INSERT INTO pc(model, speed, ram, hd, cd, price) VALUES ('B-15', 2600, 3000, 12, '4x', 600);
INSERT INTO pc(model, speed, ram, hd, cd, price) VALUES ('B-17', 800, 3000, 2, '4x', 800);
INSERT INTO pc(model, speed, ram, hd, cd, price) VALUES ('B-18', 650, 3000, 12, '4x', 650);
INSERT INTO pc(model, speed, ram, hd, cd, price) VALUES ('B-16', 4400, 8000, 15, '24x', 400);
INSERT INTO pc(model, speed, ram, hd, cd, price) VALUES ('B-19', 200, 16000, 3, '12x', 380);
INSERT INTO pc(model, speed, ram, hd, cd, price) VALUES ('B-16', 200, 16000, 8, '18x', 750);
INSERT INTO pc(model, speed, ram, hd, cd, price) VALUES ('B-15', 2600, 32000, 3, '4x', 990);

INSERT INTO laptop(model, speed, ram, hd, price, screen) VALUES ('L-15', 4700, 12000, 11, 1700, 8);
INSERT INTO laptop(model, speed, ram, hd, price, screen) VALUES ('L-15', 4400, 10000, 15, 2000, 10);
INSERT INTO laptop(model, speed, ram, hd, price, screen) VALUES ('L-16', 2700, 8000, 6, 1280, 12);
INSERT INTO laptop(model, speed, ram, hd, price, screen) VALUES ('L-17', 900, 6000, 6, 650, 13);
INSERT INTO laptop(model, speed, ram, hd, price, screen) VALUES ('L-15', 150, 32000, 3, 790, 8);

INSERT INTO printer(model, color, type, price) VALUES ('P-15', 'y', 'Laser', 600);
INSERT INTO printer(model, color, type, price) VALUES ('P-15', 'u', 'Jet', 400);
INSERT INTO printer(model, color, type, price) VALUES ('P-16', 'y', 'Laser', 380);
INSERT INTO printer(model, color, type, price) VALUES ('P-16', 'u', 'Matrix', 750);
INSERT INTO printer(model, color, type, price) VALUES ('P-15', 'y', 'Jet', 990);