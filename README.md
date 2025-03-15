
# Tracking Service API

## Description
This project is a Spring Boot-based tracking service API designed for generating tracking numbers for AirAsia packages. The API allows users to generate tracking numbers by providing information such as the origin and destination country, weight, and creation date of the package.

## Installation

1. Clone the repository to your local machine:
   ```bash
   git clone https://github.com/yourusername/tracking-service.git
   ```

2. Navigate to the project directory:
   ```bash
   cd tracking-service
   ```

3. Install dependencies using Maven:
   ```bash
   mvn install
   ```

4. Run the application:
   ```bash
   mvn spring-boot:run
   ```

## Configuration

- The service uses Spring Boot and requires Java 17.
- The application is set up to use MongoDB as the database. Make sure to have a MongoDB instance running or configure it with your own connection settings in the `application.properties` file.

## API Endpoints

### `GET /next-tracking-number`
Generates a new tracking number based on the provided details.

**Request Parameters:**
- `originCountryId` (String): The country code of the origin country.
- `destinationCountryId` (String): The country code of the destination country.
- `weight` (String): The weight of the package.
- `createdAt` (String): The date and time when the request was created (in ISO 8601 format).
- `customerId` (String): The ID of the customer.
- `customerName` (String): The name of the customer.
- `customerSlug` (String): A slug identifier for the customer.

**Response:**
- `trackingNumber` (String): The generated tracking number.
- `createdAt` (String): The date and time when the tracking number was created.

Example Request:
```bash
GET /next-tracking-number?originCountryId=IN&destinationCountryId=MY&weight=5&createdAt=2025-03-14T12:30:00+00:00&customerId=12345&customerName=JohnDoe&customerSlug=johndoe
```

Example Response:
```json
{
  "trackingNumber": "AA123456789",
  "createdAt": "2025-03-14T12:30:00+00:00"
}
```

## Dependencies

- Spring Boot 3.x
- Spring Boot Data MongoDB
- Lombok (for reducing boilerplate code)
- Spring Boot DevTools (for development ease)
- Spring Boot Starter Test (for testing)
- Spring Boot Starter Web (for REST APIs)

