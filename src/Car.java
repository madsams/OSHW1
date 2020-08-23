import java.util.List;
import java.util.Objects;

public class Car implements Runnable {
    private Direction from;
    private Direction to;
    private List<Region> path;
    private int time;
    private int index;

    Car(Direction from, Direction to, int time, int index) {
        this.from = from;
        this.to = to;
        this.index = index;
        path = Direction.getPath(from, to);
        this.time = time;
    }

    /*@Override
    public String toString() {
        return "Car " + index;
    }*/

    @Override
    public String toString() {
        return "Car with [" +
                from +
                " to " + to +
                "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return time == car.time &&
                from == car.from &&
                to == car.to;
    }

    @Override
    public int hashCode() {
        return Objects.hash(from, to, time);
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000L * time);
            lock();
            arrive();
            pass();
            release();
        } catch (InterruptedException e) {
            System.out.println("Interrupt: " + e.getMessage());
        }

    }

    private void lock() throws InterruptedException {
        Region.downMutex(index);
        for (Region region : path) {
            region.down(index);
        }
        Region.upMutex(index);
    }

    private void release() throws InterruptedException {
        Region.downMutex(index);
        for (Region region : path) {
            region.up(index);
        }
        Region.upMutex(index);
    }


    private void arrive() {
        System.out.println(this + " wanna pass the intersection");
    }

    private void pass() throws InterruptedException {
        Thread.sleep(1000L * path.size());
        System.out.println(this + " passed safely");
    }
}
