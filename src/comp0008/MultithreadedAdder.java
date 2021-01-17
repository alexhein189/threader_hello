package comp0008;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.DoubleAdder;

/**
 * A multithreaded implementation of the Adder which uses a specified number of threads to add all the numbers together.
 * This class needs to be implemented in TASK 3 ...
 */

public class MultithreadedAdder implements Adder {
    public volatile String[] values;
    private int numOfThreads;
    /**
     * Double adder is used
     */
    private ExecutorService pool;
    private CountDownLatch countDownLatch;
    private DoubleAdder doubleAdder = new DoubleAdder();

    public void run() {
        pool = Executors.newFixedThreadPool(numOfThreads);
        int numOfElements = values.length;
        for(int i=0;i<numOfElements;i = i + (numOfElements)/(numOfThreads)){
            pool.submit(new Summer(i, i+(numOfElements/numOfThreads),doubleAdder,values,countDownLatch));
        }
    };

    public void setValues(String[] values) {
        this.values = values;
        setThreads(8);
        countDownLatch = new CountDownLatch(numOfThreads);
    };

    public void setThreads(int threads) {
        this.numOfThreads = threads;
    };

    public double getSum() {
        try{
            countDownLatch.await();
        }catch (InterruptedException ie){
            ie.printStackTrace();
        }
        pool.shutdown();
        return doubleAdder.doubleValue();
    }
}
