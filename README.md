# Project Readme

## Introduction
Welcome to this small yet versatile project! This project is designed to simulate various actions, including searching, filtering, and placing orders. It demonstrates a simplified relationship between different entities such as products, colors, categories, reviews, and orders.

## Entity Relationships
- **Product**: The central entity, each product can have multiple colors and belong to different categories. It also has a collection of reviews.
- **Color**: Products can have multiple colors associated with them, and vice versa.
- **Category**: Products can belong to various categories.
- **Review**: Each product can have multiple reviews associated with it.
- **Order**: The project allows for placing orders for products.

## Project Actions
### 1. Search API
In the Search API, we utilize the Single Page Application (SPA) specification to create a powerful and comprehensive search feature. This enables users to search for products, colors, and categories, making it as versatile as possible. The goal is to provide a robust and user-friendly search experience.

### 2. Order API
The project also includes an API for placing orders. This allows users to select products and proceed with placing orders for them. The order API handles the order creation and processing.

### 3. How to Run the Project Locally
**3.1** Clone the project repository to your local machine.

**3.2** Set up your Postman environment by importing the collection from the following link: [Postman Collection](https://galactic-equinox-468140.postman.co/workspace/Team-Workspace~31ef1d59-474d-45c1-808f-85a7d537e462/collection/15890173-c2e06563-1d73-4a12-aead-8968eed7c4aa?action=share&creator=15890173).

**Note:** In the Search API, there are several use cases, and here is a sample request body for each case:

**Sample Request Body for Search API:**
```json
{
    "operator": "OR",
    "pageDto": {
        "pageNo" : 0,
        "pageSize" : 1,
        "sort" : "ASC",
        "sortByColumn" : "id"
    },
    "searchRequestDto" : [
    {
        "column": "id",
        "value" :"5",
        "joinTable":"reviews",
        "operation": "JOIN"
    }
    ]
}
```

**Operators**:
- `OR`: Used to combine search conditions with logical OR.
- `AND`: Used to combine search conditions with logical AND.

**Operations**:
- `EQUAL`: Matches exact values.
- `LIKE`: Performs a partial match.
- `IN`: Checks if a value is in a list of values.
- `GREATER_THAN`: Compares if a value is greater than a given value.
- `LESS_THAN`: Compares if a value is less than a given value.
- `BETWEEN`: Checks if a value falls within a range.
- `JOIN`: Joins tables for more complex queries.

**Sample Request Body for EQUAL Operation:**
```json
{
    "operator": "OR",
    "pageDto": {
        "pageNo" : 0,
        "pageSize" : 5,
        "sort" : "ASC",
        "sortByColumn" : "id"
    },
    "searchRequestDto" : [
    {
        "column": "name",
        "value" :"Candy with sugar",
        "operation": "EQUAL"
    },
    {
        "column": "name",
        "value" :"Candy without sugar",
        "operation": "EQUAL"
    }
    ]
}
```

**Sample Request Body for LIKE Operation:**
```json
{
    "operator": "OR",
    "pageDto": {
        "pageNo" : 0,
        "pageSize" : 5,
        "sort" : "ASC",
        "sortByColumn" : "id"
    },
    "searchRequestDto" : [
    {
        "column": "name",
        "value" :"sugar",
        "operation": "LIKE"
    }
    ]
}
```

**Sample Request Body for JOIN Operation:**
```json
{
    "operator": "AND",
    "pageDto": {
        "pageNo" : 0,
        "pageSize" : 1,
        "sort" : "ASC",
        "sortByColumn" : "id"
    },
    "searchRequestDto" : [
    {
        "column": "id",
        "value" :"5",
        "joinTable":"reviews",
        "operation": "JOIN"
    },
    {
        "column": "name",
        "value" :"Purple",
        "joinTable":"colors",
        "operation": "JOIN"
    }
    ]
}
```

Feel free to explore other operations and combinations for your search needs.

### 4. Query to Insert Data

categories:  INSERT INTO "public"."categories" ("id", "name") VALUES (1, 'Toy');
INSERT INTO "public"."categories" ("id", "name") VALUES (2, 'Food');
INSERT INTO "public"."categories" ("id", "name") VALUES (3, 'Water');
INSERT INTO "public"."categories" ("id", "name") VALUES (4, 'Candy');
INSERT INTO "public"."categories" ("id", "name") VALUES (5, 'Tree');
 colors

INSERT INTO "public"."colors" ("id", "name") VALUES (1, 'Red');
INSERT INTO "public"."colors" ("id", "name") VALUES (2, 'Blue');
INSERT INTO "public"."colors" ("id", "name") VALUES (3, 'Green');
INSERT INTO "public"."colors" ("id", "name") VALUES (4, 'Yellow');
INSERT INTO "public"."colors" ("id", "name") VALUES (5, 'Purple');


product INSERT INTO "public"."products" ("id", "description", "image", "name", "price") VALUES (1, 'Description 1', 'image1.jpg', 'Candy with sugar', 19.99);
INSERT INTO "public"."products" ("id", "description", "image", "name", "price") VALUES (2, 'Description 2', 'image2.jpg', 'Candy withou sugar', 29.99);
INSERT INTO "public"."products" ("id", "description", "image", "name", "price") VALUES (3, 'Description 3', 'image3.jpg', 'water with red color', 39.99);
INSERT INTO "public"."products" ("id", "description", "image", "name", "price") VALUES (4, 'Description 4', 'image4.jpg', 'water normal', 49.99);
INSERT INTO "public"."products" ("id", "description", "image", "name", "price") VALUES (5, 'Description 5', 'image5.jpg', 'big tree', 59.99);
INSERT INTO "public"."products" ("id", "description", "image", "name", "price") VALUES (6, 'Description 1', 'image1.jpg', 'small tree', 19.99);
INSERT INTO "public"."products" ("id", "description", "image", "name", "price") VALUES (7, 'Description 1', 'image1.jpg', 'big toy', 29.99);
INSERT INTO "public"."products" ("id", "description", "image", "name", "price") VALUES (8, 'Description 1', 'image1.jpg', 'small toy', 39.99);
INSERT INTO "public"."products" ("id", "description", "image", "name", "price") VALUES (9, 'Description 2', 'image2.jpg', 'food ', 29.99);
INSERT INTO "public"."products" ("id", "description", "image", "name", "price") VALUES (10, 'Description 3', 'image3.jpg', 'normal food', 39.99);
INSERT INTO "public"."products" ("id", "description", "image", "name", "price") VALUES (11, 'Description 4', 'image4.jpg', 'food + snack', 49.99);
INSERT INTO "public"."products" ("id", "description", "image", "name", "price") VALUES (12, 'Description 5', 'image5.jpg', 'very big toy', 59.99);


reviews INSERT INTO "public"."reviews" ("id", "content", "product_id") VALUES (1, 'Great product!', 1);
INSERT INTO "public"."reviews" ("id", "content", "product_id") VALUES (2, 'Not bad at all.', 2);
INSERT INTO "public"."reviews" ("id", "content", "product_id") VALUES (3, 'I love it!', 3);
INSERT INTO "public"."reviews" ("id", "content", "product_id") VALUES (4, 'Could be better.', 4);
INSERT INTO "public"."reviews" ("id", "content", "product_id") VALUES (5, 'Highly recommended.', 5);
INSERT INTO "public"."reviews" ("id", "content", "product_id") VALUES (6, 'love it', 6);
INSERT INTO "public"."reviews" ("id", "content", "product_id") VALUES (7, 'verry beauty', 2);
INSERT INTO "public"."reviews" ("id", "content", "product_id") VALUES (8, 'greate too ', 1);

Thank you for using this project, and I hope it meets your needs for simulating actions related to products, colors, categories, reviews, and orders. If you have any questions or encounter any issues, please don't hesitate to reach out to me.

Happy coding!
