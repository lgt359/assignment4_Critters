package assignment4;
/* CRITTERS Critter.java
 * EE422C Project 4 submission by
 * Replace <...> with your actual data.
 * <Student1 Name>
 * <Student1 EID>
 * <Student1 5-digit Unique No.>
 * <Student2 Name>
 * <Student2 EID>
 * <Student2 5-digit Unique No.>
 * Slip days used: <0>
 * Fall 2016
 */


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.lang.Math.ceil;

/* see the PDF for descriptions of the methods and fields in this class
 * you may add fields, methods or inner classes to Critter ONLY if you make your additions private
 * no new public, protected or default-package code or data can be added to Critter
 */


public abstract class Critter {
	private static String myPackage;
	private	static List<Critter> population = new java.util.ArrayList<Critter>();
	private static List<Critter> babies = new java.util.ArrayList<Critter>();

	// Gets the package name.  This assumes that Critter and its subclasses are all in the same package.
	static {
		myPackage = Critter.class.getPackage().toString().split(" ")[1];
	}

	// Returns a randomly generated integer
	private static java.util.Random rand = new java.util.Random();
	public static int getRandomInt(int max) {
		return rand.nextInt(max);
	}

	// Gets a random seed
	public static void setSeed(long new_seed) {
		rand = new java.util.Random(new_seed);
	}

	// A one-character long string that visually depicts your critter in the ASCII interface */
	public String toString() { return ""; }

	// Returns energy
	private int energy = 0;
	protected int getEnergy() { return energy; }

	// Data
	private int x_coord;
	private int y_coord;
	public boolean hasMoved;
	public boolean tryingToRun;


	/** Moves critter one spot in given direction, 0-7.
     *  Deducts energy cost from critter's current energy state.
	 * @param direction
	 */
	protected final void walk(int direction) {
        if (!this.hasMoved) {
			move(direction, 1);
			this.hasMoved = true;		// raises the flag
        }

		this.energy -= Params.walk_energy_cost;     // deduct energy cost
	}
	
	protected final void run(int direction) {
		if (!this.hasMoved) {
			if(tryingToRun = true){
				runAway();
			}
			else {
				move(direction, 2);
				this.hasMoved = true;		// raises the flag
			}
		}

		this.energy -= Params.run_energy_cost;     // deduct energy cost
	}

	protected final void runAway() {
		boolean isOccupied = false;

		if (!this.hasMoved) {

			int x_new_coord = this.x_coord + 2;

			if (x_new_coord >= Params.world_width){
				x_new_coord = Params.world_width % x_coord;
			}

			for (Critter c : population) {
				if ((x_new_coord == c.x_coord) && (this.y_coord == c.y_coord)) {
					isOccupied = true;
					break;
				}
			}

			if (!isOccupied) {
				move(0, 2);
			}
			this.hasMoved = true;		// raises the flag
		}

	}

    protected final void move( int direction, int steps) {

        switch (direction) {
            case 0: //move east (step) units
                x_coord += steps;
                break;
            case 1: //move northeast (step) units
                x_coord += steps;
                y_coord -= steps;
                break;
            case 2: //move north (step) units
                y_coord -= steps;
            case 3: //move northwest (step) units
                x_coord -= steps;
                y_coord -= steps;
                break;
            case 4: //move west (step) units
                x_coord -= steps;
                break;
            case 5: //move southwest (step) units
                x_coord -= steps;
				y_coord += steps;
				break;
            case 6: //move south (step) units
                y_coord += steps; ;
                break;
            case 7: //southeast (step) units
                x_coord += steps;
				y_coord += steps;
				break;
		}

		// Wrap-around correction
		if (x_coord < 0)
			x_coord += Params.world_width;

		if (x_coord >= Params.world_width)
			x_coord = Params.world_width % x_coord;

		if (y_coord < 0)
			y_coord += Params.world_height;

		if (y_coord >= Params.world_height)
			y_coord = Params.world_height % y_coord;

    }
	
	protected final void reproduce(Critter offspring, int direction) {

		if(this.getEnergy() < Params.min_reproduce_energy) {
			return;
		}

		offspring.energy = this.energy / 2;
		this.energy = (int) Math.ceil(this.energy / 2);

		switch (direction) {
			case 0: //move east (step) units
				offspring.x_coord = this.x_coord + 1;
				offspring.y_coord = this.y_coord;
				break;

			case 1: //move northeast (step) units
				offspring.x_coord = this.x_coord + 1;
				offspring.y_coord = this.y_coord - 1;
				break;

			case 2: //move north (step) units
				offspring.x_coord = this.x_coord;
				offspring.y_coord = this.y_coord - 1;
				break;

			case 3: //move northwest (step) units
				offspring.x_coord = this.x_coord - 1;
				offspring.y_coord = this.y_coord - 1;
				break;

			case 4: //move west (step) units
				offspring.x_coord = this.x_coord - 1;
				offspring.y_coord = this.y_coord;
				break;

			case 5: //move southwest (step) units
				offspring.x_coord = this.x_coord - 1;
				offspring.y_coord = this.y_coord + 1;
				break;

			case 6: //move south (step) units
				offspring.x_coord = this.x_coord;
				offspring.y_coord = this.y_coord + 1;
				break;

			case 7: //southeast (step) units
				offspring.x_coord = this.x_coord + 1;
				offspring.y_coord = this.y_coord + 1;
				break;
		}

		// Wrap-around correction
		if (offspring.x_coord < 0)
			offspring.x_coord += Params.world_width;

		if (offspring.x_coord >= Params.world_width)
			offspring.x_coord = Params.world_width % offspring.x_coord;

		if (offspring.y_coord < 0)
			offspring.y_coord += Params.world_height;

		if (offspring.y_coord >= Params.world_height)
			offspring.y_coord = Params.world_height % offspring.y_coord;

		// add to list of babies
		babies.add(offspring);
	}

	public abstract void doTimeStep();
	public abstract boolean fight(String oponent);

	/**
	 * create and initialize a Critter subclass.
	 * critter_class_name must be the unqualified name of a concrete subclass of Critter, if not,
	 * an InvalidCritterException must be thrown.
	 * (Java weirdness: Exception throwing does not work properly if the parameter has lower-case instead of
	 * upper. For example, if craig is supplied instead of Craig, an error is thrown instead of
	 * an Exception.)
	 * @param critter_class_name
	 * @throws InvalidCritterException
	 */
	public static void makeCritter(String critter_class_name) throws InvalidCritterException {

		try {
			// create new critter
			Class<?> c = Class.forName(myPackage + "." + critter_class_name);
			Critter newCritter = (Critter) c.newInstance();
			population.add(newCritter);

			// initialize critter  values
			newCritter.x_coord = getRandomInt(Params.world_width);
			newCritter.y_coord = getRandomInt(Params.world_height);
			newCritter.energy = Params.start_energy;

		}
		catch(InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			throw new InvalidCritterException(critter_class_name);
		}
	}
	
	/**
	 * Gets a list of critters of a specific type.
	 * @param critter_class_name What kind of Critter is to be listed.  Unqualified class name.
	 * @return List of Critters.
	 * @throws InvalidCritterException
	 */
	public static List<Critter> getInstances(String critter_class_name) throws InvalidCritterException {
        List<Critter> result = new ArrayList<Critter>();

		// Initialize and get critter class
		Class<?> critter = null;
		try {
		    // get class object corresponding to critter class name
			critter = Class.forName(myPackage + "." + critter_class_name);
		}
		catch (ClassNotFoundException cnfe) {
		    throw new InvalidCritterException(critter_class_name);
		}

		// add critters to result
		for (Critter c : population) {
		    if (critter.isInstance(c)) {
		        result.add(c);
		    }
		}

		return result;
	}
	
	/**
	 * Prints out how many Critters of each type there are on the board.
	 * @param critters List of Critters.
	 */
	public static void runStats(List<Critter> critters) {
		System.out.print("" + critters.size() + " critters as follows -- ");
		java.util.Map<String, Integer> critter_count = new java.util.HashMap<String, Integer>();
		for (Critter crit : critters) {
			String crit_string = crit.toString();
			Integer old_count = critter_count.get(crit_string);
			if (old_count == null) {
				critter_count.put(crit_string,  1);
			} else {
				critter_count.put(crit_string, old_count.intValue() + 1);
			}
		}
		String prefix = "";
		for (String s : critter_count.keySet()) {
			System.out.print(prefix + s + ":" + critter_count.get(s));
			prefix = ", ";
		}
		System.out.println();		
	}
	
	/* the TestCritter class allows some critters to "cheat". If you want to 
	 * create tests of your Critter model, you can create subclasses of this class
	 * and then use the setter functions contained here. 
	 * 
	 * NOTE: you must make sure that the setter functions work with your implementation
	 * of Critter. That means, if you're recording the positions of your critters
	 * using some sort of external grid or some other data structure in addition
	 * to the x_coord and y_coord functions, then you MUST update these setter functions
	 * so that they correctly update your grid/data structure.
	 */
	static abstract class TestCritter extends Critter {
		protected void setEnergy(int new_energy_value) {
			super.energy = new_energy_value;
		}
		
		protected void setX_coord(int new_x_coord) {
			super.x_coord = new_x_coord;
		}
		
		protected void setY_coord(int new_y_coord) {
			super.y_coord = new_y_coord;
		}
		
		protected int getX_coord() {
			return super.x_coord;
		}
		
		protected int getY_coord() {
			return super.y_coord;
		}
		

		/*
		 * This method getPopulation has to be modified by you if you are not using the population
		 * ArrayList that has been provided in the starter code.  In any case, it has to be
		 * implemented for grading tests to work.
		 */
		protected static List<Critter> getPopulation() {
			return population;
		}
		
		/*
		 * This method getBabies has to be modified by you if you are not using the babies
		 * ArrayList that has been provided in the starter code.  In any case, it has to be
		 * implemented for grading tests to work.  Babies should be added to the general population 
		 * at either the beginning OR the end of every timestep.
		 */
		protected static List<Critter> getBabies() {
			return babies;
		}
	}

	/**
	 * Clear the world of all critters, dead and alive
	 */
	public static void clearWorld() {
        population.clear();
        babies.clear();
	}

	/* 1. Invoke doTimeSteps for all critters
	 * 2. Encounters
	 *
	 */
	public static void worldTimeStep() {

		// 1. Invoke doTimeStep for all critters in population
		for (Critter c : population){
			c.doTimeStep();		// hasMoved is set to true
		}

		// 2. Check for encounters
		for (Critter first : population){
			for (Critter second : population){

				// same critters, don't need to check
				if (first == second){
					continue;
				}

				// if one critter is dead, continue loop
				if(first.energy <= 0 || second.energy <= 0){
					continue;
				}

				// Check if same coordinate
				if(first.x_coord == second.x_coord && first.y_coord == second.y_coord){

					// encounter!!!!
					first.tryingToRun = true;
					second.tryingToRun = true;

					// Get die rolls
					int first_dice = 0;
					int second_dice = 0;

					// Check who wants to fight
					if (first.fight(second.toString())) {
						first_dice = getRandomInt(first.energy);
					}
					if (second.fight(first.toString())) {
						second_dice = getRandomInt(second.energy);
					}

					// Check if one ran away
					if(!(first.x_coord == second.x_coord && first.y_coord == second.y_coord)){
						continue;
					}

					// Find the winner (if ties, choose first critter)
					if (first_dice >= second_dice) {
						first.energy += second.energy/2;
						second.energy = 0;
					}
					else {
						second.energy += first.energy/2;
						first.energy = 0;
					}
				}
			}
		}

		// 3. Deduct rest energy
		for(Critter c : population) {
			c.energy -= Params.rest_energy_cost;
		}

		// 4. Add algae
		for (int i = 0; i < Params.refresh_algae_count; i+=1) {
			try {
				makeCritter("Algae");
			}
			catch (InvalidCritterException ice) {
				System.out.println("error processing: Algae");
			}
		}

		// 5. Remove dead critters
		List<Critter> deadCritters = new ArrayList<Critter>();

		for(Critter c : population) {
			if (c.energy <= 0) {
				deadCritters.add(c);
			}
		}
		population.removeAll(deadCritters);

		// 6. Add babies to population
		population.addAll(babies);
		babies.clear();

		// 7. Reset all flags
		for (Critter c: population) {
			c.hasMoved = false;
			c.tryingToRun = false;
		}
	}
	
	public static void displayWorld() {
		printTopOrBottom();
		printMiddle();
		printTopOrBottom();
	}


	public static void printTopOrBottom() {
		System.out.print("+");
		for (int i = 0; i < Params.world_width; i++) {
			System.out.print("-");
		}
		System.out.println("+");
	}

	public static void printMiddle() {
		for(int i = 0; i < Params.world_height; i++){
			System.out.print("|");

			for(int j = 0; j < Params.world_width; j++){
				boolean isPrinted = false;			// helper flag to print " "

				if(population.isEmpty()){
					System.out.print(" ");
					continue;
				}

				// Check if critter lies on coordinate
				for(Critter c : population){
					if(c.x_coord == j && c.y_coord == i){
						System.out.print(c.toString());
						isPrinted = true;
						break;
					}
				}
				// No critter occupies space
				if(!isPrinted) {
					System.out.print(" ");
				}
			}

			System.out.println("|");
		}
	}
}


