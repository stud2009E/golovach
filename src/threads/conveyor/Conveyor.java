package threads.conveyor;

public class Conveyor {

    public static void main(String[] args) {
        Buffer buffer = new Buffer();

        Producer producer1 = new Producer("p_1", 200, buffer);
        Producer producer2 = new Producer("p_2", 300, buffer);

        Consumer consumer = new Consumer("c_1", 1000, buffer);

        new Thread(producer1).start();
        new Thread(producer2).start();
        new Thread(consumer).start();

    }
}
