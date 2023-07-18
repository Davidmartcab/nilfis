# Nilfis API EndPoints

## CustomersController

### POST /nilfis/customers

- Description: Creates a new customer.
- Request:

```perl

{
  "name": "John Doe",
  "email": "john.doe@example.com",
  "phone": "34657891284",
  "country": "United States"
}
``` 

- Response:

```json

{
  "id": "123e4567-e89b-12d3-a456-426655440000",
  "name": "John Doe",
  "email": "john.doe@example.com",
  "phone": "34657891284",
  "country": "United States",
  "verified": false
}
```

### GET /nilfis/customers/{id}

- Description: Retrieves customer details by ID.
- Request: No request body required.
- Response:

```json

{
  "id": "123e4567-e89b-12d3-a456-426655440000",
  "name": "John Doe",
  "email": "john.doe@example.com",
  "phone": "34657891284",
  "country": "United States",
  "verified": false
}
```

### GET /nilfis/customers/email/{email}

- Description: Retrieves customer details by email.
- Request: No request body required.
- Response:

```json

{
  "id": "123e4567-e89b-12d3-a456-426655440000",
  "name": "John Doe",
  "email": "john.doe@example.com",
  "phone": "34657891284",
  "country": "United States",
  "verified": false
}
```

### GET /nilfis/customers

- Description: Retrieves all customers.
- Request: No request body required.
- Response:

```json

[
  {
    "id": "123e4567-e89b-12d3-a456-426655440000",
    "name": "John Doe",
    "email": "john.doe@example.com",
    "phone": "34657891284",
    "country": "United States",
    "verified": false
  }
]
```

### DELETE /nilfis/customers/{id}

- Description: Deletes a customer by ID.
- Request: No request body required.
- Response: No data returned.

### GET /nilfis/customers/verify/{id}

- Description: Verifies a customer by ID.
- Request: No request body required.
- Response: No data returned.

## FilmsController

### POST /nilfis/films

- Description: Creates a new film.
- Request:

```json

{
  "title": "Film Title",
  "director": "John Director",
  "year": "2023",
  "url": "https://example.com/film",
  "rate": 4,
  "duration": "2h 30m",
  "subscription_type_required": "Premium"
}
``` 

- Response:

```json

{
  "id": "123e4567-e89b-12d3-a456-426655440001",
  "title": "Film Title",
  "director": "John Director",
  "year": "2023",
  "url": "https://example.com/film",
  "rate": 4,
  "duration": "2h 30m",
  "subscription_type_required": "Premium"
}
```

### GET /nilfis/films/{id}

- Description: Retrieves film details by ID.
- Request: No request body required.
- Response:

```json

{
  "id": "123e4567-e89b-12d3-a456-426655440001",
  "title": "Film Title",
  "director": "John Director",
  "year": "2023",
  "url": "https://example.com/film",
  "rate": 4,
  "duration": "2h 30m",
  "subscription_type_required": "Premium"
}
```

### GET /nilfis/films

- Description: Retrieves all films.
- Request: No request body required.
- Response:

```json

[
  {
    "id": "123e4567-e89b-12d3-a456-426655440001",
    "title": "Film Title",
    "director": "John Director",
    "year": "2023",
    "url": "https://example.com/film",
    "rate": 4,
    "duration": "2h 30m",
    "subscription_type_required": "Premium"
  }
]
```

### DELETE /nilfis/films/{id}

- Description: Deletes a film by ID.
- Request: No request body required.
- Response: No data returned.

### GET /nilfis/films/sbsType/{type}

- Description: Retrieves films by subscription type.
- Request: No request body required.
- Response:

```json

[
  {
    "id": "123e4567-e89b-12d3-a456-426655440001",
    "title": "Film Title",
    "director": "John Director",
    "year": "2023",
    "url": "https://example.com/film",
    "rate": 4,
    "duration": "2h 30m",
    "subscription_type_required": "Premium"
  }
]
```

### GET /nilfis/films/rateG/{rate}

- Description: Retrieves films with a rate greater than the specified value.
- Request: No request body required.
- Response:

```json

[
  {
    "id": "123e4567-e89b-12d3-a456-426655440001",
    "title": "Film Title",
    "director": "John Director",
    "year": "2023",
    "url": "https://example.com/film",
    "rate": 4,
    "duration": "2h 30m",
    "subscription_type_required": "Premium"
  }
]
```

### GET /nilfis/films/rateL/{rate}

- Description: Retrieves films with a rate lower than the specified value.
- Request: No request body required.
- Response:

```json

[
  {
    "id": "123e4567-e89b-12d3-a456-426655440001",
    "title": "Film Title",
    "director": "John Director",
    "year": "2023",
    "url": "https://example.com/film",
    "rate": 4,
    "duration": "2h 30m",
    "subscription_type_required": "Premium"
  }
]
```

### GET /nilfis/films/rateB/{minRate}/{maxRate}

- Description: Retrieves films with a rate between the specified values.
- Request: No request body required.
- Response:

```json

[
  {
    "id": "123e4567-e89b-12d3-a456-426655440001",
    "title": "Film Title",
    "director": "John Director",
    "year": "2023",
    "url": "https://example.com/film",
    "rate": 4,
    "duration": "2h 30m",
    "subscription_type_required": "Premium"
  }
]
```

### GET /nilfis/films/title/{title}

- Description: Retrieves films with titles starting with the specified value.
- Request: No request body required.
- Response:

```json

[
  {
    "id": "123e4567-e89b-12d3-a456-426655440001",
    "title": "Film Title",
    "director": "John Director",
    "year": "2023",
    "url": "https://example.com/film",
    "rate": 4,
    "duration": "2h 30m",
    "subscription_type_required": "Premium"
  }
]
```

### GET /nilfis/films/director/{director}

- Description: Retrieves films with directors' names starting with the specified value.
- Request: No request body required.
- Response:

```json

[
  {
    "id": "123e4567-e89b-12d3-a456-426655440001",
    "title": "Film Title",
    "director": "John Director",
    "year": "2023",
    "url": "https://example.com/film",
    "rate": 4,
    "duration": "2h 30m",
    "subscription_type_required": "Premium"
  }
]
```

### GET /nilfis/films/year/{startYear}/{endYear}

- Description: Retrieves films released between the specified years.
- Request: No request body required.
- Response:

```json

[
  {
    "id": "123e4567-e89b-12d3-a456-426655440001",
    "title": "Film Title",
    "director": "John Director",
    "year": "2023",
    "url": "https://example.com/film",
    "rate": 4,
    "duration": "2h 30m",
    "subscription_type_required": "Premium"
  }
]
```

## SeriesController

(Same as FilmsController but with series data)

## FilmsWatchedController

### POST /nilfis/filmsW

- Description: Records that a film has been watched by a customer.
- Request:

```json

{
  "customer_id": "123e4567-e89b-12d3-a456-426655440000",
  "film_id": "123e4567-e89b-12d3-a456-426655440001"
}
``` 

- Response:

```json

{
  "id": "123e4567-e89b-12d3-a456-426655440002",
  "date": "2023-07-18",
  "customer": {
    "id": "123e4567-e89b-12d3-a456-426655440000",
    "name": "John Doe",
    "email": "john.doe@example.com",
    "phone": "34657891284",
    "country": "United States",
    "verified": false
  },
  "film": {
    "id": "123e4567-e89b-12d3-a456-426655440001",
    "title": "Film Title",
    "director": "John Director",
    "year": "2023",
    "url": "https://example.com/film",
    "rate": 4,
    "duration": "2h 30m",
    "subscription_type_required": "Premium"
  }
}
```

### GET /nilfis/filmsW/{id}

- Description: Retrieves details of a watched film by ID.
- Request: No request body required.
- Response:

```json

{
  "id": "123e4567-e89b-12d3-a456-426655440002",
  "date": "2023-07-18",
  "customer": {
    "id": "123e4567-e89b-12d3-a456-426655440000",
    "name": "John Doe",
    "email": "john.doe@example.com",
    "phone": "34657891284",
    "country": "United States",
    "verified": false
  },
  "film": {
    "id": "123e4567-e89b-12d3-a456-426655440001",
    "title": "Film Title",
    "director": "John Director",
    "year": "2023",
    "url": "https://example.com/film",
    "rate": 4,
    "duration": "2h 30m",
    "subscription_type_required": "Premium"
  }
}
```

### GET /nilfis/filmsW

- Description: Retrieves all watched films.
- Request: No request body required.
- Response:

```json

[
  {
    "id": "123e4567-e89b-12d3-a456-426655440002",
    "date": "2023-07-18",
    "customer": {
      "id": "123e4567-e89b-12d3-a456-426655440000",
      "name": "John Doe",
      "email": "john.doe@example.com",
      "phone": "34657891284",
      "country": "United States",
      "verified": false
    },
    "film": {
      "id": "123e4567-e89b-12d3-a456-426655440001",
      "title": "Film Title",
      "director": "John Director",
      "year": "2023",
      "url": "https://example.com/film",
      "rate": 4,
      "duration": "2h 30m",
      "subscription_type_required": "Premium"
    }
  }
]
```

### DELETE /nilfis/filmsW/{id}

- Description: Deletes a watched film record by ID.
- Request: No request body required.
- Response: No data returned.

### GET /nilfis/filmsW/byFilm/{id}

- Description: Retrieves the number of times a film has been watched by its ID.
- Request: No request body required.
- Response:

```json

{
  "film": {
    "id": "123e4567-e89b-12d3-a456-426655440001",
    "title": "Film Title",
    "director": "John Director",
    "year": "2023",
    "url": "https://example.com/film",
    "rate": 4,
    "duration": "2h 30m",
    "subscription_type_required": "Premium"
  },
  "count": 5
}
```

### GET /nilfis/filmsW/byCustomer/{id}

- Description: Retrieves films watched by a customer by customer ID.
- Request: No request body required.
- Response:

```json

{
  "customer": {
    "id": "123e4567-e89b-12d3-a456-426655440000",
    "name": "John Doe",
    "email": "john.doe@example.com",
    "phone": "34657891284",
    "country": "United States",
    "verified": false
  },
  "films": [
    {
      "id": "123e4567-e89b-12d3-a456-426655440001",
      "title": "Film Title",
      "director": "John Director",
      "year": "2023",
      "url": "https://example.com/film",
      "rate": 4,
      "duration": "2h 30m",
      "subscription_type_required": "Premium"
    }
  ]
}
```

## SeriesWatchedController

(Same as FilmsWatchedController but with series data)

## SubscriptionsController

### POST /nilfis/sbs

- Description: Creates a new subscription.
- Request:

```json

{
  "customer_id": "123e4567-e89b-12d3-a456-426655440000",
  "subscriptionsType": "Premium"
}
``` 

- Response:

```json

{
  "id": "123e4567-e89b-12d3-a456-426655440003",
  "date_start": "2023-07-18",
  "date_end": "2023-08-18",
  "customer": {
    "id": "123e4567-e89b-12d3-a456-426655440000",
    "name": "John Doe",
    "email": "john.doe@example.com",
    "phone": "34657891284",
    "country": "United States",
    "verified": false
  },
  "type": {
    "id": "123e4567-e89b-12d3-a456-426655440004",
    "name": "Premium",
    "price": 9.99,
    "duration": "1 Month"
  }
}
```

### GET /nilfis/sbs

- Description: Retrieves all subscriptions.
- Request: No request body required.
- Response:

```json

[
  {
    "id": "123e4567-e89b-12d3-a456-426655440003",
    "date_start": "2023-07-18",
    "date_end": "2023-08-18",
    "customer": {
      "id": "123e4567-e89b-12d3-a456-426655440000",
      "name": "John Doe",
      "email": "john.doe@example.com",
      "phone": "34657891284",
      "country": "United States",
      "verified": false
    },
    "type": {
      "id": "123e4567-e89b-12d3-a456-426655440004",
      "name": "Premium",
      "price": 9.99,
      "duration": "1 Month"
    }
  }
]
```

### GET /nilfis/sbs/{id}

- Description: Retrieves subscription details by ID.
- Request: No request body required.
- Response:

```json

{
  "id": "123e4567-e89b-12d3-a456-426655440003",
  "date_start": "2023-07-18",
  "date_end": "2023-08-18",
  "customer": {
    "id": "123e4567-e89b-12d3-a456-426655440000",
    "name": "John Doe",
    "email": "john.doe@example.com",
    "phone": "34657891284",
    "country": "United States",
    "verified": false
  },
  "type": {
    "id": "123e4567-e89b-12d3-a456-426655440004",
    "name": "Premium",
    "price": 9.99,
    "duration": "1 Month"
  }
}
```

### DELETE /nilfis/sbs/{id}

- Description: Deletes a subscription by ID.
- Request: No request body required.
- Response: No data returned.

### GET /nilfis/sbs/customer/{id}

- Description: Retrieves all subscriptions of a customer by customer ID.
- Request: No request body required.
- Response:

```json

[
  {
    "id": "123e4567-e89b-12d3-a456-426655440003",
    "date_start": "2023-07-18",
    "date_end": "2023-08-18",
    "customer": {
      "id": "123e4567-e89b-12d3-a456-426655440000",
      "name": "John Doe",
      "email": "john.doe@example.com",
      "phone": "34657891284",
      "country": "United States",
      "verified": false
    },
    "type": {
      "id": "123e4567-e89b-12d3-a456-426655440004",
      "name": "Premium",
      "price": 9.99,
      "duration": "1 Month"
    }
  }
]
```

### DELETE /nilfis/sbs/customer/{id}

- Description: Deletes all subscriptions of a customer by customer ID.
- Request: No request body required.
- Response: No data returned.

### GET /nilfis/sbs/customer/active/{id}

- Description: Retrieves active subscriptions of a customer by customer ID.
- Request: No request body required.
- Response:

```json

[
  {
    "id": "123e4567-e89b-12d3-a456-426655440003",
    "date_start": "2023-07-18",
    "date_end": "2023-08-18",
    "customer": {
      "id": "123e4567-e89b-12d3-a456-426655440000",
      "name": "John Doe",
      "email": "john.doe@example.com",
      "phone": "34657891284",
      "country": "United States",
      "verified": false
    },
    "type": {
      "id": "123e4567-e89b-12d3-a456-426655440004",
      "name": "Premium",
      "price": 9.99,
      "duration": "1 Month"
    }
  }
]
```

### DELETE /nilfis/sbs/customer/active/{id}

- Description: Deletes all expired subscriptions of a customer by customer ID.
- Request: No request body required.
- Response: No data returned.

### DELETE /nilfis/sbs/active

- Description: Deletes all expired subscriptions.
- Request: No request body required.
- Response: No data returned.

## SubscriptionTypesController

### POST /nilfis/sbsTypes

- Description: Creates a new subscription type.
- Request:

```json

{
  "name": "Premium",
  "price": 9.99,
  "duration": "1 Month"
}
``` 

- Response:

```json

{
  "id": "123e4567-e89b-12d3-a456-426655440004",
  "name": "Premium",
  "price": 9.99,
  "duration": "1 Month"
}
```

### GET /nilfis/sbsTypes

- Description: Retrieves all subscription types.
- Request: No request body required.
- Response:

```json

[
  {
    "id": "123e4567-e89b-12d3-a456-426655440004",
    "name": "Premium",
    "price": 9.99,
    "duration": "1 Month"
  }
]
```

### GET /nilfis/sbsTypes/{id}

- Description: Retrieves subscription type details by ID.
- Request: No request body required.
- Response:

```json

{
  "id": "123e4567-e89b-12d3-a456-426655440004",
  "name": "Premium",
  "price": 9.99,
  "duration": "1 Month"
}
```

### DELETE /nilfis/sbsTypes/{id}

- Description: Deletes a subscription type by ID.
- Request: No request body required.
- Response: No data returned.

I hope this information helps you understand the endpoints and their functionalities.
If you have any further questions or need additional clarification, feel free to ask!