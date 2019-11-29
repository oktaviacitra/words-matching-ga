import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Random;
import java.util.Map;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Comparator;
// import java.util.Arrays;

public class Service {
  char[] alphabetArray;

  public Service() {
    alphabetArray = new char[] { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q',
        'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
  }

  public int findIndexOf(char alphabet) {
    int index = 0;
    for (int i = 0; i < alphabetArray.length; i++) {
      if (alphabetArray[i] == alphabet) {
        index = i + 1;
        break;
      }
    }
    return index;
  }

  public char findAlphabetOf(int index) {
    return alphabetArray[index - 1];
  }

  public char[] AlphabetArrayOf(int[] dataInt) {
    int n = dataInt.length;
    char[] dataChar = new char[n];
    for (int i = 0; i < n; i++) {
      dataChar[i] = findAlphabetOf(dataInt[i]);
    }
    return dataChar;
  }

  public int[] IndexArrayOf(char[] dataChar) {
    int n = dataChar.length;
    int[] dataInt = new int[n];
    for (int i = 0; i < n; i++) {
      dataInt[i] = findIndexOf(dataChar[i]);
    }
    return dataInt;
  }

  public int[][] convertIndexOf(char[][] dataChar) {
    int n = dataChar.length;
    int m = dataChar[0].length;
    int[][] dataInt = new int[n][m];
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        dataInt[i][j] = findIndexOf(dataChar[i][j]);
      }
    }
    return dataInt;
  }

  public char[][] convertAlphabetOf(int[][] dataInt) {
    int n = dataInt.length;
    int m = dataInt[0].length;
    char[][] dataChar = new char[n][m];
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        // System.out.println(dataInt[i][j]);
        dataChar[i][j] = findAlphabetOf(dataInt[i][j]);
        // System.out.println(dataInt[i][j] + ":" + dataChar[i][j]);
      }
    }
    return dataChar;
  }

  public String[] convertStringOf(char[][] dataChar) {
    int n = dataChar.length;
    String[] result = new String[n];
    for (int i = 0; i < n; i++) {
      result[i] = new String(dataChar[i]);
    }
    return result;
  }

  public String[] initialWords() {
    String[] words = new String[] { "OMURICE", "TEMPURA", "SEKIHAN", "KARAAGE", "KOROKKE", "BASASHI" };
    return words;
  }

  public char[][] initialAlphabet(String[] words) {
    int n = words.length;
    char[][] alphabets = new char[n][7];
    for (int i = 0; i < n; i++) {
      alphabets[i] = words[i].toCharArray();
    }
    return alphabets;
  }

  public int totalOf(int[] fitness) {
    int sum = 0;
    for (int i = 0; i < fitness.length; i++) {
      sum += fitness[i];
    }
    return sum;
  }

  public int randomIntIn(int min, int max) {
    // if (min >= max) {
    // throw new IllegalArgumentException("max must be greater than min");
    // }

    // Random random = new Random();
    // int value = min;
    // do {
    // value = random.nextInt((max - min) + 1) + min;
    // } while (value > max);
    Random random = new Random();
    int value = random.ints(min, (max + 1)).limit(1).findFirst().getAsInt();
    return value;
  }

  public double randomFloatIn(int min, int max) {
    if (min >= max) {
      throw new IllegalArgumentException("max must be greater than min");
    }
    double value = min;
    do {
      value = (Math.random() * ((max - min) + 1)) + min;
    } while (value > max);

    return value;
  }

  public int[][] swap(int[] a, int[] b) {
    int max = Math.min(a.length, b.length);
    for (int i = 0; i < max; i++) {
      int temp = a[i];
      a[i] = b[i];
      b[i] = temp;
    }
    int[][] result = new int[2][max];
    for (int i = 0; i < max; i++) {
      result[0][i] = a[i];
      result[1][i] = b[i];
    }
    return result;
  }

  public int[][] partOf(int[][] data, int[] range) {
    int size = range[1] - range[0] + 1;
    int[][] result = new int[2][size];
    for (int i = 0; i < 2; i++) {
      int k = range[0];
      for (int j = 0; j < size; j++) {
        result[i][j] = data[i][k];
        k++;
      }
    }
    return result;
  }

  public int[][] oneOf(int[][] data, int[][] part, int[] range) {
    for (int i = 0; i < data.length; i++) {
      int k = 0;
      for (int j = range[0]; j <= range[1]; j++) {
        data[i][j] = part[i][k];
        k++;
      }
    }
    return data;
  }

  public int[] randomDistinctIn(int n) {
    ArrayList<Integer> numbers = new ArrayList<Integer>(n);
    for (int i = 0; i < n; i++) {
      numbers.add(i);
    }
    Collections.shuffle(numbers);
    int[] random = new int[n];
    for (int i = 0; i < n; i++) {
      random[i] = numbers.get(i);
    }
    return random;
  }

  public int[][] splitOf(int[][] data, int index) {
    int[][] result = new int[2][data[0].length];
    for (int i = 0; i < 2; i++) {
      result[i] = data[index];
      index++;
    }
    return result;
  }

  public int[][] twiceOn(int[][] data) {
    int n = data.length * 2;
    int m = data[0].length;
    int[][] result = new int[n][m];
    int count = 0, index = 0;
    for (int i = 0; i < n; i++) {
      result[i] = data[index];
      count++;
      if (count > 1) {
        index++;
        count = 0;
      }
    }
    return result;
  }

  public int[][] joinOf(int[][] parent, int[][] child) {
    int n = parent.length * 2;
    int m = parent.length;
    int[][] result = new int[n][m];
    for (int i = 0; i < parent.length; i++) {
      result[i] = parent[i];
      // System.out.println(Arrays.toString(parent[i]));
    }
    int j = 0;
    for (int i = parent.length; i < n; i++) {
      result[i] = child[j];
      j++;
    }
    return result;
  }

  public List<List<Integer>> arrayToList(int[][] data) {
    List<List<Integer>> lists = new ArrayList<>();
    for (int[] ints : data) {
      List<Integer> list = new ArrayList<>();
      for (int i : ints) {
        list.add(i);
      }
      lists.add(list);
    }
    return lists;
  }

  public int[][] listToArray(List<List<Integer>> data) {
    int row = data.size();
    int column = data.get(0).size();
    int[][] result = new int[row][column];
    for (int i = 0; i < row; i++) {
      for (int j = 0; j < column; j++) {
        result[i][j] = data.get(i).get(j);
      }
    }
    return result;
  }

  public int[][] makeIndexOf(int[] data) {
    int n = data.length;
    int m = 2;
    int[][] result = new int[n][m];
    for (int i = 0; i < n; i++) {
      result[i][0] = i;
      result[i][1] = data[i];
    }
    return result;
  }

  public int[][] sortByValueOf(int[][] data) {
    HashMap<Integer, Integer> unSorted = new HashMap<>();
    LinkedHashMap<Integer, Integer> reverseSortedMap = new LinkedHashMap<>();
    for (int i = 0; i < data.length; i++) {
      unSorted.put(data[i][0], data[i][1]);
    }
    unSorted.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
        .forEachOrdered(x -> reverseSortedMap.put(x.getKey(), x.getValue()));
    int[][] result = new int[data.length][data[0].length];
    int index = 0;
    for (Map.Entry<Integer, Integer> sort : reverseSortedMap.entrySet()) {
      result[index][0] = sort.getKey();
      result[index][1] = sort.getValue();
      index++;
    }
    return result;
  }

  public int[][] dataOf(int[][] data, int[][] fitnessIndex) {
    int row = data.length;
    int column = data[0].length;
    int[][] result = new int[row][column];
    for (int i = 0; i < row; i++) {
      result[i] = data[fitnessIndex[i][0]];
    }
    return result;
  }
}

