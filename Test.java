import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Test {
  public static void main(String[] args) {
    Service service = new Service();
    int[] data = { 20, 10, 40, 35, 25 };
    int[][] indexData = service.makeIndexOf(data);
    System.out.println(Arrays.deepToString(indexData));
    int[][] ranking = service.sortByValueOf(indexData);
    System.out.println(Arrays.deepToString(ranking));
    // List<List<Integer>> lists = service.arrayToList(data);
    // lists.stream().forEach(System.out::println);
    // int[][] arrays = service.listToArray(lists);
    // System.out.println(Arrays.deepToString(arrays));
  }
}