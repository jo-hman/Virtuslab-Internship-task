# Virtuslab-Internship-task

Application was built with Spring Boot. It runs on localhost:8080.

Endpoints to hit:
localhost:8080/api/v1/basket:
- POST - /create (creates a basket and returns it's id)
- PUT - /{basketId}/add-product
- PUT - /{basketId}/remove-product
- GET - /{basketId}/get-products
- GET - /{basketId}/get-receipt

For purpose of this prototype application instead of creating a whole database for baskets I used a list of baskets inside BasketService. Similar to ProductDb.

All tests I considered necessary were created using JUnit.
