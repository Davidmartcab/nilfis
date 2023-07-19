# NILFIS API Documentation

This document provides an overview of the endpoints available in the NILFIS API. The API allows users to perform various operations related to customers, films, series, films watched, series watched, subscriptions, and subscription types. 

When appear:
- 10 -> Integer
- 100 -> Long
- 10.100 -> BigDecimal
## Customers Endpoint
### Create a Customer
- URL: `/customers`
- Method: `POST`
- Request Body:

```json

{
  "id": "<UUID>",
  "name": "<String>",
  "email": "<String>",
  "phone": "<String>",
  "country": "<String>"
}
``` 
- Response:
- Status Code: `200 OK`
- Response Body:

```json

{
  "id": "<UUID>",
  "name": "<String>",
  "email": "<String>",
  "phone": "<String>",
  "country": "<String>",
  "verified": false
}
```
### Get a Customer by ID
- URL: `/customers/{id}`
- Method: `GET`
- Path Variable: `id` - The ID of the customer.
- Response:
- Status Code: `200 OK`
- Response Body:

```json

{
  "id": "<UUID>",
  "name": "<String>",
  "email": "<String>",
  "phone": "<String>",
  "country": "<String>",
  "verified": false
}
```
### Get a Customer by Email
- URL: `/customers/email/{email}`
- Method: `GET`
- Path Variable: `email` - The email of the customer.
- Response:
- Status Code: `200 OK`
- Response Body:

```json

{
  "id": "<UUID>",
  "name": "<String>",
  "email": "<String>",
  "phone": "<String>",
  "country": "<String>",
  "verified": false
}
```
### Get All Customers
- URL: `/customers`
- Method: `GET`
- Response:
- Status Code: `200 OK`
- Response Body: Array of customer objects:

```json

[
  {
    "id": "<UUID>",
    "name": "<String>",
    "email": "<String>",
    "phone": "<String>",
    "country": "<String>",
    "verified": false
  }
]
```
### Delete a Customer
- URL: `/customers/{id}`
- Method: `DELETE`
- Path Variable: `id` - The ID of the customer.
- Response:
- Status Code: `204 No Content`
## Films Endpoint
### Create a Film
- URL: `/films/new`
- Method: `POST`
- Request Body:

```json

{
  "title": "<String>",
  "director": "<String>",
  "year": "<String>",
  "url": "<String>",
  "rate": 10,
  "duration": "<String>",
  "subscription_type_required": "<String>"
}
``` 
- Response:
- Status Code: `200 OK`
- Response Body:

```json

{
  "id": "<UUID>",
  "title": "<String>",
  "director": "<String>",
  "year": "<String>",
  "url": "<String>",
  "rate": 10,
  "duration": "<String>",
  "subscription_type_required": "<String>"
}
```
### Get a Film by ID
- URL: `/films/{id}`
- Method: `GET`
- Path Variable: `id` - The ID of the film.
- Response:
- Status Code: `200 OK`
- Response Body:

```json

{
  "id": "<UUID>",
  "title": "<String>",
  "director": "<String>",
  "year": "<String>",
  "url": "<String>",
  "rate": 10,
  "duration": "<String>",
  "subscription_type_required": "<String>"
}
```
### Get All Films
- URL: `/films`
- Method: `POST`
- Request Headers:
- `page` (optional, default: 0) - The page number for pagination.
- `size` (optional, default: 10) - The number of films per page.
- `sortType` (optional) - The type of sorting (NONE, ASC, DESC).
- Request Body (optional):

```json

{
  "type_name": "<String>",
  "minRate": 10,
  "maxRate": 10,
  "titleStart": "<String>",
  "directorStart": "<String>",
  "yearStart": "<String>",
  "yearEnd": "<String>"
}
``` 
- Response:
- Status Code: `200 OK`
- Response Body: Array of film objects:

```json

[
  {
    "id": "<UUID>",
    "title": "<String>",
    "director": "<String>",
    "year": "<String>",
    "url": "<String>",
    "rate": 10,
    "duration": "<String>",
    "subscription_type_required": "<String>"
  }
]
```
### Delete a Film
- URL: `/films/{id}`
- Method: `DELETE`
- Path Variable: `id` - The ID of the film.
- Response:
- Status Code: `204 No Content`
## Films Watched Endpoint
### Create Films Watched
- URL: `/filmsW`
- Method: `POST`
- Request Body:

```json

{
  "customer_id": "<UUID>",
  "film_id": "<UUID>"
}
``` 
- Response:
- Status Code: `200 OK`
- Response Body:

```json

{
  "id": "<UUID>",
  "date": "<LocalDate>",
  "customer": {
    "id": "<UUID>",
    "name": "<String>",
    "email": "<String>",
    "phone": "<String>",
    "country": "<String>",
    "verified": false
  },
  "film": {
    "id": "<UUID>",
    "title": "<String>",
    "director": "<String>",
    "year": "<String>",
    "url": "<String>",
    "rate": 10,
    "duration": "<String>",
    "subscription_type_required": "<String>"
  }
}
```
### Get Films Watched by ID
- URL: `/filmsW/{id}`
- Method: `GET`
- Path Variable: `id` - The ID of the films watched entry.
- Response:
- Status Code: `200 OK`
- Response Body:

```json

{
  "id": "<UUID>",
  "date": "<LocalDate>",
  "customer": {
    "id": "<UUID>",
    "name": "<String>",
    "email": "<String>",
    "phone": "<String>",
    "country": "<String>",
    "verified": false
  },
  "film": {
    "id": "<UUID>",
    "title": "<String>",
    "director": "<String>",
    "year": "<String>",
    "url": "<String>",
    "rate": 10,
    "duration": "<String>",
    "subscription_type_required": "<String>"
  }
}
```
### Get All Films Watched
- URL: `/filmsW`
- Method: `GET`
- Response:
- Status Code: `200 OK`
- Response Body: Array of films watched objects:

```json

[
  {
    "id": "<UUID>",
    "date": "<LocalDate>",
    "customer": {
      "id": "<UUID>",
      "name": "<String>",
      "email": "<String>",
      "phone": "<String>",
      "country": "<String>",
      "verified": false
    },
    "film": {
      "id": "<UUID>",
      "title": "<String>",
      "director": "<String>",
      "year": "<String>",
      "url": "<String>",
      "rate": 10,
      "duration": "<String>",
      "subscription_type_required": "<String>"
    }
  }
]
```
### Delete Films Watched
- URL: `/filmsW/{id}`
- Method: `DELETE`
- Path Variable: `id` - The ID of the films watched entry.
- Response:
- Status Code: `204 No Content`
### Get Films Watched by Film ID
- URL: `/filmsW/byFilm/{id}`
- Method: `GET`
- Path Variable: `id` - The ID of the film.
- Response:
- Status Code: `200 OK`
- Response Body:

```json

{
  "film": {
    "id": "<UUID>",
    "title": "<String>",
    "director": "<String>",
    "year": "<String>",
    "url": "<String>",
    "rate": 10,
    "duration": "<String>",
    "subscription_type_required": "<String>"
  },
  "count": 100
}
```
### Get Films Watched by Customer ID
- URL: `/filmsW/byCustomer/{id}`
- Method: `GET`
- Path Variable: `id` - The ID of the customer.
- Response:
- Status Code: `200 OK`
- Response Body:

```json

{
  "customer": {
    "id": "<UUID>",
    "name": "<String>",
    "email": "<String>",
    "phone": "<String>",
    "country": "<String>",
    "verified": false
  },
  "films": [
    {
      "id": "<UUID>",
      "title": "<String>",
      "director": "<String>",
      "year": "<String>",
      "url": "<String>",
      "rate": 10,
      "duration": "<String>",
      "subscription_type_required": "<String>"
    }
  ]
}
```
## Series Endpoint
### Create a Series
- URL: `/series/new`
- Method: `POST`
- Request Body:

```json

{
  "title": "<String>",
  "director": "<String>",
  "year": "<String>",
  "url": "<String>",
  "rate": 10,
  "chapters": 10,
  "subscription_type_required": "<String>"
}
``` 
- Response:
- Status Code: `200 OK`
- Response Body:

```json

{
  "id": "<UUID>",
  "title": "<String>",
  "director": "<String>",
  "year": "<String>",
  "url": "<String>",
  "rate": 10,
  "chapters": 10,
  "subscription_type_required": "<String>"
}
```
### Get a Series by ID
- URL: `/series/{id}`
- Method: `GET`
- Path Variable: `id` - The ID of the series.
- Response:
- Status Code: `200 OK`
- Response Body:

```json

{
  "id": "<UUID>",
  "title": "<String>",
  "director": "<String>",
  "year": "<String>",
  "url": "<String>",
  "rate": 10,
  "chapters": 10,
  "subscription_type_required": "<String>"
}
```
### Get All Series
- URL: `/series`
- Method: `POST`
- Request Headers:
- `page` (optional, default: 0) - The page number for pagination.
- `size` (optional, default: 10) - The number of series per page.
- `sortType` (optional) - The type of sorting (NONE, ASC, DESC).
- Request Body (optional):

```json

{
  "type_name": "<String>",
  "minRate": 10,
  "maxRate": 10,
  "titleStart": "<String>",
  "directorStart": "<String>",
  "yearStart": "<String>",
  "yearEnd": "<String>"
}
``` 
- Response:
- Status Code: `200 OK`
- Response Body: Array of series objects:

```json

[
  {
    "id": "<UUID>",
    "title": "<String>",
    "director": "<String>",
    "year": "<String>",
    "url": "<String>",
    "rate": 10,
    "chapters": 10,
    "subscription_type_required": "<String>"
  }
]
```
### Delete a Series
- URL: `/series/{id}`
- Method: `DELETE`
- Path Variable: `id` - The ID of the series.
- Response:
- Status Code: `204 No Content`
## Series Watched Endpoint
### Create Series Watched
- URL: `/seriesW`
- Method: `POST`
- Request Body:

```json

{
  "customer_id": "<UUID>",
  "serie_id": "<UUID>"
}
``` 
- Response:
- Status Code: `200 OK`
- Response Body:

```json

{
  "id": "<UUID>",
  "date": "<LocalDate>",
  "customer": {
    "id": "<UUID>",
    "name": "<String>",
    "email": "<String>",
    "phone": "<String>",
    "country": "<String>",
    "verified": false
  },
  "serie": {
    "id": "<UUID>",
    "title": "<String>",
    "director": "<String>",
    "year": "<String>",
    "url": "<String>",
    "rate": 10,
    "chapters": 10,
    "subscription_type_required": "<String>"
  }
}
```
### Get Series Watched by ID
- URL: `/seriesW/{id}`
- Method: `GET`
- Path Variable: `id` - The ID of the series watched entry.
- Response:
- Status Code: `200 OK`
- Response Body:

```json

{
  "id": "<UUID>",
  "date": "<LocalDate>",
  "customer": {
    "id": "<UUID>",
    "name": "<String>",
    "email": "<String>",
    "phone": "<String>",
    "country": "<String>",
    "verified": false
  },
  "serie": {
    "id": "<UUID>",
    "title": "<String>",
    "director": "<String>",
    "year": "<String>",
    "url": "<String>",
    "rate": 10,
    "chapters": 10,
    "subscription_type_required": "<String>"
  }
}
```
### Get All Series Watched
- URL: `/seriesW`
- Method: `GET`
- Response:
- Status Code: `200 OK`
- Response Body: Array of series watched objects:

```json

[
  {
    "id": "<UUID>",
    "date": "<LocalDate>",
    "customer": {
      "id": "<UUID>",
      "name": "<String>",
      "email": "<String>",
      "phone": "<String>",
      "country": "<String>",
      "verified": false
    },
    "serie": {
      "id": "<UUID>",
      "title": "<String>",
      "director": "<String>",
      "year": "<String>",
      "url": "<String>",
      "rate": 10,
      "chapters": 10,
      "subscription_type_required": "<String>"
    }
  }
]
```
### Delete Series Watched
- URL: `/seriesW/{id}`
- Method: `DELETE`
- Path Variable: `id` - The ID of the series watched entry.
- Response:
- Status Code: `204 No Content`
### Get Series Watched by Series ID
- URL: `/seriesW/bySerie/{id}`
- Method: `GET`
- Path Variable: `id` - The ID of the series.
- Response:
- Status Code: `200 OK`
- Response Body:

```json

{
  "serie": {
    "id": "<UUID>",
    "title": "<String>",
    "director": "<String>",
    "year": "<String>",
    "url": "<String>",
    "rate": 10,
    "chapters": 10,
    "subscription_type_required": "<String>"
  },
  "count": 100
}
```
### Get Series Watched by Customer ID
- URL: `/seriesW/byCustomer/{id}`
- Method: `GET`
- Path Variable: `id` - The ID of the customer.
- Response:
- Status Code: `200 OK`
- Response Body:

```json

{
  "customer": {
    "id": "<UUID>",
    "name": "<String>",
    "email": "<String>",
    "phone": "<String>",
    "country": "<String>",
    "verified": false
  },
  "series": [
    {
      "id": "<UUID>",
      "title": "<String>",
      "director": "<String>",
      "year": "<String>",
      "url": "<String>",
      "rate": 10,
      "chapters": 10,
      "subscription_type_required": "<String>"
    }
  ]
}
```
## Subscriptions Endpoint
### Create a Subscription
- URL: `/sbs`
- Method: `POST`
- Request Body:

```json

{
  "customer_id": "<UUID>",
  "subscriptionsType": "<String>"
}
``` 
- Response:
- Status Code: `200 OK`
- Response Body:

```json

{
  "id": "<UUID>",
  "date_start": "<LocalDate>",
  "date_end": "<LocalDate>",
  "customer": {
    "id": "<UUID>",
    "name": "<String>",
    "email": "<String>",
    "phone": "<String>",
    "country": "<String>",
    "verified": false
  },
  "type": {
    "id": "<UUID>",
    "name": "<String>",
    "price": 10.100,
    "duration": "<String>"
  }
}
```
### Get All Subscriptions
- URL: `/sbs`
- Method: `GET`
- Response:
- Status Code: `200 OK`
- Response Body: Array of subscription objects:

```json

[
  {
    "id": "<UUID>",
    "date_start": "<LocalDate>",
    "date_end": "<LocalDate>",
    "customer": {
      "id": "<UUID>",
      "name": "<String>",
      "email": "<String>",
      "phone": "<String>",
      "country": "<String>",
      "verified": false
    },
    "type": {
      "id": "<UUID>",
      "name": "<String>",
      "price": 10.100,
      "duration": "<String>"
    }
  }
]
```
### Get a Subscription by ID
- URL: `/sbs/{id}`
- Method: `GET`
- Path Variable: `id` - The ID of the subscription.
- Response:
- Status Code: `200 OK`
- Response Body:

```json

{
  "id": "<UUID>",
  "date_start": "<LocalDate>",
  "date_end": "<LocalDate>",
  "customer": {
    "id": "<UUID>",
    "name": "<String>",
    "email": "<String>",
    "phone": "<String>",
    "country": "<String>",
    "verified": false
  },
  "type": {
    "id": "<UUID>",
    "name": "<String>",
    "price": 10.100,
    "duration": "<String>"
  }
}
```
### Delete a Subscription
- URL: `/sbs/{id}`
- Method: `DELETE`
- Path Variable: `id` - The ID of the subscription.
- Response:
- Status Code: `204 No Content`
### Get Subscriptions by Customer ID
- URL: `/sbs/customer/{id}`
- Method: `GET`
- Path Variable: `id` - The ID of the customer.
- Response:
- Status Code: `200 OK`
- Response Body: Array of subscription objects:

```json

[
  {
    "id": "<UUID>",
    "date_start": "<LocalDate>",
    "date_end": "<LocalDate>",
    "customer": {
      "id": "<UUID>",
      "name": "<String>",
      "email": "<String>",
      "phone": "<String>",
      "country": "<String>",
      "verified": false
    },
    "type": {
      "id": "<UUID>",
      "name": "<String>",
      "price": 10.100,
      "duration": "<String>"
    }
  }
]
```
### Delete Subscriptions by Customer ID
- URL: `/sbs/customer/{id}`
- Method: `DELETE`
- Path Variable: `id` - The ID of the customer.
- Response:
- Status Code: `204 No Content`
### Get Active Subscriptions by Customer ID
- URL: `/sbs/customer/active/{id}`
- Method: `GET`
- Path Variable: `id` - The ID of the customer.
- Response:
- Status Code: `200 OK`
- Response Body: Array of subscription objects:

```json

[
  {
    "id": "<UUID>",
    "date_start": "<LocalDate>",
    "date_end": "<LocalDate>",
    "customer": {
      "id": "<UUID>",
      "name": "<String>",
      "email": "<String>",
      "phone": "<String>",
      "country": "<String>",
      "verified": false
    },
    "type": {
      "id": "<UUID>",
      "name": "<String>",
      "price": 10.100,
      "duration": "<String>"
    }
  }
]
```
## Subscription Types Endpoint
### Create a Subscription Type
- URL: `/sbstypes`
- Method: `POST`
- Request Body:

```json

{
  "name": "<String>",
  "price": 10.100,
  "duration": "<String>"
}
``` 
- Response:
- Status Code: `200 OK`
- Response Body:

```json

{
  "id": "<UUID>",
  "name": "<String>",
  "price": 10.100,
  "duration": "<String>"
}
```
### Get All Subscription Types
- URL: `/sbstypes`
- Method: `GET`
- Response:
- Status Code: `200 OK`
- Response Body: Array of subscription type objects:

```json

[
  {
    "id": "<UUID>",
    "name": "<String>",
    "price": 10.100,
    "duration": "<String>"
  }
]
```
### Get a Subscription Type by ID
- URL: `/sbstypes/{id}`
- Method: `GET`
- Path Variable: `id` - The ID of the subscription type.
- Response:
- Status Code: `200 OK`
- Response Body:

```json

{
  "id": "<UUID>",
  "name": "<String>",
  "price": 10.100,
  "duration": "<String>"
}
```
### Delete a Subscription Type
- URL: `/sbstypes/{id}`
- Method: `DELETE`
- Path Variable: `id` - The ID of the subscription type.
- Response:
- Status Code: `204 No Content`
### Update a Subscription Type
- URL: `/sbstypes/{id}`
- Method: `PUT`
- Path Variable: `id` - The ID of the subscription type.
- Request Body:

```json

{
  "name": "<String>",
  "price": 10.100,
  "duration": "<String>"
}
``` 
- Response:
- Status Code: `200 OK`
- Response Body:

```json

{
  "id": "<UUID>",
  "name": "<String>",
  "price": 10.100,
  "duration": "<String>"
}
```

This documentation provides an overview of the available endpoints and their request/response formats in the NILFIS API. Please refer to the specific endpoints for more details on their usage and available operations.
