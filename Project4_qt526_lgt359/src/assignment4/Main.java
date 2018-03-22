/* CRITTERS Main.java
 * EE422C Project 4 submission by
 * <Quoc Truong>
 * <qt526>
 * <Lorrie Tria>
 * <lgt359>
 * Spring 2018
 */

package assignment4;
import org.omg.CORBA.DynAnyPackage.Invalid;

import java.util.List;
import java.util.Scanner;
import java.io.*;


/*
 * Usage: java <pkgname>.Main <input file> test
 * input file is optional.  If input file is specified, the word 'test' is optional.
 * May not use 'test' argument without specifying input file.
 */
public class Main {

    static Scanner kb;	// scanner connected to keyboard input, or input file
    private static String inputFile;	// input file, used instead of keyboard input if specified
    static ByteArrayOutputStream testOutputString;	// if test specified, holds all console output
    private static String myPackage;	// package of Critter file.  Critter cannot be in default pkg.
    private static boolean DEBUG = false; // Use it or not, as you wish!
    static PrintStream old = System.out;	// if you want to restore output to console


    // Gets the package name.  The usage assumes that Critter and its subclasses are all in the same package.
    static {
        myPackage = Critter.class.getPackage().toString().split(" ")[1];
    }

    /**
     * Main method.
     * @param args args can be empty.  If not empty, provide two parameters -- the first is a file name, 
     * and the second is test (for test output, where all output to be directed to a String), or nothing.
     */
    public static void main(String[] args) { 
        if (args.length != 0) {
            try {
                inputFile = args[0];
                kb = new Scanner(new File(inputFile));			
            } catch (FileNotFoundException e) {
                System.out.println("USAGE: java Main OR java Main <input file> <test output>");
                e.printStackTrace();
            } catch (NullPointerException e) {
                System.out.println("USAGE: java Main OR java Main <input file>  <test output>");
            }
            if (args.length >= 2) {
                if (args[1].equals("test")) { // if the word "test" is the second argument to java
                    // Create a stream to hold the output
                    testOutputString = new ByteArrayOutputStream();
                    PrintStream ps = new PrintStream(testOutputString);
                    // Save the old System.out.
                    old = System.out;
                    // Tell Java to use the special stream; all console output will be redirected here from now
                    System.setOut(ps);
                }
            }
        } else { // if no arguments to main
            kb = new Scanner(System.in); // use keyboard and console
        }

        /* Do not alter the code above for your submission. */
        /* Write your code below. */

        boolean loop = true;
        do {
            String input = kb.nextLine();
            String[] command = input.split(" ");

            switch (command[0]) {
                case "quit": {
                    if (command.length > 1) {
                        System.out.println("invalid command: " + input);
                    } else {
                        loop = false;
                        break;
                    }
                }

                case "show": {
                    if (command.length > 1) {
                        System.out.println("error processing: " + input);
                    } else {
                        Critter.displayWorld();
                    }

                    continue;
                }


                case "step": {
                    if (command.length > 2) {
                        System.out.println("error processing: " + input);
                    } else {
                        if (command.length == 1) {
                            Critter.worldTimeStep();
                        } else {
                            try {
                                int count = Integer.parseInt(command[1]);
                                for (int i = 0; i < count; i++) {
                                    Critter.worldTimeStep();
                                }
                            }
                            catch (NumberFormatException nfe){
                                System.out.println("error processing: " + input);
                            }
                        }
                    }
                    continue;
                }


                case "seed": {
                    if (command.length > 2) {
                        System.out.println("error processing: " + input);
                    } else {
                        try {
                            int seed = Integer.parseInt(command[1]);
                            Critter.setSeed(seed);
                        }
                        catch (NumberFormatException | IndexOutOfBoundsException e){
                            System.out.println("error processing: " + input);
                        }

                    }
                    continue;
                }

                case "make": {
                    if (command.length > 3 || command.length == 1) {
                        System.out.println("error processing: " + input);
                    } else {
                        if (command.length == 2) {
                            try {
                                Critter.makeCritter(command[1]);
                            } catch (InvalidCritterException ice) {
                                System.out.println("error processing: " + input);
                            }
                        } else {
                            int numCritters = 0;

                            try {
                                numCritters = (int) Integer.parseInt(command[2]);
                            } catch (NumberFormatException nfe) {
                                System.out.println("error processing: " + input);
                            }

                            for (int i = 0; i < numCritters; i++) {
                                try {
                                    Critter.makeCritter(command[1]);
                                } catch (InvalidCritterException ice) {
                                    System.out.println("error processing: " + input);
                                }
                            }
                        }
                    }
                    continue;
                }



                case "stats": {
                    if(command.length > 2 || command.length == 1)
                        System.out.println("error processing: " + input);
                    else
                    {
                        try{
                            List<Critter> list = Critter.getInstances(command[1]);                      // list is a list of all critters of specific type, command[1]
                            Class <?> c = Class.forName(myPackage + "." + command[1]);                  // c = name of Critter (Craig, Algae, etc)

                            // stats gets <Critter>.runStats
                            java.lang.reflect.Method stats = c.getMethod("runStats", java.util.List.class);

                            //invokes the underlying method, which in this case is <Critter>.runStats
                            stats.invoke(c, list);
                        }
                        catch(Exception e)
                        {
                            System.out.println("error processing: " + input);
                        }
                    }
                    continue;
                }

                default: {
                    System.out.println("invalid command:" + input);
                }

            }

        } while(loop);
        /* Write your code above */
        System.out.flush();
    }
}
