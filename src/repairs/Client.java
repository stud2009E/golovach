package repairs;

public class Client {

    public static void main(String[] args) {

        System.out.println("set a task to foreman");

        Foreman foreman = new Foreman(new String[]{
            "...............kitchen",
            "bedroom...............",
            ".........hall........."
        });
        foreman.start();
        foreman.join();

        System.out.printf("accept task from foreman");
    }
}
