package comp0008;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.DoubleAdder;

public class Summer implements Runnable {
    private int beginIndex;
    private int endIndex;
    private DoubleAdder doubleAdder;
    private volatile String[] values;
    private CountDownLatch countDownLatch;

    public Summer(int beginIndex, int endIndex, DoubleAdder doubleAdder, String[] values, CountDownLatch countDownLatch){
        this.beginIndex = beginIndex;
        this.endIndex = endIndex;
        this.doubleAdder = doubleAdder;
        this.values = values;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        try{
            for(int i=beginIndex;i<endIndex;i++){
                doubleAdder.add(Double.parseDouble(values[i]));
            }
        }finally{
            countDownLatch.countDown();
        }

    }
}
