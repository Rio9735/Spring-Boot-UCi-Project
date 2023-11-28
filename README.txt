Exercise 1Here is an example of how your README would look like applying the tips I gave you, but in English:

# ECASA Warehouse Management API

This API allows you to manage the warehouse of ECASA, a company that distributes consumer products. The warehouse is divided into sections, each of which can only store one type of product. The API provides endpoints to create, update, delete and query the sections and products of the warehouse.

## Requirements

- Java 11 or higher
- Maven 3.6.3 or higher
- Postman or similar to test the API

## Installation

- Download the executable jar file from the target folder
- Run the command `java -jar exercise-1-0.0.1-SNAPSHOT.jar` from the folder where the file is located(target folder)
- The API will start on port 8080

## Usage

The API provides two users with different roles:

- username: admin
- password: password
- role: admin

- username: operator
- password: password
- role: operator

The API is designed and developed to use JWT as the authentication method, however, 
to facilitate testing with Postman, a modification was added to use Basic Auth.

The API uses an H2 in-memory database, so when you close the application, 
the data that you have entered or modified will be lost. Also, when you start 
the application, the types of container and product proposed in the exercise 
order and 2 sections with id 1 and 2 are inserted into the database.

## Endpoints

### /section/list

This endpoint allows you to get the list of all the sections of the warehouse.

- Method: GET
- Parameters: None
- Body: None
- Response: List of sections with the format:

```json
[
  {
    "id": 2,
    "size": 10.0,
    "productType": {
      "id": 2,
      "name": "Meat"
    }
  },
  {
    "id": 3,
    "size": 10.0,
    "productType": {
      "id": 2,
      "name": "Meat"
    }
  }
]
```

### /section/createorupdate

This endpoint allows you to create or update a section of the warehouse.

- Method: POST
- Parameters: None
- Body: Section to create or update with the format:

```json
{
  "id": 3,
  "size": 10,
  "productType": {
    "id": 2
  }
}
```

- Response: Success or error message

### /section/delete

This endpoint allows you to delete a section of the warehouse. It can only 
be accessed by users with the administrator role.

- Method: DELETE
- Parameters: sectionId with the value of the id of the section to delete
- Body: None
- Response: Success or error message

### /product/search

This endpoint allows you to search for products by their attributes, 
and returns a list of products that match all the attributes that are sent in the body.

- Method: GET
- Parameters: None
- Body: Partial or complete product with the format:

```json
{
  "name": "Refrigerator",
  "size": 10.0,
  "color": "White",
  "prize": 3000.0,
  "currency": "cup",
  "fragile": true,
  "batch": "1234",
  "productType": {
    "id": 1
  },
  "container": {
    "id": 1
  },
  "sections": [
    {
      "id": {
        "idSection": 2
      }
    }
  ]
}
```

- Response: List of products with the format:

```json
[
  {
    "id": 1,
    "name": "Refrigerator",
    "size": 10.0,
    "color": "White",
    "prize": 3000.0,
    "currency": "cup",
    "fragile": true,
    "batch": "1234",
    "productType": {
      "id": 1,
      "name": "Appliances"
    },
    "container": {
      "id": 1,
      "name": "Cardboard"
    },
    "sections": [
      {
        "id": {
          "idProduct": 1,
          "idSection": 2
        },
        "section": {
          "id": 2,
          "size": 10.0,
          "productType": {
            "id": 2,
            "name": "Meat"
          }
        },
        "cant": 93
      }
    ]
  }
]
```

### /product/addtosection

This endpoint allows you to add a product to a section, creating it if 
it does not exist or updating its quantity if it already exists.

- Method: POST
- Parameters: sectionId with the value of the id of the section to which you want to add 
the product, and cant with the value of the quantity of the product that you want to add to the section
- Body: Complete product with the format:

```json
{
  "name": "Refrigerator",
  "size": 10,
  "color": "White",
  "prize": 3000,
  "currency": "cup",
  "fragile": true,
  "batch": "1234",
  "productType": {
    "id": 1
  },
  "container": {
    "id": 1
  }
}
```

- Response: Success or error message

