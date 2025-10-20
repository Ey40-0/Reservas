-- Bueno no esta de más incluir un pequeño script que no quite lo obvio...

/*
  Resalto que en el desarrollo de esta aplicación se
  siguieron al pie de la letra todas las instrucciones
  sin asumir datos de más.
*/

/* drop database reservas; */
use reservas;

/*
SELECT * FROM product_family;
SELECT * FROM product;
SELECT * FROM class;
SELECT * FROM client;
SELECT * FROM event;
SELECT * FROM register;

SELECT 
    e.id_eve,
    e.id_cla,
    e.start_at,
    e.end_at,
    c.name AS class_name
FROM client cl
JOIN product p ON cl.id_pro = p.id_pro
JOIN class c ON c.id_fam = p.id_fam
JOIN event e ON e.id_cla = c.id_cla
WHERE cl.id_cli = 1
  AND e.start_at >= NOW() - INTERVAL 10 MINUTE
ORDER BY e.start_at;

SELECT 
	id_cla, name,
    c.id_fam, description
FROM class c
JOIN product_family pf ON c.id_fam = pf.id_fam
ORDER BY name;

SELECT 
    c.name AS class_name,
    cl.run AS client_run,
    DATE_FORMAT(e.start_at, '%d/%m/%Y') AS class_date,
    DATE_FORMAT(e.start_at, '%H:%i') AS start_time,
    DATE_FORMAT(e.end_at, '%H:%i') AS end_time,
    r.registered_at
FROM register r
INNER JOIN event e ON r.id_eve = e.id_eve
INNER JOIN client cl ON r.id_cli = cl.id_cli
INNER JOIN class c ON e.id_cla = c.id_cla;
*/