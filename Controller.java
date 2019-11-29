import java.util.Scanner;

public class Controller {
    public Controller() {

    }

    public void printGeneral2(int[][][] data) {
        System.out.print("\n");
        for (int i = 0; i < data.length; i++) {
            System.out.print(i + "\n");
            for (int j = 0; j < data[i].length; j++) {
                for (int k = 0; k < data[i][j].length; k++) {
                    System.out.print(data[i][j][k] + " ");
                }
                System.out.print("\n");
            }
            System.out.print("\n");
        }
        System.out.print("\n");
    }

    public void printGeneral(char[][] data) {
        System.out.print("\n");
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                System.out.print(data[i][j] + " ");
            }
            System.out.print("\n");
        }
        System.out.print("\n");
    }

    public void printOffspring(char[][] offspringChar, int[][] offspringInt, int[] selection) {
        System.out.print("\nOFFSPRING\n");
        for (int i = 0; i < offspringInt.length; i++) {
            System.out.print("[" + (selection[i] + 1) + "]\t");
            for (int j = 0; j < offspringInt[i].length; j++) {
                System.out.print(offspringChar[i][j] + ":" + offspringInt[i][j] + "\t");
            }
            System.out.print("\t" + String.valueOf(offspringChar[i]) + "\n");
        }
    }

    public void printParent(char[][] parentChar, int[][] parentInt) {
        System.out.print("\n");
        for (int i = 0; i < parentInt.length; i++) {
            for (int j = 0; j < parentInt[i].length; j++) {
                System.out.print(parentChar[i][j] + ":" + parentInt[i][j] + "\t");
            }
            System.out.print("\n");
        }
    }

    public void printData(char[][] dataChar, int[][] dataInt, String[] dataString, String title) {
        System.out.print("\n" + title + "\n");
        for (int i = 0; i < dataChar.length; i++) {
            System.out.print("[" + (i + 1) + "]\t");
            for (int j = 0; j < dataChar[i].length; j++) {
                System.out.print(dataChar[i][j] + ":" + dataInt[i][j] + "\t");
            }
            System.out.print("\t" + dataString[i] + "\n");
        }
        System.out.print("\n");
    }

    public void printCrossOver(char[][] dataChar, int[][] dataInt, String[] dataString, String[] range) {
        System.out.print("\nCROSS OVER\n");
        for (int i = 0; i < dataChar.length; i++) {
            System.out.print("[" + (i + 1) + "]\t");
            for (int j = 0; j < dataChar[i].length; j++) {
                System.out.print(dataChar[i][j] + ":" + dataInt[i][j] + "\t");
            }
            System.out.print("\t" + dataString[i] + "\t\t" + range[i] + "\n");
        }
        System.out.print("\n");
    }

    public void printFitness(char[][] dataChar, int[][] dataInt, String[] dataString, int[] fitness, int index) {
        System.out.print("\nFITNESS\n");
        for (int i = 0; i < dataChar.length; i++) {
            System.out.print("[" + (i + 1) + "]\t");
            for (int j = 0; j < dataChar[i].length; j++) {
                System.out.print(dataChar[i][j] + ":" + dataInt[i][j] + "\t");
            }
            System.out.print("\t" + dataString[i] + "\t\t" + fitness[i]);
            if (i == index) {
                System.out.print(" --> MAX VALUE");
            }
            System.out.print("\n");
        }
        System.out.print("\n");
    }

    public void printJoinFitness(char[][] dataChar, int[][] dataInt, String[] dataString, int[] fitness) {
        System.out.print("\nFITNESS\n");
        for (int i = 0; i < dataChar.length; i++) {
            System.out.print("[" + (i + 1) + "]\t");
            for (int j = 0; j < dataChar[i].length; j++) {
                System.out.print(dataChar[i][j] + ":" + dataInt[i][j] + "\t");
            }
            System.out.print("\t" + dataString[i] + "\t\t" + fitness[i] + "\n");
        }
        System.out.print("\n");
    }

    public void printTarget(char[] targetChar, int[] targetInt) {
        System.out.print("\n[T]\t");
        for (int i = 0; i < targetInt.length; i++) {
            System.out.print(targetChar[i] + ":" + targetInt[i] + "\t");
        }
        System.out.print("\t" + String.valueOf(targetChar) + "\n\n");
    }

    public void printIndividual(char[][] individualChar, int[][] individualInt, char[][] offspringChar,
            int[][] offspringInt) {
        System.out.print("\n");
        for (int i = 0; i < individualInt.length; i++) {
            for (int j = 0; j < individualInt[i].length; j++) {
                System.out.print(individualChar[i][j] + ":" + individualInt[i][j] + "\t");
            }
            System.out.print("\t");
            for (int j = 0; j < offspringInt[0].length; j++) {
                System.out.print(offspringChar[i][j] + ":" + offspringInt[i][j] + "\t");
            }
            System.out.print("\n");
        }
        System.out.print("\n");
    }

    public String[] stringArrayOf(int[][] data) {
        int n = data.length;
        int m = data[0].length;
        String[] result = new String[n];
        String temp = "";
        for (int i = 0; i < n; i++) {
            temp = "";
            for (int j = 0; j < m; j++) {
                temp += (String.valueOf(data[i][j]) + " ");
            }
            result[i] = temp;
        }
        return result;
    }

    public int inputTotalPopulation() {
        Scanner scanner = new Scanner(System.in);
        int n;
        do {
            System.out.print("Input total of population\t: ");
            n = scanner.nextInt();
        } while (n % 2 != 0);
        return n;
    }

    public String inputTarget() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Input the word for testing\t: ");
        String data = scanner.nextLine();
        return data;
    }
}