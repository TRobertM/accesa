
# Accesa Demo 



## Project Structure

This is a 3-layer architecture demo built with Java and Spring Framework, using Gradle  and an in-memory database (H2).

```
com.demo/
├── configuration/       # Security and application configuration
├── controller/          # REST API endpoints
├── dto/                 # Data Transfer Objects
├── exception/           # Custom exceptions and global handler
├── mapper/              # Entity to DTO mapping
├── model/               # JPA entities
├── repository/          # Data access layer
├── security/            # JWT authentication and security utilities
├── service/             # Business logic layer
├── util/                # Utility classes (CSV parser for demo data)
└── validator/           # Input validation
```

Data is automatically added to the database on application startup
``` 
resources/
├── discounts/       # Discount data
├── products/        # Product price data
```
## Building and running the application

```bash
    git clone https://github.com/TRobertM/accesa.git
    cd accesa
    gradlew bootRun
```
This will start the application on port 8080, to change this you can edit **server.port** in application.properties

localhost:8080 will be the base url for api endpoints 

localhost:8080/h2-console provides a gui for the database
- JDBC URL: jdbc:h2:mem:testdb
- Username: sa
- Password: (leave empty)

## Assumptions and Simplifications

### Simplifications

- The application uses an in-memory database for simplicity
- Environmental variables are hardcoded and not hidden for easier setup
- No JWT refreshing
- Demo data is added on application startup
- Dates inside the code are hardcoded to specific days to ensure data exists for all API requests made
- The notification for price alerts is a simple console print

### Assumptions

- CSV parsing is all done in a single file without any regard for clean code or principles, based on the assumption that csv parsing wasn't the main focus of the challenge


## API Reference

### Authentication API

#### Register

This endpoint expects a JSON request body:

```
  POST /register
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `username` | `string` | **Required**. Your username |
| `password` | `string` | **Required**. Your password |

#### Login

```
  POST /login
```
This endpoint expects a JSON request body:

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `username`      | `string` | **Required**. Your username |
| `password` | `string` | **Required**. Your password |

This will return a JWT that needs to be sent with every request besides the ones towards /register and /login

### Basket API

#### Fetch basket

```
  GET /api/basket
```

Returns the basket of the currently logged in user

#### Add to basket

```
  POST /api/basket/add
```
This endpoint expects a JSON request body:

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `productId`| `string` | **Required**. ID of the product |
| `quantity` | `Integer` | **Required**. Quantity of said product |

Returns the basket of the currently logged in user

#### Optimize basket

```
  POST /api/basket/optimize
```
Optimizes the basket, creating shopping lists for cost efficiency

### Discount API

#### Fetch best discounts 

```
  GET /api/discounts/best
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `page`      | `int` | **Optional**. Page number you want to access, defaults to 0 |
| `size` | `int` | **Optional**. Number of elements on the page, defaults to 10 |

Returns all existing discounts, from best to worst, together with pagination
```
Example request: http://localhost:8080/api/discounts/best?page=0&size=3
```

#### Fetch new discounts 

```
  GET /api/discounts/new
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `page`      | `int` | **Optional**. Page number you want to access, defaults to 0 |
| `size` | `int` | **Optional**. Number of elements on the page, defaults to 10 |

Returns all existing discounts created on the day of the request or the previous day, together with pagination
```
Example request: http://localhost:8080/api/discounts/new?page=0&size=3
```

### Price Alert API

#### Create price alert

```
  POST /api/alerts/create
```
This endpoint expects a JSON request body:

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `productId`      | `String` | **Required**. Product ID of the monitored product |
| `wantedPrice` | `Double` | **Required**. Price required for the alert to trigger |

Creates a price alert for the currently logged in user

#### Get price alerts

```
  GET /api/alerts
```

Returns all the price alerts for the currently logged in user

### Product API

#### Get specific product

```
  GET /api/products/{id}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `productId`      | `String` | **Required**. Product ID of the wanted product |

Returns information of a specific product

### Prices API

#### Get price history

```
  GET /api/prices/history
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `daysBetweenPoints`      | `Integer` | **Optional**. Specifies the days between data points, defaults to 7  |
| `productId`      | `String` | **Optional**. Only selects the product with this specific ID |
| `storeId`      | `String` | **Optional**. Filters the products based on their store |
| `category`      | `String` | **Optional**. Filters the products based on their category |
| `brand`      | `String` | **Optional**. Filters the products based on their brand |
| `startDate`      | `LocalDate` | **Required**. Start date from which the data points will begin |
| `endDate`      | `LocalDate` | **Optional**. End date at which data points will stop, defaults to the current day of the request |

Returns price information about a list of products based on applied filters

```
Example request: http://localhost:8080/api/prices/history?productId=P001&startDate=2025-05-01&endDate=2025-05-16
```

### Shopping Lists API

#### Get shopping lists

```
  GET /api/shoppinglists
```

Returns the shopping lists of the currently logged in user












