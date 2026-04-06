/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lca_02;

/**
 *
 * @author kriii
 */
import java.io.*;
import java.util.*;

//  Custom Exceptions

class InvalidCIDException extends Exception {
    public InvalidCIDException(String msg) {
        super(msg);
    }
}

class InvalidAmountException extends Exception {
    public InvalidAmountException(String msg) {
        super(msg);
    }
}

class InsufficientBalanceException extends Exception {
    public InsufficientBalanceException(String msg) {
        super(msg);
    }
}

//  Customer Class

class Customer {
    int cid;
    String cname;
    double amount;

    Customer(int cid, String cname, double amount) {
        this.cid = cid;
        this.cname = cname;
        this.amount = amount;
    }

    void display() {
        System.out.println("CID: " + cid);
        System.out.println("Name: " + cname);
        System.out.println("Balance: " + amount);
    }
}

//  Main Class

public class Ass4 {

    static Customer customer = null;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- Banking Menu ---");
            System.out.println("1. Create Account");
            System.out.println("2. Withdraw Amount");
            System.out.println("3. Display Details");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");

            choice = sc.nextInt();

            try {
                switch (choice) {

                    case 1:
                        createAccount(sc);
                        break;

                    case 2:
                        withdraw(sc);
                        break;

                    case 3:
                        display();
                        break;

                    case 4:
                        System.out.println("Exiting...");
                        break;

                    default:
                        System.out.println("Invalid choice");

                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }

        } while (choice != 4);
    }

    //  Create Account
    static void createAccount(Scanner sc)
            throws InvalidCIDException, InvalidAmountException, IOException {

        System.out.print("Enter CID (1-20): ");
        int cid = sc.nextInt();

        if (cid < 1 || cid > 20) {
            throw new InvalidCIDException("CID must be between 1 and 20");
        }

        System.out.print("Enter Name: ");
        String name = sc.next();

        System.out.print("Enter Initial Amount (>=1000): ");
        double amount = sc.nextDouble();

        if (amount < 1000) {
            throw new InvalidAmountException("Minimum balance is 1000");
        }

        customer = new Customer(cid, name, amount);

        //  File Writing
        BufferedWriter bw = new BufferedWriter(new FileWriter("customer.txt", true));
        bw.write(cid + " " + name + " " + amount);
        bw.newLine();
        bw.close();

        System.out.println("Account created successfully!");
    }

    //  Withdraw
    static void withdraw(Scanner sc)
            throws InsufficientBalanceException, InvalidAmountException {

        if (customer == null) {
            System.out.println("No account found!");
            return;
        }

        System.out.print("Enter withdrawal amount: ");
        double wth = sc.nextDouble();

        if (wth <= 0) {
            throw new InvalidAmountException("Amount must be positive");
        }

        if (wth > customer.amount) {
            throw new InsufficientBalanceException("Insufficient balance");
        }

        customer.amount -= wth;
        System.out.println("Withdrawal successful!");
    }

    //  Display
    static void display() {
        if (customer != null) {
            customer.display();
        } else {
            System.out.println("No account found!");
        }
    }
}
