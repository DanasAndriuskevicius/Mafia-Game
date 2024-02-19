# Mafia-Game
This project is a Java Spring Boot implementation of the popular Mafia party game.

## Introduction
The Mafia game is a social deduction game, where players are assigned roles and must work together to uncover the Mafia members among them, while the Mafia tries to eliminate the other players. This project aims to provide a digital platform for playing the Mafia game.

## Features
- CRUD functions for player managing
- Role assignment (Mafia, Townsfolk, etc.)

## Prerequisites
Before running the project, make sure you have the following installed:

- Java Development Kit (JDK) 8 or higher
- Maven
- MySQL database server

## Installation
1. Clone the project repository:
git clone <repository_url>

2. Navigate to the project directory:
cd mafia-game

3. Create a MySQL database schema for the project:
CREATE DATABASE mafia_game_db;

4. Configure the database connection in the application.properties file located in the src/main/resources directory. Update the following properties with your MySQL database credentials:
spring.datasource.url=jdbc:mysql://localhost:3306/mafia_game_db
spring.datasource.username=<your_mysql_username>
spring.datasource.password=<your_mysql_password>

5. Run the project:
mvn spring-boot:run

6. Clone and run fron end project from:
https://github.com/DanasAndriuskevicius/MafiaGameFrontEnd/tree/master
