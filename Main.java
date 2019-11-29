import java.util.Arrays;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
  public static void main(String[] args) {
    GeneticAlgorithm GA = new GeneticAlgorithm();
    Service service = new Service();
    Controller controller = new Controller();
    int n = controller.inputTotalPopulation();
    String[] dataString = new String[6];
    char[][] dataChar = new char[6][7];
    int[][] dataInt = new int[6][7];
    dataString = service.initialWords();
    dataChar = service.initialAlphabet(dataString);
    dataInt = service.convertIndexOf(dataChar);
    String targetString = controller.inputTarget();
    char[] targetChar = targetString.toCharArray();
    int[] targetInt = service.IndexArrayOf(targetChar);
    controller.printTarget(targetChar, targetInt);
    for (int i = 0; i < n; i++) {
      System.out.println(
          "\n======================================================================================\nIteration :" + i);
      controller.printData(dataChar, dataInt, dataString, "POPULASI");
      int[] fitness = GA.fitnessOfAll(dataInt, targetInt);
      int index = GA.MAXIndexOf(fitness);
      controller.printFitness(dataChar, dataInt, dataString, fitness, index);
      int[][] value = GA.makeRouletteValueFrom(fitness);
      int[] selection = GA.playRouletteMachineUsing(value);
      int[][] offspringInt = GA.makeOffspringBasedOn(selection, dataInt);
      List<List<Integer>> offspringList = service.arrayToList(offspringInt);
      char[][] offspringChar = service.convertAlphabetOf(offspringInt);
      String[] offspringString = service.convertStringOf(offspringChar);
      controller.printOffspring(offspringChar, offspringInt, selection);
      int[][] range = GA.rangeIndexOf(offspringInt);
      int[][] rangeperData = service.twiceOn(range);
      int[][] crossOverInt = GA.crossOverIn(offspringInt, range);
      char[][] crossOverChar = service.convertAlphabetOf(crossOverInt);
      String[] crossOverString = service.convertStringOf(crossOverChar);
      controller.printCrossOver(crossOverChar, crossOverInt, crossOverString, controller.stringArrayOf(rangeperData));
      int[][] mutationInt = GA.mutationOf(crossOverInt);
      char[][] mutationChar = service.convertAlphabetOf(mutationInt);
      String[] mutationString = service.convertStringOf(mutationChar);
      controller.printData(mutationChar, mutationInt, mutationString, "MUTATION");
      int[][] offspringArray = service.listToArray(offspringList);
      // System.out.println(Arrays.deepToString(offspringArray));
      int[][] joinInt = service.joinOf(offspringArray, mutationInt);
      char[][] joinChar = service.convertAlphabetOf(joinInt);
      String[] joinString = service.convertStringOf(joinChar);
      controller.printData(joinChar, joinInt, joinString, "JOIN");
      int[] fitnessJoin = GA.fitnessOfAll(joinInt, targetInt);
      controller.printJoinFitness(joinChar, joinInt, joinString, fitnessJoin);
      int[][] fitnessAndIndex = service.makeIndexOf(fitnessJoin);
      int[][] sortFitness = service.sortByValueOf(fitnessAndIndex);
      int[][] individualInt = service.dataOf(joinInt, sortFitness);
      char[][] individualChar = service.convertAlphabetOf(individualInt);
      String[] individualString = service.convertStringOf(individualChar);
      controller.printData(individualChar, individualInt, individualString, "RANKING");
      dataInt = GA.elitismOf(individualInt);
      dataChar = service.convertAlphabetOf(dataInt);
      dataString = service.convertStringOf(dataChar);
    }
    System.out.println("======================================================================================");
    controller.printData(dataChar, dataInt, dataString, "RESULT");
    // System.out.println(Arrays.deepToString(sortFitness));
    // Arrays.sort(fitnessJoin, Collections.reverseOrder());

  }
}