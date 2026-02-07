# VeinTreeBreaker

A Paper/Spigot plugin for Minecraft 1.21.x that allows players to break entire trees and ore veins by sneaking (Shift) and breaking a block.

## Features
- **Tree Feller:** Shift + Break a log to fell the entire tree.
- **Vein Miner:** Shift + Break an ore to mine the entire vein.
- **Effects:** Plays appropriate break sounds for each block.
- **Safety:** Recursion limit prevents server crashes on massive structures.

## Installation
1. Build the plugin using Gradle: `gradle build`
2. Copy the jar from `build/libs` to your server's `plugins` folder.
3. Restart the server.

## Usage
- Hold `Shift` (Sneak) and break a Log or Ore block.

## Configuration
Currently, the limit is hardcoded to 2000 blocks per break to ensure performance.
