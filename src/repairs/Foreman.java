package repairs;

import java.util.ArrayList;

public class Foreman implements Runnable{
    private Thread thread;
    private ArrayList<Worker> employees = new ArrayList<>();
    private Worker cleaner = new Worker("---------cleaning----------");

    public Foreman(String[] rooms){
        thread = new Thread(this);

        for (int i = 0; i < rooms.length; i++) {
            employees.add(new Worker(rooms[i]));
        }
    }


    @Override
    public void run() {
        System.out.println("repair start");

        for (int i = 0; i < 3; i++) {
            employees.forEach(worker -> worker.work());
            employees.forEach(worker -> worker.join());

            cleaner.run();
        }

        System.out.println("repair end");
    }

    public void start(){
        thread.start();
    }

    public void join(){
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
