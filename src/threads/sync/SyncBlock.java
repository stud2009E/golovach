package threads.sync;

public class SyncBlock {

    public static void main(String[] args) throws InterruptedException {
        Object block = new Object();

        new Thread(() -> {
            synchronized (block){
                System.out.println("thread am in");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();


        System.out.println("main starts to sleep");
        Thread.sleep(1000);
        System.out.println("main stops to sleep");

        synchronized (block){
            System.out.println("main in sync");
        }
    }
}
