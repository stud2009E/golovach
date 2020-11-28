package threads.name;

public class Name {
    public static void main(String[] args) {
        MyRunnable myRunner = new MyRunnable();
        new Thread(myRunner).start();

        myRunner.run();
    }

    private static class MyRunnable implements Runnable{
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName());
        }
    }

}
