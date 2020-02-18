import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Stream {
    public static void main(String[] args) {
        List<Integer> integerList = Arrays.asList(9, 19, 45, 1, 63, 6, 15, 15, 66, 87, 12, 15);
        List<Integer> collect = IntStream.range(0, integerList.size())
                .filter(n -> n % 2 == 0)
                .mapToObj(integerList::get)
                .collect(Collectors.toList());

        for (int i : collect) {
            System.out.println(i);
        }
    }

}

