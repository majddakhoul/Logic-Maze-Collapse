# Logic-Maze-Collapse
A Java-based maze board game with multiple piece types and advanced pathfinding algorithms.

## Features

- **Board Pieces**:  
  - `StartPiece` – Player's starting position  
  - `FinalPiece` – Goal to reach  
  - `NormalPiece` – Standard tile with a movement cost  
  - `LockedPiece` – Requires a key to pass  
  - `UnlockerPiece` – Grants a key to the player  
  - `MultiPassPiece` – Can be stepped on multiple times  

- **Player Mechanics**:  
  - Move in four directions (`W`, `A`, `S`, `D`)  
  - Collect keys to unlock locked pieces  
  - Track player's position and inventory  

- **Pathfinding Algorithms**:  
  - Depth-First Search (DFS)  
  - Breadth-First Search (BFS)  
  - Uniform Cost Search (UCS)  
  - A* Search with Manhattan distance heuristic 
