package com.rensoft.demo.primes;

import java.util.ArrayList;
import java.util.List;

public class Generator implements PrimeNumberGenerator{

	//doesnt make sense to look at vals less than 1 ever
	private static final int ONE = 1;
	
	/**
	 * This implementation is based on the Sieve of Eratosthenes
	 * which is discussed here: https://en.wikipedia.org/wiki/Sieve_of_Eratosthenes
	 * 
	 * The nice thing about the sieve is that you can find all the primes
	 * up to a given number fairly easily. Then you can just return the ones
	 * that fall within your range. This is especially good for ranges 
	 * towards the end of Integer.MAX_VALUE where nested loops become increasingly
	 * bad for performance.
	 */
	public List<Integer> generate(int startingValue, int endingValue) {
		//starting and ending can be sent in any order, so lets do some testing
		int min = startingValue < endingValue ? startingValue : endingValue;
		int max = endingValue > startingValue ? endingValue : startingValue;
		//make sure we are at least greater than 1
		if(min < ONE) min = ONE;
		if(max < ONE) max = ONE;
        //create a bitset as a bucket to hold all the numbers up to max
        java.util.BitSet set = new java.util.BitSet(max);
        //set all the bits...this makes it look like everything is a prime
        set.set(ONE, max);
        //now for each number in our bitset...
        //...(except for index = 0 (num = 1)...which we can skip
        for(int i = 0; i < Math.sqrt(max); i++){ 
        	//the number we are looking at lives in the previous bit index
        	//so index 0 holds the prime value for 1, 1 holds the prime value for 2, etc
            int num = i + 1;  
            //if we have a prime number...tag the multiples as non-prime
            if(set.get(i)){
            	//we start at 2 * our number since the 1st multiple
            	//was already marked as a prime.
                //Get rid of the rest of the multiples of the current prime 
            	//since they are obviously not prime
                for(int j = num * 2 - 1; j < max; j += num){
                	//if the max passed in is Integer.MAX, 
                	//then j could become negative after the increment
                	//if that happens, bail
                	if(j < 0) {
                		break;
                	}
                	set.set(j, false);
                }
            }
        }

        //now we can build a list to send back to the client.
        //arraylist should be significantly cheaper than linkedlist.
        int primeCount = set.cardinality();
        int diff = max - min + 1;
        List<Integer> primes = new ArrayList<Integer>(diff < primeCount ? diff : primeCount);
        //we could start this at min, but code coverage complains that the inner if call is partially checked
        for(int i = 0; i < max; i++){
            if(set.get(i)){
                if(i + 1 >= min) {
                	primes.add(new Integer(i + 1));
                }
            }
        }
        return primes;
	}

	/**
	 * The one-off implementation has less overhead than the range check and
	 * simply checks to see if each number (up to n/2) divides n.
	 */
	public boolean isPrime(int value) {
		//dont bother with anything less than 2 or an even number
		if(value <= 1 || (value != 2 && value % 2 == 0)){
            return false;
        }
        int n = 2;
        //legend has it that no number has a factor larger than half itself
        int half = value / 2;
        //while we are ! > the largest possible factor
        while(n <= half){
            //if mod = 0, we evenly divided...no prime
            if(value % n == 0){
                return false;
            }
            //next check
            n++;
        }
        //if we made it here, we were not able to find a divisor, must be prime
        return true;
	}
}
