# primes
prime number generator

The project consists of 3 main source files:

1. PrimeNumberGenerator.java - the interface for creating lists of prime numbers in a given range.
2. Generator.java - the implementation class of the interface.
3. App.java - a simple command line utility for testing the prime number range method of the generator.

There are 2 testing files to test the generator and the command line utility.

A build of the project is available as a jar file in the target directory.

# running
The target jar file is runnable, but java will need to be installed in order to execute. Once installed, users may enter the project directory and execute the following command to run the command line utility:

java -jar target/primes-0.0.1-SNAPSHOT.jar

The program will execute and prompt the user to enter integers to test with. By default, the program will give up after 5 invalid inputs. To change the number of attempts, provide a number as a command line argument when invoking the jar command. For example, to allow 10 invalid attempts, run the program like this:

java -jar target/primes-0.0.1-SNAPSHOT.jar 10

The program will take the two nunbers provided and print out a list of the primes that occur between them.

# building
The project is a simple maven project, so maven should be installed in order to compile and build the project. Once installed, navigate to the project root and execute:
  
mvn clean package

This command should be sufficient to download required packages, build, package, and test the project.
