package threads.conveyor;

public class Buffer {

    private Integer value = null;

    public synchronized void set(int value) throws InterruptedException {
        while(this.value != null){
            this.wait();
        }

        this.value = value;

        this.notifyAll();
    }

    public synchronized int get() throws InterruptedException {
        while(value == null){
            this.wait();
        }

        int temp = value;
        value = null;
        this.notifyAll();

        return temp;
    }

}
