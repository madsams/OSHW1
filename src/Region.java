import java.util.concurrent.Semaphore;


public class Region {
    static Region SE = new Region("SE");
    static Region SW = new Region("SW");
    static Region NE = new Region("NE");
    static Region NW = new Region("NW");

    private String name;
    private Semaphore semaphore = new Semaphore(1);
    private static Semaphore mutex = new Semaphore(1);

    public Region(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    static void downMutex(int carIndex) throws InterruptedException {
        mutex.acquire();
//        System.out.println("mutex locked by " + carIndex);
    }

    static void upMutex(int carIndex) {
        mutex.release(1);
//        System.out.println("mutex released by " + carIndex);
    }

    void down(int carIndex) {
        try {
            if (!semaphore.isFair()) {
                semaphore.acquire();
            } else{
            upMutex(carIndex);
            semaphore.acquire();
        }} catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(this + " locked by " + carIndex);
    }

    void up(int carIndex) {
        semaphore.release(1);
        System.out.println(this + " released by " + carIndex);
    }
}
