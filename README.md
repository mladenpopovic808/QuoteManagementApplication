# Quote Management Application

This project is a two-service web application that allows users to save and view quotes, as well as see a "Quote of the Day." The system is divided into two services that communicate via HTTP using Java Sockets.

## Features

- **Add Quote**: Users can add a new quote and its author through a form.
- **View Quotes**: All saved quotes are displayed on the same page as the form.
- **Quote of the Day**: A random quote is fetched from an internal service and displayed on the form page as daily inspiration.

## System Architecture

The system consists of two web services:

1. **Main Service**: 
   - Handles user interactions (adding and displaying quotes).
   - Saves quotes to the system.
   - Fetches the "Quote of the Day" from the auxiliary service.

2. **Auxiliary Service**: 
   - Provides a random "Quote of the Day" to the main service.
   - This service is not directly accessible by the client.

### Communication Between Services

- The main service communicates with the auxiliary service via HTTP, using Java Sockets (no third-party HTTP libraries allowed).
- JSON is used for formatting the response from the auxiliary service.

## Endpoints

### Main Service

- `GET /quotes`: Returns an HTML page with the form for adding a quote, displays the "Quote of the Day," and shows all saved quotes.
- `POST /save-quote`: Saves a new quote submitted by the user and redirects back to `/quotes`.

### Auxiliary Service

- `GET /quote-of-the-day`: Returns a randomly selected "Quote of the Day" in JSON format.

## How to Run

### Main Service

1. Compile and run the Main Service on `localhost` with the specified port (e.g., 8080).
2. Access the application via `http://localhost:8080/quotes`.

### Auxiliary Service

1. Compile and run the Auxiliary Service on a different port (e.g., 8081).
2. This service runs internally and provides a quote to the main service when requested.


