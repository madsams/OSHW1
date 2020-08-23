import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Router {
    private List<Car> cars = new ArrayList<>();

    public static void main(String[] args) {
        Router router = new Router();
        Scanner scanner = new Scanner(System.in);

        System.out.println("- To add a car enter: \'car [from] [to] [time]\'");
        System.out.println("-\'From\' and \'To\' can be W, S, E, N and a combination of two of them like SE");
        System.out.println("-\'Time\' is the moment that car arrives to crossroad");
        System.out.println("- After adding cars you can enter \'start\' to run and see the result");

        String string = "";

        int i=0;

        while (!string.equalsIgnoreCase("start")) {
            string = scanner.next();

            if (string.equalsIgnoreCase("car")) {
                String from = scanner.next();
                String to = scanner.next();
                int time = scanner.nextInt();

                router.cars.add(new Car(Direction.valueOf(from.toUpperCase()), Direction.valueOf(to.toUpperCase()), time, i++));
            }
        }

        router.start();
    }

    private void start() {
        for (Car car : cars) {
            Thread thread = new Thread(car);
            thread.start();
        }
    }
}
