package threads.sync;

public class SyncByObject {

    public static void main(String[] args) {
        new SyncByObject().f0();
        new SyncByObject().f1();

        f2();
        f3();

    }
    //f1, f0 identical methods synchronized by this
    public synchronized void f0(){
        this.notify();
    }

    public void f1(){
        synchronized (this){
            this.notify();
        }
    }

    //synchronized by class
    public static synchronized void f2(){
        SyncByObject.class.notify();
    }

    public static void f3() {
        synchronized (SyncByObject.class){
            SyncByObject.class.notify();
        }
    }

}
