# Java backend for movie api application
## Overview

This project is a Java-based backend for a movie API application. It provides various endpoints to manage and retrieve movie data.

## Features

- Retrieve a list of movies
- Get details of a specific movie
- Add a new movie
- Update existing movie information
- Delete a movie

## Technologies Used

- Java
- Spring Boot
- MongoDB

## Getting Started

### Prerequisites

- Java 11 or higher
- Maven
- MongoDB

### Installation

1. Clone the repository:
	```sh
	git clone git@github.com:valeriiazvezdina/movies-app-backend.git
	```
2. Navigate to the project directory:
	```sh
	cd movies-api/backend
	```
3. Update the `application.yaml` file with your MongoDB credentials according to .env-example.

4. Build the project

5. Run the application

## API Endpoints

- `GET /api/movies` - Retrieve a list of movies
- `GET /api/movies/{id}` - Get details of a specific movie
- `POST /api/reviews` - Create a new review
- `GET /api/users/me` - Get authentication details of current user
- `POST /api/users/register` - Sign in
- `POST /api/users/login` - Log in
- `POST /api/users/logout` - Log out

## Contributing

Contributions are welcome! Please fork the repository and create a pull request.
