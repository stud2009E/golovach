package threads.conveyor;

import java.util.Random;

public class Producer implements Runnable{

    private long time;
    private String name;
    private Buffer buffer;

    public Producer(String name, long time, Buffer buffer) {
        this.time = time;
        this.buffer = buffer;
        this.name = name;
    }

    @Override
    public void run() {

        while (true){
            try {
                int value = produce();
                buffer.set(value);
                System.out.println(name + " produced " + value);
                Thread.sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private int produce(){
        return (int) (Math.random() * (100 - 1) + 1);
    }
}
