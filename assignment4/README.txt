README.txt
EE422C_Project4_Critters
----------------------------------------------------------------------------
New Classes Implemented

Critter1.java
NAME: The Straight-Edge
ATTRIBUTES: This critter only fights when it has enough energy (>50)
MOVEMENT(doTimeStep): Always moves horizontally or vertically (randomly)

Critter2.java
NAME: The Diagonal
ATTRIBUTES: This critter only fights when it has enough energy (>50)
MOVEMENT(doTimeStep): Always moves diagonally (randomly)

Critter3.java
NAME: The Gardening Tool
ATTRIBUTES: This critter always wants to reproduce and never wants to fight (unless Algae)
MOVEMENT(doTimeStep): Reproduces if critter has enough energy; if not, walks
OFFSPRING POSITION: Offspring always spawns below parent

Critter4.java
NAME: The Drunk Rock
ATTRIBUTES: This critter always wants to fight!!!
MOVEMENT(doTimeStep): Always runs and charges randomly
-------------------------------------------------------------------------------
Methods and Flags Added

Method #1: runAway( )
Critter always attempts to run away towards the right (direction = 0, steps = 2)
Must check if critter's run away position is occupied by another.
If occupied, critter can not run away and instead stays in place. Energy is still deducted.
If not occupied, updates critter's position.

Method #2: move(int direction, int steps)
Helper function for run and walk that moves critter in corresponding direction and steps
@param direction 0-7, representing where to move critter
@param steps int tha represents how many steps critter takes

Method #3: printTopOrBottom( )
Helper function for displayWorld( ) that prints top/bottom of grid

Method #4: printMiddle
Helper function for displayWorld( ) that prints middle section of grid

Flag #1: hasMoved
Boolean flag that indicates whether a critter has moved or not

Flag #2: tryingToRun
Boolean flag that indicates whether a critter is attempting to run away during an encounter
-------------------------------------------------------------------------------
Additional Notes:

Data structure used to hold critters: ArrayList<Critter> population