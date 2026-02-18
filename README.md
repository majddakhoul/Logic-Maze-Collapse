# Logic Maze Collapse

Logic Maze Collapse is a Java-based maze board game featuring multiple types of pieces, player mechanics, and advanced pathfinding algorithms. The project demonstrates a combination of object-oriented design, state management, and AI search algorithms such as DFS, BFS, UCS, and A*.

---

## Table of Contents

- [Features](#features)  
- [Game Pieces](#game-pieces)  
- [Player Mechanics](#player-mechanics)  
- [Pathfinding Algorithms](#pathfinding-algorithms)  
- [Project Structure](#project-structure)  
- [Setup & Requirements](#setup--requirements)  
- [How to Run](#how-to-run)  
- [Manual Play Instructions](#manual-play-instructions)  
- [License](#license)  

---

## Features

- Java-based 5x5 maze board game  
- Multiple piece types with distinct behaviors  
- Player can move in four directions (`W`, `A`, `S`, `D`)  
- Collect keys to unlock locked pieces  
- Track player position, keys, and score  
- Solve the board automatically using AI search algorithms  
- Print board state and path from start to goal  

---

## Game Pieces

- **StartPiece (`S`)**: Player's starting position  
- **FinalPiece (`F`)**: Goal tile to reach to win  
- **NormalPiece (`N`)**: Standard tile with a movement cost  
- **LockedPiece (`L`)**: Requires a specific key to enter  
- **UnlockerPiece (`K`)**: Grants a key to the player  
- **MultiPassPiece (`2`/`1`/`0`)**: Can be stepped on multiple times, updates symbol accordingly  

---

## Player Mechanics

- Tracks current position and inventory of keys  
- Can pick up keys from `UnlockerPiece`  
- Cannot enter `LockedPiece` without the corresponding key  
- Moves affect score based on piece cost  
- Winning is achieved by reaching the `FinalPiece`  

---

## Pathfinding Algorithms

- **Depth-First Search (DFS)**: Explores as deep as possible along each branch  
- **Breadth-First Search (BFS)**: Explores neighbors level by level  
- **Uniform Cost Search (UCS)**: Explores nodes with the lowest cumulative cost first  
- **A* Search**: Uses Manhattan distance heuristic plus current cost for optimal path  

---

## Project Structure

```
gameboard/
├── algorithms/
│   ├── AStar.java
│   ├── BFS.java
│   ├── DFS.java
│   └── UCS.java
├── boardcells/
│   ├── FinalPiece.java
│   ├── LockedPiece.java
│   ├── MultiPassPiece.java
│   ├── NormalPiece.java
│   ├── StartPiece.java
│   └── UnlockerPiece.java
├── matrixcoordinates/
│   └── Coordinate.java
├── player/
│   └── Player.java
└── state/
    └── Board.java
LogicMazeCollapse.java
```

- `LogicMazeCollapse.java`: Main class to initialize boards and run the AI or manual game mode  
- `gameboard.algorithms`: Search algorithms  
- `gameboard.boardcells`: Tile types with behaviors  
- `gameboard.matrixcoordinates`: Coordinate representation  
- `gameboard.player`: Player object  
- `gameboard.state`: Board object and movement logic  

---

## Setup & Requirements

- **Java Development Kit (JDK)** version 8 or higher  
- IDE such as **IntelliJ IDEA**, **Eclipse**, or **VS Code** with Java support  

---

## How to Run

1. Clone or download the project  
2. Open in your preferred IDE  
3. Compile all `.java` files  
4. Run the `LogicMazeCollapse` main class  
5. By default, the program runs the **A\*** search algorithm.  
6. To use DFS, BFS, or UCS, uncomment the respective method calls in `main`  

---

## Manual Play Instructions

1. Uncomment the "Manual play mode" section in `LogicMazeCollapse.java`  
2. Compile and run the program  
3. Use the keyboard keys:  
   - `W` = Up  
   - `A` = Left  
   - `S` = Down  
   - `D` = Right  
4. The board will display the current state:  
   - Example: `N*[1]` = NormalPiece with player on it and cost 1  
5. Reach the `FinalPiece (F)` to win the game  

---

## License

This project is licensed under the MIT License. See the LICENSE file for details.

---
