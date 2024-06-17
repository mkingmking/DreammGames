# Backend Engineering Case Study

This project is a backend system for managing a mobile game's tournament feature and user progression. It provides REST API endpoints for creating users, managing user progress, participating in tournaments, and retrieving leaderboard data. The system is developed using Spring Boot with Java and uses MySQL as the main persistent storage.

Features
User Progress: New users start with 5,000 coins and progress through levels. User data, including level, coins, and country, is stored in a MySQL database.
World Cup Tournament: Users can participate in daily tournaments, representing their countries and earning rewards based on their performance.
Leaderboards: Real-time leaderboards are available for tournament groups and countries, showcasing user scores and contributions.
Project Structure
src/main/java: Contains Java source code for backend services, including controllers, services, repositories, and entity classes.
src/main/resources: Contains configuration files, including application properties and database scripts.
src/test: Contains unit tests for various components of the backend system.

API Endpoints
/api/users: Create and update user progress.
/api/tournaments: Participate in tournaments and claim rewards
/api/leaderboards: Retrieve leaderboard data for tournament groups and countries
