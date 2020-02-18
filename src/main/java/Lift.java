import java.util.Scanner;
import java.util.SortedSet;
import java.util.TreeSet;

public class Lift {

    static int numberOfFloors;
    static int timeGoUpOnFloor;
    static int timeGoDownOnFloor;
    static int liftTime;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите количество этажей: ");
        numberOfFloors = scanner.nextInt();
        System.out.print("Введите время необходимо на подъем человека на 1 этаж пешком: ");
        timeGoUpOnFloor = scanner.nextInt();
        System.out.print("Введите время необходимо на спуск человека на 1 этаж пешком: ");
        timeGoDownOnFloor = scanner.nextInt();
        System.out.print("Введите время которое необходимо лифту для перемещения на 1 этаж: ");
        liftTime = scanner.nextInt();

        Lift lift = new Lift();
        System.out.println("Result: " + lift.receiveTotalResultTime());

    }

    public int receiveTimeWithUseLift(int currentFloor, int floorToUp) {
        SortedSet<Integer> maxTimeToEnterOnFloor = new TreeSet<>();
        //Для тех, кто едет в в лифте, затем поднимается
        maxTimeToEnterOnFloor.add(currentFloor * liftTime + (numberOfFloors - currentFloor) * timeGoUpOnFloor);
        //Время для тех кто спускается после лифта
        maxTimeToEnterOnFloor.add(currentFloor * liftTime + (currentFloor - floorToUp - 1) * timeGoDownOnFloor);
        //Время для тех, кто не едет в лифте
        maxTimeToEnterOnFloor.add(floorToUp * timeGoUpOnFloor);
        return maxTimeToEnterOnFloor.last();
    }

    public int receiveTotalResultTime() {
        SortedSet<Integer> allTimeResults = new TreeSet<>();
        int floorToUp = 0;
        int totalTimeResult = numberOfFloors * timeGoUpOnFloor;
        allTimeResults.add(totalTimeResult);
        for (int currentFloor = 1; currentFloor <= numberOfFloors; currentFloor++) {
            // Этаж, на котором встретятся люди которые поднимаются и люди которые спускаются
            floorToUp = (liftTime + timeGoDownOnFloor) * currentFloor / (timeGoUpOnFloor + timeGoDownOnFloor);
            allTimeResults.add(receiveTimeWithUseLift(currentFloor, floorToUp));
            totalTimeResult = allTimeResults.first();
        }
        return totalTimeResult;
    }
}
