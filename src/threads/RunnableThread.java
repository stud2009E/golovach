package threads;

public  class RunnableThread {

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> RunnableThread.sillyWork("run"));
        t1.start();

        sillyWork("main");
    }

    private static void sillyWork(String word){
        double d = 0;

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 100_000; j++) {
                d = Math.sin(d);
            }

            System.out.println(word);
        }

    }
}