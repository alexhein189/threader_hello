package comp0008;

/**
 * A serial implementation of the Adder which uses a single thread to add all the numbers together.
 * But possibly this doesn't work as the client is expecting ... and there may be concurrency bugs ...
 * help to improve this implementation so that it works and it is thread-safe.
 */

public class SerialAdder implements Adder {

    public String[] values;
    public double sum;
    public boolean ready = false;

    public synchronized void run() {
        sum = 0.0;

        for (int i = 0; i < values.length; i++) {
            sum = sum + Double.valueOf(values[i]);
        }
        ready = true;
    }

    public synchronized void setValues(String[] values) {
        this.values = values;
    }

    public void setThreads(int threads) {
        // This does nothing since this is the single-threaded version.
    }

    public synchronized double getSum() {
        try{
            while(!ready){
                wait(1);
            }
        }catch (InterruptedException ie){
            ie.printStackTrace();
        }
        return sum;
    }
}
