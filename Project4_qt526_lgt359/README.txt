README.txt
Programming Assignment 4: CritterPart1
---------------------------------------------------------------------------------------------
New classes Implemented(4):

- Critter1.java
NAME: The Straight-Edge
ATTRIBUTES: This critter only fights when it has energy > 50
MOVEMENT: Only moves horizontally/vertically (random)

- Critter2.java
NAME: The Diagonal
ATTRIBUTES: This critter only fights when it has energy > 50
MOVEMENTS: Only moves diagonally (random)

- Critter3.java
NAME: The Gardening Tool
ATTRIBUTES: This critter always wants to reproduce and never wants to fight(unless Algae)
MOVEMENTS: Always tries to reproduce if possible, if not, walks(randomly)
OFFSPRING POSITION: Offspring always spawns below parent

- Critter4.java
NAME: The Drunk Rock
ATTRIBUTES: This critter always wants to fight
MOVEMENT: Always runs and charges randomly

---------------------------------------------------------------------------------------------
Methods/Flags added:

- Method #1: runAway()
Critter always attempts to run away towards the right (direction = 0, steps = 2)
Must check if critter's run away position is occupied by another critter.
If occupied, critter can not run away.
If not occupied, updates critter's position

- Method #2: move (int direction, int steps)
Helper function for walk/run that actually does the moving
@param direction 0-7, representing where to move critter
@param steps integer that represents how many steps critter takes

- Method #3: printTopOrBottom
Helper function for displayWorld to print top/bottom border of frame

- Method #4: printMiddle
Helper function for displayWorld to print middle section of frame

- Flag #1: hasMoved
Boolean flag that indicates whether a critter has moved or not

- Flag #2: tryingToRun
Boolean flag that indicates whether a critter is attempting to run away during an encounter

---------------------------------------------------------------------------------------------
Additional Notes:

Data structure used to hold Critters: ArrayList<Critters> population

