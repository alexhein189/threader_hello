package comp0008;

/*
 * Adder Framework which adds together a sequence of double-precision floating point numbers that are given as Strings.
 *
 * Task 1 - you should not need to change this file.
 * Task 2 - you should only need to change the one line highlighted below.
 * Task 3 - you should change the code to use the MultithreadedAdder instead of the Serial Adder. Also you may
 *          wish to change this code to run the Adder multiple times to determine average running times for your
 *          analysis of how much your multithreaded implementation has speeded up the wall clock running time.
 *
 * (c) K. Bryson 2020
 */

import java.io.*;

public class Main {

    /**
     * All this data is "statistically initialized" and hence visibility to all threads in the running application.
     */
    private static final String[] DATA1 = {"1.0", "2.0", "3.0", "4.0"};
    private static final String[] DATA2 = {"100000000000000000000.0", "-100000000000000000000.0", "1.0", "2.0"};
    private static final String[] DATA3 = {"1.0", "2.0", "100000000000000000000.0", "-100000000000000000000.0"};

    /**
     * This is an Example of more complex "static initialization" that guarantees data visibility to all threads.
     */

    private static final String[] DATA4;

    static {

        /*** TASK3: CHANGE THIS VALUE SO THAT YOUR COMPUTER TAKES SEVERAL SECONDS FOR THE SERIAL CASE ***/
//        final int POWER = 10;
        final int POWER = 1;
        final int N = (int)Math.pow(2, POWER);
        DATA4 = new String[N];

        for (int i = 0; i < N; i++) {
            DATA4[i] = String.valueOf(1.0/N);
        }
    }


    public static void main(String[] args) throws InterruptedException, IOException {

        // Start the timer ...
        long startTime = System.currentTimeMillis();

        /*** TASK 2 - CHANGE THIS LINE TO SEE HOW THE CODE BEHAVES WITH DIFFERENT DATA INPUTS. ***/
//        String[] values = DATA1;
        String[] values = DATA1;

        /*** TASK 3 - CHANGE THE FOLLOWING SINGLE LINE TO CHANGE TO USING A MULTITHREADED VERSION OF THE ADDER. ***/

        // This is an example of "programming to an interface" ... so only a single line
        // needs to be changed to change the implementation used in the rest of the code.

        Adder adder = new SerialAdder(); // = MultithreaderAdder();
        adder.setValues(values);

        new Thread(adder).start();

        System.out.println("Answer = " + adder.getSum());

        // Printed answer ... stop the timer.
        long endTime = System.currentTimeMillis();

        // Nanoseconds to seconds ...
        System.out.println("Time = " + (endTime - startTime)/1000.0 + " seconds.") ;

    }
}
