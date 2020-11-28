package threads.conveyor;

public class Consumer implements Runnable {

    private Buffer buffer;
    private long time;
    private String name;

    public Consumer(String name, long time, Buffer buffer) {
        this.buffer = buffer;
        this.time = time;
        this.name = name;
    }

    @Override
    public void run() {

        while (true) {
            try {
                int value = buffer.get();
                System.out.println(name + " consumed " + value);
                Thread.sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
