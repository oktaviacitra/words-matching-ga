
public class GeneticAlgorithm {
    public GeneticAlgorithm() {

    }

    public int differenceBetween(int[] data, int[] target) {
        int sum = 0;
        for (int i = 0; i < target.length; i++) {
            sum += Math.abs(data[i] - target[i]);
        }
        return sum;
    }

    public int[] fitnessOfAll(int[][] data, int[] target) {
        int n = data.length;
        int charSize = data[0].length;
        int[] fitness = new int[n];
        for (int i = 0; i < n; i++) {
            fitness[i] = (26 * charSize) - differenceBetween(data[i], target);
        }
        return fitness;
    }

    public int MAXIndexOf(int[] fitness) {
        int index = 0;
        int value = fitness[0];
        for (int i = 0; i < fitness.length; i++) {
            if (value < fitness[i]) {
                value = fitness[i];
                index = i;
            }
        }
        return index;
    }

    public int[][] makeRouletteValueFrom(int[] fitness) {
        int n = fitness.length;
        int m = 2;
        int[][] value = new int[n][m];
        int currentRange = 0;
        for (int i = 0; i < n; i++) {
            value[i][0] = currentRange + 1;
            value[i][1] = currentRange + fitness[i];
            currentRange += fitness[i];
        }
        // for (int i = 0; i < n; i++) {
        // if (i == 0) {
        // value[i][0] = 0;
        // value[i][1] = fitness[i];
        // } else {
        // value[i][0] = value[i-1][1] + 1;
        // value[i][1] = value[i][0] + fitness[i] - 1;
        // }
        // }
        return value;
    }

    public int[] playRouletteMachineUsing(int[][] value) {
        int n = value.length;
        int[] selection = new int[n];
        Service service = new Service();
        int[] random = new int[n];
        // int[] index = service.randomDistinctIn(n);
        // for (int i = 0; i < index.length; i++) {
        // System.out.println(index[i] + " " + value[index[i]][0] + " " +
        // value[index[i]][1]);
        // }
        // for (int i = 0; i < n; i++) {
        // random[i] = service.randomIntIn(value[index[i]][0], value[index[i]][1]);
        // System.out.println(random[i]);
        // }
        for (int i = 0; i < n; i++) {
            random[i] = service.randomIntIn(value[0][0] + 1, value[value.length - 1][1]);
            if (i % 2 == 1 && i != 0 && random[i] == random[i - 1]) {
                do {
                    random[i] = service.randomIntIn(value[0][0] + 1, value[value.length - 1][1]);
                } while (random[i] == random[i - 1]);
            }
        }
        for (int i = 0; i < n; i++) {
            // if (random[i] >= value[i][0] && random[i] <= value[i][1]) {
            // selection[i] = i;
            // }
            for (int j = 0; j < n; j++) {
                if (random[i] >= value[j][0] && random[i] <= value[j][1]) {
                    selection[i] = j;
                }
            }
        }
        return selection;
    }

    public int[][] makeOffspringBasedOn(int[] selection, int[][] dataInt) {
        int n = dataInt.length;
        int m = dataInt[0].length;
        int[][] offspring = new int[n][m];
        for (int i = 0; i < n; i++) {
            // offspring[i] = dataInt[selection[i]];
            int[] temp = new int[m];
            for (int j = 0; j < m; j++) {
                temp[j] = dataInt[selection[i]][j];
            }
            offspring[i] = temp;
        }
        return offspring;
    }

    public int[][] crossOverIn(int[][] offspring, int[][] range) {
        int n = offspring.length;
        int m = offspring[0].length;
        Service service = new Service();
        double probability = 0.75;
        int[][] crossOver = new int[n][m];
        int i = 0, j = 0, index = 0;
        while (i < n) {
            int[][] couple = service.splitOf(offspring, i);
            double prediction = service.randomFloatIn(0, 1);
            if (prediction < probability) {
                int[][] genSelection = service.partOf(offspring, range[j]);
                genSelection = service.swap(genSelection[0], genSelection[1]);
                couple = service.oneOf(couple, genSelection, range[j]);
            }
            crossOver[index] = couple[0];
            index++;
            crossOver[index] = couple[1];
            index++;
            j++;
            i += 2;
        }
        return crossOver;
    }

    public int[][] rangeIndexOf(int[][] crossOver) {
        int n = crossOver.length;
        int row = n / 2;
        int column = 2;
        int[][] range = new int[row][column];
        Service service = new Service();
        for (int i = 0; i < row; i++) {
            do {
                range[i][0] = service.randomIntIn(0, (n - 2));
                do {
                    range[i][1] = service.randomIntIn(1, (n - 1));
                } while (range[i][0] == range[i][1]);
            } while (range[i][0] > range[i][1]);
        }
        return range;
    }

    public boolean decisionOf(double probability) {
        Service service = new Service();
        double prediction = service.randomFloatIn(0, 1);
        boolean decision = false;
        if (prediction < probability) {
            decision = true;
        }
        return decision;
    }

    // public double[] percentOfAll(int[] fitness, int total) {
    // int n = fitness.length;
    // double[] percent = new double[n];
    // for (int i = 0; i < n; i++) {
    // percent[i] = (Double.valueOf(fitness[i]) / Double.valueOf(total)) * 100;
    // }
    // return percent;
    // }

    // public double[][] rouletteMachineFrom(double[] percent) {
    // int n = percent.length;
    // double[][] roulette = new double[n][2];
    // for (int i = 0; i < n; i++) {
    // if (i == 0) {
    // roulette[i][0] = 0;
    // roulette[i][1] = percent[i];
    // } else {
    // roulette[i][0] = roulette[i-1][1] + 1;
    // roulette[i][1] = roulette[i][0] + percent[i] - 1;
    // }
    // }
    // return roulette;
    // }

    // public int[] selectionUsing(double[][] roulette) {
    // int n = roulette.length;
    // Service service = new Service();
    // int[] random = new int[n];
    // for (int i = 0; i < n; i++) {
    // random[i] = service.randomIntIn(0, 100);
    // }
    // int[] selection = new int[n];
    // for (int i = 0; i < n; i++) {
    // if (random[i] >= roulette[i][0] && random[i] <= roulette[i][1]) {
    // selection[i] = i;
    // }
    // }
    // return selection;
    // }

    // public int[][] makeCoupleOf(int[] selection) {
    // int n = selection.length / 2;
    // int m = 2;
    // int[][] couple = new int[n][m];
    // int count = 0;
    // for (int i = 0; i < n; i++) {
    // for (int j = 0; j < m; j++) {
    // couple[i][j] = selection[count];
    // count++;
    // }
    // }
    // return couple;
    // }

    // public boolean decisionOf(int[] couple, double probability) {
    // Service service = new Service();
    // double prediction = service.randomFloatIn(0, 1);
    // boolean decision = false;
    // if (prediction < probability) {
    // decision = true;
    // }
    // return decision;
    // }

    // public int[][] parentFrom(int[][] couple, int[][] dataInt) {
    // int[][] parent = new int[dataInt.length][dataInt[0].length];
    // int row = 0;
    // for (int i = 0; i < couple.length; i++) {
    // for (int j = 0; j < couple[i].length; j++) {
    // parent[row] = dataInt[couple[i][j]];
    // row++;
    // }
    // }
    // return parent;
    // }

    // public int[][][] crossingOverTo(int[][] couple, int[][] dataInt) {
    // Service service = new Service();
    // int n = couple.length;
    // int m = couple[0].length;
    // int genSize = dataInt[0].length;
    // int[][][] crossOver = new int[n][m][genSize];
    // for (int i = 0; i < n; i++) {
    // for (int j = 0; j < m; j++) {
    // crossOver[i][j] = dataInt[couple[i][j]];
    // }
    // int[][] dataCouple = new int[m][genSize];
    // double probability = service.randomFloatIn(0, 1);
    // if (decisionOf(couple[i], probability)) {
    // int[][] temp = new int[m][genSize];
    // for (int j = 0; j < m; j++) {
    // temp[j] = crossOver[i][j];
    // }
    // int left, right;
    // do {
    // left = service.randomIntIn(0, genSize - 2);
    // right = service.randomIntIn(1, genSize - 1);
    // } while (left > right);
    // for (int j = left; j <= right; j++) {
    // dataCouple[0][j] = temp[1][j];
    // dataCouple[1][j] = temp[0][j];
    // }
    // crossOver[i] = dataCouple;
    // }
    // }
    // return crossOver;
    // }

    public int[][] offSpringFrom(int[][][] crossOver) {
        int n = crossOver.length * crossOver[0].length;
        int m = crossOver[0][0].length;
        int[][] offSpring = new int[n][m];
        int row = 0, column = 0;
        for (int i = 0; i < crossOver.length; i++) {
            for (int j = 0; j < crossOver[0].length; j++) {
                column = 0;
                for (int k = 0; k < crossOver[0][0].length; k++) {
                    offSpring[row][column] = crossOver[i][j][k];
                    column++;
                }
                row++;
            }
        }
        return offSpring;
    }

    public int[][] mutationOf(int[][] individual) {
        int[][] mutation = individual;
        Service service = new Service();
        double probability = 0.80;
        for (int i = 0; i < mutation.length; i++) {
            double prediction = service.randomFloatIn(0, 1);
            if (prediction < probability) {
                int randomIndex = service.randomIntIn(0, mutation[i].length - 1);
                mutation[i][randomIndex] = service.randomIntIn(1, 26);
            }
        }
        return mutation;
    }

    public int[][] elitismOf(int[][] data) {
        int row = data.length / 2;
        int column = data[0].length;
        int[][] result = new int[row][column];
        for (int i = 0; i < row; i++) {
            result[i] = data[i];
        }
        return result;
    }
}