package changeJarPack;

import javax.naming.Context;
import java.io.*;
import java.math.BigDecimal;
import java.util.*;

/*****************************************************************
 * The purpose this class is to simulate a ChangeJar
 *
 * @author Ricardo Herrera-Santos & Larry Goto
 * @version Winter 2020
 *****************************************************************/
public class ChangeJar {

    /** The number of quarters in the current Jar */
    private int quarters;

    /** The number of dimes in the current Jar */
    private int dimes;

    /** The number of nickels in the current Jar */
    private int nickels;

    /** The number of pennies in the current Jar */
    private int pennies;

    /** Allows elements to be edited */
    static boolean MUTATE = true;

    /******************************************************************
     * This is the default constructor for ChangeJar
     ******************************************************************/
    public ChangeJar() {
        quarters = 0;
        dimes = 0;
        nickels = 0;
        pennies = 0;
    }

    /******************************************************************
     * This constructor creates a ChangeJar of specified
     * quarters, dimes, nickels, and pennies
     *
     * @param quarters 25-cent pieces in ChangeJar
     * @param dimes 10-cent pieces in ChangeJar
     * @param nickels 5-cent pieces in ChangeJar
     * @param pennies 1-cent pieces in ChangeJar
     * @throws IllegalArgumentException when params are negative
     ******************************************************************/
    public ChangeJar(int quarters, int dimes, int nickels, int pennies) {
        if(quarters < 0 || dimes < 0 || nickels < 0 || pennies < 0){
            throw new IllegalArgumentException();
        }
        this.quarters = quarters;
        this.dimes = dimes;
        this.nickels = nickels;
        this.pennies = pennies;
    }

    /******************************************************************
     * This constructor creates a ChangeJar from a given
     * positive double value
     *
     * @param amount total value of ChangeJar
     * @throws IllegalArgumentException when amount is negative or
     *         exceeds three decimal places
     ******************************************************************/
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
     * This constructor creates a ChangeJar from an existing
     * ChangeJar
     *
     * @param other is an existing ChangeJar
     * @throws IllegalArgumentException if ChangeJar other instance
     *         variables are negative
     ******************************************************************/
    public ChangeJar(ChangeJar other) {
        if(other.quarters < 0 || other.dimes < 0 ||
                other.nickels < 0 || other.pennies < 0){
            throw new IllegalArgumentException();
        }
        quarters = other.quarters;
        dimes = other.dimes;
        nickels = other.nickels;
        pennies = other.pennies;
    }

    /******************************************************************
     * This constructor creates a ChangeJar from a string value
     *
     * @param amount total value of ChangeJar
     ******************************************************************/
    public ChangeJar(String amount) {
        this(Double.parseDouble(amount));
    }

    /******************************************************************
     * Boolean method that returns true if the object's instance
     * variables are equal
     *
     * @param other object being compared to "this" ChangeJar
     * @return boolean for equivalent ChangeJars
     ******************************************************************/
    public boolean equals(Object other) {
        if (other != null) {
            if (other instanceof ChangeJar) {
                ChangeJar temp = (ChangeJar) other;
                return this.quarters == temp.quarters && this.dimes == temp.dimes &&
                        this.nickels == temp.nickels && this.pennies == temp.pennies;
            }
        }
        return false;
    }

    /******************************************************************
     * Class method that checks if two given ChangeJars are equal
     *
     * @param jar1 first ChangeJar being compared to second
     * @param jar2 second ChangeJar being compared to first
     * @return true if jar1 and jar2 are equal
     ******************************************************************/
    public static boolean equals(ChangeJar jar1, ChangeJar jar2) {
        return jar1.quarters == jar2.quarters && jar1.dimes == jar2.dimes &&
                jar1.nickels == jar2.nickels && jar1.pennies == jar2.pennies;
    }

    /******************************************************************
     * Method that compares the amount inside of a ChangeJar
     * to "this" ChangeJar
     *
     * @param other object being compared to "this" ChangeJar
     * @return integer value specifying if "this" ChangeJar is greater
     *         than, less than, or equal to other ChangeJar
     ******************************************************************/
    public int compareTo(ChangeJar other) {
        if (this.getAmount() > other.getAmount())
            return 1;
        else if (this.getAmount() < other.getAmount())
            return -1;
        else
            return 0;
    }

    /******************************************************************
     * Class method that compares the amounts inside of two ChangeJars
     *
     * @param jar1 first ChangeJar being compared to second
     * @param jar2 second ChangeJar being compared to first
     * @return integer value specifying if jar1 is greater
     *         than, less than, or equal to jar2
     ******************************************************************/
    public static int compareTo(ChangeJar jar1, ChangeJar jar2) {
        if (jar1.getAmount() > jar2.getAmount())
            return 1;
        else if (jar1.getAmount() < jar2.getAmount())
            return -1;
        else
            return 0;
    }

    /******************************************************************
     * Method that removes given quarters, dimes, nickels, and pennies
     * from a ChangeJar
     *
     * @param quarters 25-cent pieces to be taken out of ChangeJar
     * @param dimes 10-cent pieces to be taken out of ChangeJar
     * @param nickels 5-cent pieces to be taken out of ChangeJar
     * @param pennies 1-cent pieces to be taken out of ChangeJar
     * @throws IllegalArgumentException if params are negative or
     *         less than instance variables in given ChangeJar
     ******************************************************************/
    public void takeout(int quarters, int dimes, int nickels, int pennies) {
        if (MUTATE) {
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

    /******************************************************************
     * Method that removes coins from ChangeJar based on amounts
     * in another ChangeJar
     *
     * @param other ChangeJar being "removed" from "this" ChangeJar
     * @throws IllegalArgumentException if other ChangeJar instance
     *         variables exceed "this" ChangeJar's variables
     ******************************************************************/
    public void takeout(ChangeJar other) {
        if (MUTATE) {
            if (other.quarters > this.quarters || other.dimes > this.dimes ||
                    other.nickels > this.nickels || other.pennies > this.pennies ||
                    other.quarters < 0 || other.dimes < 0 ||
                    other.nickels < 0 || other.pennies < 0) {
                throw new IllegalArgumentException();
            }

            quarters -= other.getQuarters();
            dimes -= other.getDimes();
            nickels -= other.getNickels();
            pennies -= other.getPennies();
        }
    }

    /******************************************************************
     * Method that decreases ChangeJar by 1 penny if possible
     ******************************************************************/
    public void dec() {
        if (MUTATE) {
            if (pennies < 1) {
                throw new IllegalArgumentException();
            }
            pennies--;
        }
    }


    /******************************************************************
     * Method that increases a ChangeJar's values by given quarters,
     * dimes, nickels, and pennies
     *
     * @param quarters 25-cent pieces being added to ChangeJar
     * @param dimes 10-cent pieces being added to ChangeJar
     * @param nickels 5-cent pieces being added to ChangeJar
     * @param pennies 1-cent pieces being added to ChangeJar
     * @throws IllegalArgumentException if any params are negative
     ******************************************************************/
    public void add(int quarters, int dimes, int nickels, int pennies) {
        if (MUTATE) {
            if (quarters < 0 || dimes < 0 || nickels < 0 || pennies < 0){
                throw new IllegalArgumentException();
            }
            this.quarters += quarters;
            this.dimes += dimes;
            this.nickels += nickels;
            this.pennies += pennies;
        }
    }

    /******************************************************************
     * Method that increases a ChangeJar's values given another
     * ChangeJar's values
     *
     * @param other ChangeJar being added to "this" ChangeJar
     * @throws IllegalArgumentException if ChangeJar values are
     *         negative
     ******************************************************************/
    public void add(ChangeJar other) {
        if (MUTATE) {
            if (other.quarters < 0 || other.dimes < 0 || other.nickels < 0 || other.pennies < 0){
                throw new IllegalArgumentException();
            }
            this.quarters = other.quarters;
            this.dimes = other.dimes;
            this.nickels = other.nickels;
            this.pennies = other.pennies;
        }
    }

    /******************************************************************
     * Method that increases ChangeJar by 1 penny if possible
     ******************************************************************/
    public void inc() {
        if (MUTATE) {
            pennies++;
        }
    }

    /******************************************************************
     * Method that converts the values in a ChangeJar into cents
     *
     * @param temp ChangeJar being presented as pennies
     ******************************************************************/
    private static int convertToPennies(ChangeJar temp) {
        return (temp.quarters * 25) + (temp.dimes * 10) + (temp.nickels * 5) + temp.pennies;
    }

    /******************************************************************
     * Method that prevents ChangeJars from being edited
     ******************************************************************/
    public static void mutate(boolean on) {
        MUTATE = on;
    }

    /******************************************************************
     * Method that creates a ChangeJar by subtracting from an
     * existing ChangeJar
     *
     * @param amount dollar value to be subtracted from one ChangeJar
     *        and added to a new one
     * @return ChangeJar if possible from given params
     * @throws IllegalArgumentException when amount is negative,
     *         exceeds 3 decimal places
     ******************************************************************/
    public ChangeJar takeout(double amount) {
        if ((amount < 0 || BigDecimal.valueOf(amount).scale() > 2)) {
            throw new IllegalArgumentException();
        }
        if (MUTATE){
            ChangeJar t = new ChangeJar();
            int tempAmount = (int) Math.round(amount * 100);

            for (int p = 0; p <= this.pennies; p++) {
                for (int n = 0; n <= this.nickels; n++) {
                    for (int d = 0; d <= this.dimes; d++) {
                        for (int q = 0; q <= this.quarters; q++) {
                            if ((25 * q) + (10 * d) + (5 * n) + p == tempAmount) {
                                t.quarters = q;
                                t.dimes = d;
                                t.nickels = n;
                                t.pennies = p;
                                takeout(t);
                                return t;
                            }
                        }
                    }
                }
            }
        }
        throw new IllegalArgumentException();
    }

    /******************************************************************
     * Method that prints out elements inside of ChangeJar class
     *
     * @return quarters, dimes, nickels, and pennies in a ChangeJar
     ******************************************************************/
    public String toString() {
        String s = this.quarters + " Quarter" + ((quarters != 1) ? "s" : "") + "\n";
        s = s + this.dimes + " Dime" + ((dimes != 1) ? "s" : "") + "\n";
        s = s + this.nickels + " Nickel" + ((nickels != 1) ? "s" : "") + "\n";
        s = s + this.pennies + " Penny" + ((pennies != 1) ? s.replace("y", "") + "ies" : "") + "\n";
        return s;
    }

    /******************************************************************
     * Saves ChangeJars to text file
     *
     * @param fileName name for the to be created text file
     ******************************************************************/
    public void save(String fileName) {

        PrintWriter out = null;
        try {
            out = new PrintWriter(new BufferedWriter(new FileWriter(fileName)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        out.println(quarters);
        out.println(dimes);
        out.println(nickels);
        out.println(pennies);
        out.close();
    }

    /******************************************************************
     * Loads ChangeJars from text files
     *
     * @param fileName name of the file to be opened
     * @throws FileNotFoundException if file not found
     ******************************************************************/
    public void load(String fileName){

        int quarters;
        int dimes;
        int nickels;
        int pennies;

        try {
            Scanner scanner = new Scanner(new File(fileName));
            quarters = scanner.nextInt();
            dimes = scanner.nextInt();
            nickels = scanner.nextInt();
            pennies = scanner.nextInt();
            if(quarters < 0 || dimes < 0 || nickels < 0 || pennies < 0) {
                throw new IllegalArgumentException();
            } else {
                this.quarters = quarters;
                this.dimes = dimes;
                this.nickels = nickels;
                this.pennies = pennies;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /******************************************************************
     * Method that gets total amount of coins in ChangeJar
     *
     * @return amount in ChangeJar
     ******************************************************************/
    public double getAmount () {
        return convertToPennies(this) / 100.0;
    }

    /******************************************************************
     * Method that gets total number of quarters in ChangeJar
     *
     * @return quarters in ChangeJar
     ******************************************************************/
    public int getQuarters() {
        return quarters;
    }

    /******************************************************************
     * Method that sets total number of quarters in ChangeJar
     ******************************************************************/
    public void setQuarters(int quarters) {
        this.quarters = quarters;
    }

    /******************************************************************
     * Method that gets total number of dimes in ChangeJar
     *
     * @return dimes in ChangeJar
     ******************************************************************/
    public int getDimes() {
        return dimes;
    }

    /******************************************************************
     * Method that sets total number of dimes in ChangeJar
     ******************************************************************/
    public void setDimes(int dimes) {
        this.dimes = dimes;
    }

    /******************************************************************
     * Method that gets total number of nickels in ChangeJar
     *
     * @return nickels in ChangeJar
     ******************************************************************/
    public int getNickels() {
        return nickels;
    }

    /******************************************************************
     * Method that sets total number of nickels in ChangeJar
     ******************************************************************/
    public void setNickels(int nickels) {
        this.nickels = nickels;
    }

    /******************************************************************
     * Method that gets total number of pennies in ChangeJar
     *
     * @return pennies in ChangeJar
     ******************************************************************/
    public int getPennies() {
        return pennies;
    }

    /******************************************************************
     * Method that sets total number of pennies in ChangeJar
     ******************************************************************/
    public void setPennies(int pennies) {
        this.pennies = pennies;
    }
}