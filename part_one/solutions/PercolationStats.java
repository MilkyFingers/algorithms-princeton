/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private double[] percFractions;

    public PercolationStats(int n, int trials) {
        percFractions = new double[trials];
        for (int i = 0; i < trials; i++) {
            Percolation perc = new Percolation(n);
            while (!perc.percolates()) {
                int x;
                int y;
                while (true) {
                    x = StdRandom.uniform(n);
                    y = StdRandom.uniform(n);
                    if (!perc.isOpen(x + 1, y + 1)) {
                        break;
                    }
                }
                perc.open(x + 1, y + 1);
            }
            percFractions[i] = perc.numberOfOpenSites() / ((n * n) / 1.0);
        }
    }

    public double mean() {
        return StdStats.mean(percFractions);
    }

    public double stddev() {
        return StdStats.stddev(percFractions);
    }

    public double confidenceLo() {
        return mean() - ((1.96 * stddev()) / Math.sqrt(percFractions.length));
    }

    public double confidenceHi() {
        return mean() + ((1.96 * stddev()) / Math.sqrt(percFractions.length));
    }

    public static void main(String[] args) {
        System.out.println("Enter a grid size followed by trial number.");
        int n = Integer.parseInt(args[0]);
        int t = Integer.parseInt(args[1]);
        PercolationStats percer = new PercolationStats(n, t);
        System.out.println(String.format("Mean  :   %1.16f", percer.mean()));
        System.out.println(String.format("sdev  :   %1.16f", percer.stddev()));
        System.out.println(String.format("95 Lo :   %1.16f", percer.confidenceLo()));
        System.out.println(String.format("95 Hi :   %1.16f", percer.confidenceHi()));
    }
}
