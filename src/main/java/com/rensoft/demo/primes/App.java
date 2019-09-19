package com.rensoft.demo.primes;

import java.util.List;
import java.util.Scanner;

public class App 
{
	//for testing purposes only...
	public static List<Integer> primes;
	//user still needs to enter some valid input
	private static final int INVALID = -1;
	//build a simple prompt
	private static final String PROMPT = String.format("Enter an integer from 0 to %d: ", Integer.MAX_VALUE);
	private static final String ERROR = "Try Again. ";
	//the number of times we will try to set the values from std in per method call
	private int maxErrors;
	//the overall app error state
	private boolean inError = false;
	//left boond
	private int startingValue = INVALID;
	//right bound
	private int endingValue = INVALID;
	//something to collect the input
	private Scanner scanner = new Scanner(System.in);
	
	//assume 3 errors per collection attempt
	public App() {
		this(3);
	}
	
	//allow users to specifiy the error count during init
	public App(int maxErrors) {
		setMaxErrors(maxErrors);
	}
	
	//build the generator and use whatever values we have set so far
	//if we are in some sort of error condition, it is possible
	//only starting will be set. We will allow generation in all cases
	//but we could bail if we wanted with some error messaging if desired
	public List<Integer> generatePrimes() {
		PrimeNumberGenerator gen = new Generator();
		List<Integer> primes = gen.generate(startingValue, endingValue);
		return primes;
	}
	
	//this method will use the scanner to collect a pair of ints. 
	//users will get up to maxErrors per invocation to collect the input
	public void askForValues() {
		//clear this for now, since we are going to ask again
		setInError(false);
		//make a local copy of this
		int attempts = maxErrors;
		//try and set our values
		while((startingValue == INVALID || endingValue == INVALID) && attempts > 0) {
			int val = INVALID;
			//if we are in an error state, let em know
			if(isInError()) {
				System.out.print(ERROR);
			}
			//ask for input
			System.out.print(PROMPT);
			//do we have an int?
			if(scanner.hasNextInt()) {
				//reset this for now since we got a decent value
				setInError(false);
				//set a val
				val = scanner.nextInt();
				if(startingValue == INVALID){
					startingValue = val;
				}
				else {
					endingValue = val;
				}
			}
			//something other than an int...set the error flag
			else {
				setInError(true);
				attempts--;
			}
			//flush any garbage
			scanner.nextLine();			
		}
	}
	
    public int getStartingValue() {
		return startingValue;
	}

	public void setStartingValue(int startingValue) {
		this.startingValue = startingValue;
	}

	public int getEndingValue() {
		return endingValue;
	}

	public void setEndingValue(int endingValue) {
		this.endingValue = endingValue;
	}

	public int getMaxErrors() {
		return maxErrors;
	}

	public void setMaxErrors(int maxErrors) {
		this.maxErrors = maxErrors;
	}
	
	public boolean isInError() {
		return inError;
	}

	public void setInError(boolean inError) {
		this.inError = inError;
	}

	public static void main( String[] args )
    {
		App.primes = null;
		int maxErrors = 3;
		if(args.length == 1) {
			try {
				maxErrors = Integer.parseInt(args[0]);
			}
			catch(Exception e) {
				//log here
				System.err.println("Exception for max errors: " + e.getMessage());
			}
		}
		//make a new app
        App app = new App(maxErrors);
        //collect input
        app.askForValues();
        //bail?
        if(app.isInError()) {
        	System.out.println("Too many errors...bailing");
        }
        //made it here, show something
        else {
        	App.primes = app.generatePrimes();
            System.out.println("We got back a list of " + primes.size());
            for(Integer i : App.primes) {
            	System.out.println(i);
            }
        }
        
    }
}
