#Task 1
SELECT 
    model, speed, hd
FROM
    pc
WHERE
    price >= 500;
    

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
    price >= 600 AND cd IN ('18x' , '24x');
        
        
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
SELECT 
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
LIMIT 0 , 3;


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


#Task 15


#Task 16


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


#Task 20
SELECT 
    maker, COUNT(DISTINCT model)
FROM
    pc
        LEFT JOIN
    product USING (model)
WHERE
    maker = 'A'
        AND (SELECT 
            COUNT(DISTINCT model)
        FROM
            pc
                LEFT JOIN
            product USING (model)
        WHERE
            maker = 'A') >= 3 
UNION SELECT 
    maker, COUNT(DISTINCT model)
FROM
    pc
        LEFT JOIN
    product USING (model)
WHERE
    maker = 'B'
        AND (SELECT 
            COUNT(DISTINCT model)
        FROM
            pc
                LEFT JOIN
            product USING (model)
        WHERE
            maker = 'B') >= 3
UNION SELECT 
    maker, COUNT(DISTINCT model)
FROM
    pc
        LEFT JOIN
    product USING (model)
WHERE
    maker = 'C'
        AND (SELECT 
            COUNT(DISTINCT model)
        FROM
            pc
                LEFT JOIN
            product USING (model)
        WHERE
            maker = 'C') >= 3;


#Task 21
SELECT 
    maker, MAX(price)
FROM
    pc
        LEFT JOIN
    product USING (model)
WHERE
    maker = 'A' 
UNION SELECT 
    maker, MAX(price)
FROM
    pc
        LEFT JOIN
    product USING (model)
WHERE
    maker = 'B' 
UNION SELECT 
    maker, MAX(price)
FROM
    pc
        LEFT JOIN
    product USING (model)
WHERE
    maker = 'C';


#Task 22



#Task 23
SELECT 
    maker
FROM
    pc,
    laptop
        LEFT JOIN
    product USING (model)
WHERE
    maker = 'A' AND pc.speed >= 750
        AND laptop.speed >= 750 
UNION SELECT 
    maker
FROM
    pc,
    laptop
        LEFT JOIN
    product USING (model)
WHERE
    maker = 'B' AND pc.speed >= 750
        AND laptop.speed >= 750 
UNION SELECT 
    maker
FROM
    pc,
    laptop
        LEFT JOIN
    product USING (model)
WHERE
    maker = 'C' AND pc.speed >= 750
        AND laptop.speed >= 750;


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
LIMIT 0 , 3;


#Task 25
SELECT 
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
        LIMIT 0 , 1) , (SELECT 
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
                    LIMIT 0 , 1)));