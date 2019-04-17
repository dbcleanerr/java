public abstract class Abs implements Runnable {
    public abstract void process();

    public void run() {
        System.out.println("Begin");
        process();
        System.out.println("End");
    }
}
