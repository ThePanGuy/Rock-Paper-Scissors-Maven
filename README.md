# Rock-Paper-Scissors Game

This project implements a command-line Rock-Paper-Scissors game simulation in Java 17, built using Maven. It
demonstrates object-oriented design principles, including the Strategy pattern, and incorporates SLF4J with Logback for
structured logging.

## Table of Contents

* [Project Overview](#project-overview)
* [Features](#features)
* [Design Overview](#design-overview)
* [Strategies Implemented](#strategies-implemented)
* [Prerequisites](#prerequisites)
* [How to Build](#how-to-build)
* [How to Run](#how-to-run)
* [How to Run Tests](#how-to-run-tests)
* [Logging Configuration](#logging-configuration)
* [Advantages/Disadvantages](#advantagesdisadvantages)

## Project Overview

The core task is to simulate a Rock-Paper-Scissors game between two players over a specified number of rounds and report
the win/loss/tie statistics. One player is to only use Paper and the other one randomly pick one of the three options.
The project has evolved to incorporate a flexible design for player strategies and robust logging.

## Features

* **Game Simulation**: Simulates multiple rounds of Rock-Paper-Scissors between two configurable players.
* **Pluggable Strategies:** Players utilize different strategies to choose their moves, easily swappable at runtime.
* **Clear Game Rules:** Core game logic (determining round winners) is encapsulated in a dedicated utility class.
* **Logging:** Uses SLF4J as a facade with Logback as the underlying implementation for structured output.
* **Unit Tests:** Comprehensive JUnit 5 tests cover individual components and game logic.
* **Maven Build:** Standard Maven project structure for easy building and dependency management.

## Design Overview

The project adheres to several object-oriented design principles:

* **Strategy Pattern:** The `MoveStrategy` interface defines how a player chooses a move. Concrete strategy classes (
  e.g., `FixedMoveStrategy`, `RandomMoveStrategy`) implement this interface, allowing player behavior to be swapped
  dynamically without changing the `Player` class itself.
* **Separation of Concerns:**
    * `Move`: Simple enum representing the game moves.
    * `GameResult`: Enum representing round outcomes.
    * `RockPaperScissorsRules`: A static utility class solely responsible for determining round outcomes based on moves
      and providing helper methods for moves (e.g., `getWinningMove`). It contains the core, stateless game rules.
    * `MoveStrategy`: Interface for player behavior.
    * `Player`: Represents a participant in the game. It *composes* a `MoveStrategy` to decide its moves, and it
      delegates behavior updating to its strategy. It manages its identity (`name`).
    * `RockPaperScissorsGame`: The main application class (dev.pantelis.rps.app) that orchestrates the entire
      simulation, including the game loop, round-by-round logic, and overall scorekeeping.
* **Logging:** Utilizes the SLF4J API to abstract away the logging implementation, which is
  Logback. This allows easy switching of logging backends in the future.

## Strategies Implemented

The `dev.pantelis.rps.domain.strategy` package contains the following `MoveStrategy` implementations:

* `FixedMoveStrategy`: A stateless strategy that always chooses a specific `Move` (Rock, Paper, or Scissors) provided
  during its construction.
* `RandomMoveStrategy`: A stateless strategy that chooses a move randomly in each round.
* `CopycatStrategy`: A stateful strategy that observes the opponent's last move and plays that same move in the current
  round. For the first round, it chooses randomly.
* `CounterMoveStrategy`: A stateful strategy that observes the opponent's last move and plays the move that beats
  it in the current round. For the first round, it chooses randomly.

## Prerequisites

* Java Development Kit (JDK) 17 or higher
* Apache Maven 3.6.0 or higher

## How to Build

1. **Navigate** to the project root directory.
2. **Open your terminal or command prompt** in the project root directory (where `pom.xml` is located).
3. **Run the Maven clean install command:**
   ```bash
   mvn clean install
   ```
   This command will compile the Java code, run all unit tests, and package the application into a **single executable
   JAR file** in the `target/` directory.

## How to Run

After successfully building the project, you can run the game simulation using the generated executable JAR file:

1. **Run the executable JAR file:**
   ```bash
   java -jar ./target/Rock-Paper-Scissors-Maven-1.0-SNAPSHOT-exec.jar
   ```
   The game results, showing wins and ties for the configured players over 100 rounds, will be printed to the console
   via the Logback logger.

You can modify the `main` method in `src/main/java/com/example/rps/RockPaperScissorsGame.java` to change which
strategies play against each other.

## How to Run Tests

To execute the unit tests defined in `src/test/java/`, run the following Maven command from the project root directory:

```bash
mvn test
```

This will compile and run all JUnit 5 tests, reporting their outcomes.

## Logging Configuration

The project uses SLF4J with Logback. The logging configuration is managed by the `logback.xml` file located in
`src/main/resources/`.

Currently, logs are output to the console with a basic pattern and at `INFO` level.

## Advantages/Disadvantages

**Advantages**

1. Flexible and Extensible Player Behaviors (Strategy Pattern): By using the MoveStrategy interface, the system allows
   for highly flexible and extensible player behaviors. New strategies can be added without modifying existing Player or
   RockPaperScissorsGame code.
2. Clear Separation of Core Game Rules: The RockPaperScissorsRules class serves as a clean, stateless utility class. All
   fundamental rules of Rock-Paper-Scissors (like determining round winners or finding a winning/losing move) are
   encapsulated here. This centralizes core logic, making it highly testable and ensuring consistency across all
   strategy implementations.
3. Modular and Testable Components: Individual components like Move, GameResult, MoveStrategy implementations, and
   RockPaperScissorsRules are all self-contained and highly unit-testable. This modularity contributes to code quality
   and maintainability.

**Disadvantages**

1. Orchestration within main Class for Scalability: While effective for this command-line application,
   having the entire game orchestration (round loop, score accumulation, strategy state updates) directly within a
   static play method of the RockPaperScissorsGame class could limit future scalability.
2. Explicit Strategy Testing: For more intricate testing of strategies, introducing a mocking framework like
   Mockito could simplify stubbing and verifying calls to updateStrategyState on mock strategy objects.

