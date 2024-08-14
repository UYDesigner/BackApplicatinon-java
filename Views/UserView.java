package Views;

import dao.DataDao;
import dao.UserDao;
import db.MyConnection;
import model.Data;
import model.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class UserView {

    String email;
    String password;

    public UserView(String email, String password) {
        this.email = email;
        this.password = password;
    }


    public void userScreen() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Welcome To The Backing App ");
        System.out.println("Press 1 to Open Account\nPress 2 to Check Account Details\nPress 3 to Transfer " +
                "Money\nPress" +
                " 4 to Check Credit Money\nPress 0 to Exit");
        int choice = 0;

        try {
            choice = Integer.parseInt(br.readLine());
            if (choice != 0 && choice != 1 && choice != 2 && choice != 3 && choice != 4) {
                System.out.println("Invalid Choice! Please enter 0, 1, or 2.");
                System.out.println();
                // Prompt user to try again
                userScreen();
                return;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        switch (choice) {
            case 1 -> openAccount();
            case 2 -> accountDetails();
            case 3 -> transferMoney();
            case 4 -> creditMoney();
            case 0 -> System.exit(0);
        }
    }

    public void transferMoney() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Sender Information--");
        System.out.println("Enter email of sender : ");
        String email1 = sc.nextLine();
        System.out.println("Enter Account number : ");
        String account_number1 = sc.nextLine();
        System.out.println("Enter Security pin : ");
        String security_pin1 = sc.nextLine();
        try {
            if (DataDao.accountExist(email1) == false) {
                System.out.println("Information of sender is incorrect...");
                userScreen();
            } else {
                System.out.println("Receiver Information--");
                System.out.println("Enter email of sender : ");
                String email2 = sc.nextLine();
                System.out.println("Enter Account number : ");
                String account_number2 = sc.nextLine();

                try {
                    if (!(DataDao.accountExist(email2))) {
                        System.out.println("Information of Receiver is incorrect...");
                        userScreen();
                    } else {

                        System.out.println("Enter Account to be transfered : ");
                        float amount = sc.nextFloat();
                        Data sender = new Data(email1, account_number1, security_pin1);
                        Data receiver = new Data(email2, account_number2);
                        int ans = DataDao.transactionMoney(sender , receiver, amount);
                        System.out.println(ans == 1 ? "transaction successful..." : " Problem hai bhai problem....");
                        userScreen();

                    }
                }
                catch (SQLException e)
                {
                    e.printStackTrace();
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void creditMoney() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter your email  : ");
        String email1 = sc.nextLine();
        System.out.println("Enter Account number : ");
        String account_number1 = sc.nextLine();
        System.out.println("Enter Security pin : ");
        String security_pin1 = sc.nextLine();
        try {
            if (DataDao.accountExist(email1) == false) {
                System.out.println("Information of is incorrect...");
                userScreen();
            } else {
                System.out.println("Enter Account to be credited : ");
                float amount = sc.nextFloat();
                Data sender = new Data(email1, account_number1, security_pin1);
                int ans = DataDao.creditMoney(sender, amount);
                System.out.println(ans == 1 ? "Money credited successful..." : " Problem hai bhai problem....");
                userScreen();

            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void openAccount() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter account number : ");
        String account_number = sc.nextLine();
        System.out.print("Enter Email : ");
        String email = sc.nextLine();
        System.out.print("Enter Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Security Pin : ");
        String security_pin = sc.nextLine();
        System.out.println("Enter account to set : ");
        float balance = sc.nextFloat();

        try {
            if (DataDao.accountExist(email) == false) {
                Data data = new Data(name, email, account_number, security_pin, balance);
                if (email.length() == 0 || name.length() == 0 || security_pin.length() == 0 || balance == 00.0) {
                    System.out.println("Fill correct information... ");
                    accountDetails();
                } else {
                    try {
                        int ans = DataDao.saveAccount(data);
                        System.out.println(ans == 1 ? "Account Created..." : "There is problem...");
                        userScreen();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }


            } else {

                System.out.println("this email is already used in another account");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public void accountDetails() {

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Email : ");
        String email = sc.nextLine();
        System.out.print("Enter Account Number: ");
        String account_number = sc.nextLine();
        System.out.print("Enter Security Pin : ");
        String security_pin = sc.nextLine();
        try {
            if (DataDao.accountExist(email) == false) {

                System.out.println("User does not exist.");
                userScreen();
            } else {

                Data data = new Data(email, account_number, security_pin);
                if (email.length() == 0 || account_number.length() == 0 || security_pin.length() == 0) {
                    System.out.println("Fill correct information... ");
                    accountDetails();
                } else {
                    try {
                        boolean ans = DataDao.fetchAccountDetails(data);
                        userScreen();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }


//
//

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
