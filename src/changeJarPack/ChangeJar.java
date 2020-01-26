package changeJarPack;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;

/**
 *
 * The purpose this class is to simulate a change Jar.
 *
 * @RicardoHerrera-Santos&Larry
 */

public class ChangeJar {


    static boolean mutate = true;

    /**
     * The number of quarters in the current Jar
     */
    private int quarters;

    /**
     * The number of dimes in the current Jar
     */
    private int dimes;

    /**
     * The number of nickels in the current Jar
     */
    private int nickels;

    /**
     * The number of pennies in the current Jar
     */
    private int pennies;

    /******************************************************************
     *  The is the default constructor for ChangeJar
     */
    public ChangeJar() {
        quarters = 0;
        dimes = 0;
        nickels = 0;
        pennies = 0;
    }

    public ChangeJar(int quarters, int dimes, int nickels, int pennies) {
        this.check();
        this.quarters = quarters;
        this.dimes = dimes;
        this.nickels = nickels;
        this.pennies = pennies;
    }

    public ChangeJar(double amount) {
        if (amount < 0 || BigDecimal.valueOf(amount).scale() > 2) {
            throw new IllegalArgumentException();
        }
        int num = (int) Math.round(amount * 100);
        quarters = num / 25;
        dimes = (num % 25) / 10;
        nickels = (num % 25) % 10 / 5;
        pennies = num % 5;
    }

    /******************************************************************
     *
     *   This constructor creates a Change Jar from an existing
     *   Change Jar.
     *
     * @param other is an existing Change Jar
     */
    public ChangeJar(ChangeJar other) {
        quarters = other.quarters;
        dimes = other.dimes;
        nickels = other.nickels;
        pennies = other.pennies;
    }

    public ChangeJar(String amount) {
        this(Double.parseDouble(amount));
    }

    public boolean equals(Object other) {
        if (other != null) {
            if (other instanceof ChangeJar) {
                ChangeJar temp = (ChangeJar) other;
                return this.quarters == temp.quarters && this.dimes == temp.quarters &&
                        this.nickels == temp.nickels && this.pennies == temp.pennies;
            }
        }
        return false;
    }

    public static boolean equals(ChangeJar jar1, ChangeJar jar2) {
        return jar1.getAmount() == jar2.getAmount();
    }

    public int compareTo(ChangeJar other) {
        if (this.getAmount() > other.getAmount())
            return 1;
        else if (this.getAmount() < other.getAmount())
            return -1;
        else
            return 0;
    }

    public static int compareTo(ChangeJar jar1, ChangeJar jar2) {
        if (jar1.getAmount() > jar2.getAmount())
            return 1;
        else if (jar1.getAmount() < jar2.getAmount())
            return -1;
        else
            return 0;
    }

    public void takeout(int quarters, int dimes, int nickels, int pennies) {
        if (mutate == true) {
            if (quarters > this.quarters || dimes > this.nickels ||
                    dimes > this.dimes || pennies > this.pennies ||
                    quarters < 0 || nickels < 0 || dimes < 0 || pennies < 0)
                throw new IllegalArgumentException();

            this.quarters -= quarters;
            this.dimes -= dimes;
            this.nickels -= nickels;
            this.pennies -= pennies;
        }
    }

    public void takeout(ChangeJar other) {
        if (mutate == true) {
            if (other.getQuarters() > this.getQuarters() || other.getDimes() > this.getDimes() ||
                    other.getNickels() > this.getNickels() || other.getPennies() > this.getPennies())
                throw new IllegalArgumentException();

            quarters -= other.getQuarters();
            dimes -= other.getDimes();
            nickels -= other.getNickels();
            pennies -= other.getPennies();
        }
    }

    public void dec() {
        if (mutate == true) {
            if (pennies >= 0) {
                pennies--;
            } else {
                throw new IllegalArgumentException();
            }
        }
    }

    public void add(int quarters, int dimes, int nickels, int pennies) {
        if (mutate == true) {
            this.check();
            this.quarters += quarters;
            this.dimes += dimes;
            this.nickels += nickels;
            this.pennies += pennies;
        }
    }

    public void add(ChangeJar other) {
        if (mutate == true) {
            this.quarters = other.quarters;
            this.dimes = other.dimes;
            this.nickels = other.nickels;
            this.pennies = other.pennies;
        }
    }

    public void inc() {
        if (mutate == true) {
            pennies++;
        }
    }

    private static int convertToPennies(ChangeJar temp) {
        return (temp.quarters * 25) + (temp.dimes * 10) + (temp.nickels * 5) + temp.pennies;
    }

    public static void mutate(boolean on) {
        mutate = on;
    }

    public ChangeJar takeout(double amount) {
        ChangeJar t = new ChangeJar();
        boolean solveable = false;
        for (int q = 0; q <= quarters; q++) {
            for (int d = 0; d <= dimes; d++) {
                for (int n = 0; n <= nickels; n++) {
                    for (int p = 0; p <= pennies; p++) {
                        if ((25 * q) + (10 * d) + (5 * n) + p == amount) {
                            quarters -= q;
                            dimes -= d;
                            nickels -= n;
                            pennies -= p;
                            t.quarters = q;
                            t.dimes = d;
                            t.nickels = n;
                            t.pennies = p;
                            solveable = true;
                            return t;
                        }
                    }
                }
            }
        }
        if (solveable == false) {
            throw new IllegalArgumentException();
        }
        return null;
    }



    public String toString() {
        String s = this.quarters + " Quarter" + ((quarters != 1) ? "s" : "") + "\n";
        s = s + this.dimes + " Dime" + ((dimes != 1) ? "s" : "") + "\n";
        s = s + this.nickels + " Nickel" + ((nickels != 1) ? "s" : "") + "\n";
        s = s + this.pennies + " Penny" + ((pennies != 1) ? "ies" : "") + "\n";
        return s;
    }

    public void save(String fileName) {

        PrintWriter out = null;
        try {
            out = new PrintWriter(new BufferedWriter(new FileWriter(
                    fileName)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        out.close();
    }


    public void load(String fileName) {

        Scanner scanner = null;
        try {
            scanner = new Scanner(new File(fileName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public double getAmount () {
        return convertToPennies(this) / 100.0;
    }


    public static void main(String[] args) {

    }

    public int getQuarters() {
        return quarters;
    }

    public void setQuarters(int quarters) {
        this.quarters = quarters;
    }

    public int getDimes() {
        return dimes;
    }

    public void setDimes(int dimes) {
        this.dimes = dimes;
    }

    public int getNickels() {
        return nickels;
    }

    public void setNickels(int nickels) {
        this.nickels = nickels;
    }

    public int getPennies() {
        return pennies;
    }

    public void setPennies(int pennies) {
        this.pennies = pennies;
    }

    public void check(){
        if (quarters<0||nickels<0||dimes<0||pennies<0){
            throw new IllegalArgumentException();
        }
    }
}