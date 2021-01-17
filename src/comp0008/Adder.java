package comp0008;

/**
 * Adder interface - DO NOT CHANGE THIS INTERFACE.
 *
 * This allows a sequence of double precision floating numbers which are stored as a String array
 * to be added together to produce a sum, running in the background as a separated thread.
 *
 * A user should:
 *  1) Use the setValues() to set the double precision floating point values to add together as Strings.
 *  2) Use setThreads() to set the number of threads if this is relevant.
 *  3) Then start the thread which is going to run this Adder Runnable.
 *  4) Get the resulting answer using getSum().
 */
public interface Adder extends Runnable {

    void setValues(String[] values);
    void setThreads(int threads);
    double getSum();

}
