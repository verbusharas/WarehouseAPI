![GitHub Logo](/banner/api_banner.jpg)

Developer: [Šarūnas Verbus](https://www.linkedin.com/in/sarunas-verbus/)

Client: [Visma Lietuva](https://www.visma.lt/)

This is a simple Open API providing view/modify services for product warehouse information system.
Application is implemented in Java using Spring framework with Spring-Boot plugin. 
Data is created ant filtered using Rest API, persisted into in-memory H2 database.

##
### API Documentation
You can access interactive auto-generated OpenApi3.0 documentation via `/api/documentation`


| HTTP method | URI | Description | Query Sample
| ----------- | --------- | ----- | ---------- |
| **`GET`** | `/api/products/` | Returns all products from database | |
| **`GET`** | `/api/products/expires_before` | Returns all products expiring before specified date | `/api/products/expires_before?date=2020-09-25`
| **`GET`** | `/api/products/of` | Returns products of specified type type and not exceeding specified quantity | `/api/products/of?type=dairy&max_quantity=3`
| **`GET`** | `/api/products/{id}` | Returns specified product | `/api/products/1`
| **`POST`** | `/api/products/` | Adds new product to database | |
| **`PUT`** | `/api/products/{ID}` | Updates details of existing product | |
| **`DELETE`** | `/api/products/{ID}` | Removes product from database | |

### For quick H2 database population
`INSERT INTO products (id, type, name, expiry_date, quantity) VALUES (1, 'dairy', 'milk', '2020-10-23', 9);`

`INSERT INTO products (id, type, name, expiry_date, quantity) VALUES (2, 'dairy', 'cheese', '2020-12-22', 18);`

`INSERT INTO products (id, type, name, expiry_date, quantity) VALUES (3, 'dairy', 'eggs', '2020-10-08', 3);`

`INSERT INTO products (id, type, name, expiry_date, quantity) VALUES (4, 'vegetable', 'cucumber', '2020-11-27', 4);`

`INSERT INTO products (id, type, name, expiry_date, quantity) VALUES (5, 'vegetable', 'beetroot', '2021-01-11',  25);`

`INSERT INTO products (id, type, name, expiry_date, quantity) VALUES (6, 'vegetable', 'carrot', '2020-10-04', 7);`

`INSERT INTO products (id, type, name, expiry_date, quantity) VALUES (7, 'fruit', 'banana', '2020-11-01', 4);`

`INSERT INTO products (id, type, name, expiry_date, quantity) VALUES (8, 'fruit', 'orange', '2020-10-20',  15);`

`INSERT INTO products (id, type, name, expiry_date, quantity) VALUES (9, 'fruit', 'avocado', '2020-10-02', 6);`
