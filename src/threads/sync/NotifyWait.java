package threads.sync;

public class NotifyWait {
    public static final Object object = new Object();

    public static void main(String[] args) throws InterruptedException {

        new Thread(() -> {
            synchronized (object){
                try {
                    System.out.println("1 sleep");
                    object.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.println("1 run");
        }).start();

        new Thread(() -> {
            synchronized (object){
                try {
                    System.out.println("2 sleep");
                    object.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.println("2 run");
        }).start();

        Thread.sleep(1000);
        System.out.println("main notify");
        synchronized (object){
            object.notifyAll();
        }
    }
}
