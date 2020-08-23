import java.util.ArrayList;
import java.util.List;

public enum Direction {
    W(1), S(2), E(3), N(4);

    private int dir;
    Direction(int dir) {
        this.dir = dir;
    }

    /*@Override
    public String toString() {
        return dir+"";
    }*/

    static List<Region> getPath(Direction from, Direction to) {
        List<Region> regions = new ArrayList<>();

        switch (from) {
            case W:
                switch (to) {
                    case S:
                        regions.add(Region.SW);
                        break;
                    case E:
                        regions.add(Region.SW);
                        regions.add(Region.SE);
                        break;
                    case N:
                        regions.add(Region.SW);
                        regions.add(Region.SE);
                        regions.add(Region.NE);
                        break;
                }
                break;
            case S:
                switch (to) {
                    case W:
                        regions.add(Region.SE);
                        regions.add(Region.NE);
                        regions.add(Region.NW);
                        break;
                    case E:
                        regions.add(Region.SE);
                        break;
                    case N:
                        regions.add(Region.SE);
                        regions.add(Region.NE);
                        break;
                }
                break;
            case E:
                switch (to) {
                    case W:
                        regions.add(Region.NE);
                        regions.add(Region.NW);
                        break;
                    case S:
                        regions.add(Region.NE);
                        regions.add(Region.NW);
                        regions.add(Region.SW);
                        break;
                    case N:
                        regions.add(Region.NE);
                        break;
                }
                break;
            case N:
                switch (to) {
                    case W:
                        regions.add(Region.NW);
                        break;
                    case S:
                        regions.add(Region.NW);
                        regions.add(Region.SW);
                        break;
                    case E:
                        regions.add(Region.NW);
                        regions.add(Region.SW);
                        regions.add(Region.SE);
                        break;
                }
                break;
        }
        return regions;
    }
}
