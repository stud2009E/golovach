package repairs;

public class Worker implements Runnable{
    private String what = "";
    private int delay = 0;
    private Thread thread;

    public Worker(String what){
        this.what = what;
    }

    static int workerSpeed(int min, int max){
        return min + (int)(Math.random() * ((max - min) + 1));
    }

    public void work(){
        this.delay = workerSpeed(100, 500);
        this.thread = new Thread(this::run);
        thread.start();
    }

    public void join(){
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(what);
        }
    }
}
