package assignment4;
/* CRITTERS Main.java
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

        String input = kb.nextLine();
        String[] command = input.split(" ");

        switch (command[0]) {
            case "quit":{
                if (command.length > 1){
                    System.out.println("Invalid Input");
                }
                else{
                    System.exit(0);
                }
            }

            case "show":{
                if (command.length > 1){
                    System.out.println("Invalid Input");
                }
                else{
                    Critter.displayWorld();
                }
            }

            case "step":{
                if (command.length > 2){
                    System.out.println("Invalid Input");
                }
                else {
                    if (command.length == 1){
                        Critter.worldTimeStep();
                    }
                    else {
                        int count = Integer.parseInt(command[1]);
                        for(int i = 0; i < count; i++){
                            Critter.worldTimeStep();
                        }
                    }
                }
            }

            case "seed":{
                if (command.length > 2){
                    System.out.println("Invalid Input");
                }
                else {
                    int seed = Integer.parseInt(command[1]);
                    Critter.setSeed(seed);
                    }
            }

            case "make":{
                if (command.length > 3 || command.length == 1) {
                    System.out.println("Invalid Input");
                }
                else {
                    if (command.length == 2){
                        try {
                            Critter.makeCritter(command[1]);
                        }
                        catch (InvalidCritterException ice) {
                            System.out.println("Invalid Critter Name");
                        }
                    }
                    else {
                        int numCritters = (int) Integer.parseInt(command[2]);
                        for(int i = 0; i < numCritters; numCritters++) {
                            try {
                                Critter.makeCritter(command[1]);
                            }
                            catch (InvalidCritterException ice) {
                                System.out.println("Invalid Critter Name");
                            }
                        }
                    }
                }
            }

            case "stats": {
                if (command.length > 2){
                    System.out.println("Invalid Input");
                }

                // invoking get instance
                try {

                    List<Critter> sameCritters = Critter.getInstances(command[1]);



                }
                catch (InvalidCritterException ice) {
                    System.out.println("Invalid Critter Name");
                }

            }

        }


        /* Write your code above */
        System.out.flush();
    }
}
