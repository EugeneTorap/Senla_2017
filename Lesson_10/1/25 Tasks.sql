#Task 1
SELECT 
    model, speed, hd
FROM
    pc
WHERE
    price < 500;
    

#Task 2
SELECT 
    maker
FROM
    printer
        INNER JOIN
    product USING (model);
    

#Task 3
SELECT 
    model, ram, screen
FROM
    laptop
WHERE
    price > 1000;
    

#Task 4
SELECT 
    *
FROM
    printer
WHERE
    color = 'y';
    

#Task 5
SELECT 
    model, speed, hd
FROM
    pc
WHERE
    price < 600 AND cd IN ('18x' , '24x');
        
        
#Task 6
SELECT 
    maker, speed
FROM
    laptop
        INNER JOIN
    product USING (model)
WHERE
    hd >= 10;


#Task 7
SELECT 
    model, price
FROM
    laptop
        LEFT JOIN
    product USING (model)
WHERE
    maker = 'B' 
UNION SELECT 
    model, price
FROM
    pc
        LEFT JOIN
    product USING (model)
WHERE
    maker = 'B' 
UNION SELECT 
    model, price
FROM
    printer
        LEFT JOIN
    product USING (model)
WHERE
    maker = 'B';


#Task 8
SELECT 
    maker
FROM
    pc
        LEFT JOIN
    product USING (model)
WHERE
    NOT maker IN (SELECT 
            maker
        FROM
            laptop
                LEFT JOIN
            product USING (model));


#Task 9
SELECT DISTINCT
    maker
FROM
    pc
        INNER JOIN
    product USING (model)
WHERE
    speed >= 450;


#Task 10
SELECT 
    model, price
FROM
    printer
ORDER BY price DESC
LIMIT 3;


#Task 11
SELECT 
    AVG(speed)
FROM
    pc;


#Task 12
SELECT 
    AVG(speed)
FROM
    laptop
WHERE
    price > 1000;


#Task 13
SELECT 
    AVG(speed)
FROM
    pc
        INNER JOIN
    product USING (model)
WHERE
    maker = 'A';


#Task 14
SELECT 
    speed, AVG(price)
FROM
    pc
GROUP BY speed;


#Task 15
SELECT 
    hd
FROM
    pc
GROUP BY hd
HAVING COUNT(*) > 1;


#Task 16
SELECT 
    pc1.model, pc2.model, pc1.speed, pc1.ram
FROM
    pc pc1,
    pc pc2
WHERE
    pc1.model > pc2.model
        AND pc1.speed = pc2.speed
        AND pc1.ram = pc2.ram;


#Task 17
SELECT 
    type, model, speed
FROM
    laptop
        INNER JOIN
    product USING (model)
WHERE
    laptop.speed < (SELECT 
            MIN(pc.speed)
        FROM
            pc);


#Task 18
SELECT 
    maker, price
FROM
    printer
        INNER JOIN
    product USING (model)
WHERE
    color = 'y'
        AND price = (SELECT 
            MIN(price)
        FROM
            printer);


#Task 19
SELECT 
    maker, AVG(screen)
FROM
    laptop
        INNER JOIN
    product USING (model)
GROUP BY maker;


#Task 20
SELECT 
    maker, COUNT(DISTINCT model) AS unique_model
FROM
    pc
        INNER JOIN
    product USING (model)
GROUP BY maker
HAVING unique_model >= 3;


#Task 21
SELECT 
    maker, MAX(price)
FROM
    pc
        INNER JOIN
    product USING (model)
GROUP BY maker;


#Task 22
SELECT 
    speed, AVG(price)
FROM
    pc
WHERE
    speed > 600
GROUP BY speed
HAVING COUNT(*) > 1;


#Task 23
SELECT 
    maker
FROM
    pc
        LEFT JOIN
    product USING (model)
WHERE
    NOT maker IN (SELECT 
            maker
        FROM
            pc
                LEFT JOIN
            product USING (model)
        WHERE
            speed < 750)
        AND NOT maker IN (SELECT 
            maker
        FROM
            laptop
                LEFT JOIN
            product USING (model)
        WHERE
            speed < 750);
            

#Task 24
SELECT 
    model, price
FROM
    pc
        LEFT JOIN
    product USING (model)
UNION SELECT 
    model, price
FROM
    laptop
        LEFT JOIN
    product USING (model)
UNION SELECT 
    model, price
FROM
    printer
        LEFT JOIN
    product USING (model)
ORDER BY price DESC
LIMIT 3;


#Task 25
SELECT DISTINCT
    maker
FROM
    printer
        LEFT JOIN
    product USING (model)
WHERE
    maker IN ((SELECT 
            maker
        FROM
            pc
                LEFT JOIN
            product USING (model)
        WHERE
            pc.ram = (SELECT 
                    MIN(pc.ram)
                FROM
                    pc)
        LIMIT 1) , (SELECT 
                maker
            FROM
                pc
                    LEFT JOIN
                product USING (model)
            WHERE
                pc.speed = (SELECT 
                        MAX(pc.speed)
                    FROM
                        pc
                    LIMIT 1)));