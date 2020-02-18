import java.util.Scanner;
import java.util.SortedSet;
import java.util.TreeSet;

public class Lift {

    static int numberOfFloors;
    static int timeGoUpOnFoot;
    static int timeGoDownOnFoot;
    static int liftTime;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите количество этажей: ");
        numberOfFloors = scanner.nextInt();
        System.out.print("Введите время необходимо на подъем человека на 1 этаж пешком: ");
        timeGoUpOnFoot = scanner.nextInt();
        System.out.print("Введите время необходимо на спуск человека на 1 этаж пешком: ");
        timeGoDownOnFoot = scanner.nextInt();
        System.out.print("Введите время которое необходимо лифту для перемещения на 1 этаж: ");
        liftTime = scanner.nextInt();

        Lift lift = new Lift();
        System.out.println("Result: " + lift.receiveTotalResultTime());

    }

    public int receiveTimeWithUseLift(int currentFloor, int floorToUp) {
        SortedSet<Integer> maxTimeToEnterFloor = new TreeSet<>();
        //Для тех, кто едет в в лифте, затем поднимается
        maxTimeToEnterFloor.add(currentFloor * liftTime + (numberOfFloors - currentFloor) * timeGoUpOnFoot);
        //Время для тех кто спускается поле лифта
        maxTimeToEnterFloor.add(currentFloor * liftTime + (currentFloor - floorToUp - 1) * timeGoDownOnFoot);
        //Время для тех, кто не едет в лифте
        maxTimeToEnterFloor.add(floorToUp * timeGoUpOnFoot);
        return maxTimeToEnterFloor.last();
    }

    public int receiveTotalResultTime() {
        SortedSet<Integer> allTimeResults = new TreeSet<>();
        int floorToUp = 0;
        int totalResult = numberOfFloors * timeGoUpOnFoot;
        allTimeResults.add(totalResult);
        for (int currentFloor = 1; currentFloor <= numberOfFloors; currentFloor++) {
            // Этаж, на котором встретятся люди которые поднимаются и люди которые спускаются
            floorToUp = (liftTime + timeGoDownOnFoot) * currentFloor / (timeGoUpOnFoot + timeGoDownOnFoot);
            allTimeResults.add(receiveTimeWithUseLift(currentFloor, floorToUp));
            totalResult = allTimeResults.first();
        }
        return totalResult;
    }
}
