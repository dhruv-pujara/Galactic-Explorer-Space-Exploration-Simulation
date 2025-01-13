# Galactic Explorer: Space Exploration Simulation

## Overview
Galactic Explorer is a Java-based space exploration simulation game. The game features a dynamic galactic map where spaceships interact with each other and strive to achieve various objectives, such as delivering cargo, reporting threats, and completing exploration tasks. Players experience randomized gameplay and strategic challenges.

## Features
- **Dynamic Galactic Map:** Spaceships navigate a map with configurable settings loaded from a file.
- **Randomized Gameplay:** Each turn selects a random spaceship to perform actions, ensuring varied game dynamics.
- **Spaceship Interactions:** Includes movement, cargo transfers, reporting threats, and more.
- **Multiple Win Conditions:**
  - All cargoes delivered to destinations.
  - All explorers and cargo ships removed by fighters.
  - All fighters reported by explorers.
- **Configurable Settings:** Load map and spaceship configurations from an external `config.txt` file.

## How to Play
1. Clone the repository:
   ```bash
   git clone <repository-url>
   ```

2. Compile the code:
   ```bash
   javac Game.java GalacticMap.java Spaceship.java FileReader.java
   ```

3. Run the game:
   ```bash
   java Game
   ```

4. Follow the game progress in the console as spaceships move, interact, and achieve goals.

## Files in the Project
- **Game.java**: Contains the main game loop and manages win conditions.
- **GalacticMap.java**: Represents the galactic map and provides methods for managing spaceships and cargo.
- **Spaceship.java**: Defines the behavior and attributes of spaceships, including movement and interactions.
- **FileReader.java**: Handles reading the `config.txt` file to initialize the galactic map.
- **config.txt**: Provides the initial configuration for the galactic map, spaceships, and cargo.

## Technologies Used
- **Programming Language:** Java
- **Core Concepts:** Object-Oriented Programming, File I/O, Randomization

## Future Enhancements
- Add a graphical user interface for improved user interaction.
- Implement advanced AI for spaceship behaviors.
- Introduce additional spaceship types with unique abilities.

## Credits
- **Developer:** Dhruv Pujara
- **Assignment Supervisor:** Parisa Daeijavad

---

Feel free to suggest improvements or report issues by opening an issue in this repository!
