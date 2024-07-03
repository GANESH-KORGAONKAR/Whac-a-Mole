# Whac-A-Mole Game

Welcome to the Whac-A-Mole game! This is a simple yet exciting game where you have to click on moles appearing randomly on a 3x3 grid to score points. Be careful not to click on the plants, or it's game over!

## Table of Contents
- [Game Description](#game-description)
- [Features](#features)
- [Game Instructions](#game-instructions)
- [Code Explanation](#code-explanation)

## Game Description

Whac-A-Mole is a fun and interactive game where the objective is to click on the moles that randomly appear on the game board. Each successful click on a mole scores you 10 points. However, if you click on a plant, the game ends. The game also keeps track of your highest score and gives you an option to restart the game once it ends.

## Features

- Randomly appearing moles and plants on a 3x3 grid.
- Score tracking system.
- High score tracking.
- Game over detection when a plant is clicked.
- Option to restart the game after it ends.

## Game Instructions

1. Click on the moles that appear on the grid to score points.
2. Avoid clicking on the plants.
3. Each mole click scores 10 points.
4. Clicking on a plant ends the game.
5. The game displays your score and the highest score achieved.
6. After the game ends, you have the option to restart the game.

## Code Explanation

### `App.java`
This file contains the main method which creates an instance of the `WhacAMole` class to start the game.

```java
public class App {
    public static void main(String[] args) throws Exception {
        WhacAMole whacAMole = new WhacAMole();
    }
}
