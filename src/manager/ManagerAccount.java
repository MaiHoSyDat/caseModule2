package manager;

import account.Account;
import main.Main;


import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ManagerAccount {
    static File file = new File("Account.txt");
    public static List<Account> accounts = readAcc();
    Scanner scanner = new Scanner(System.in);

    public static void writeAcc(List<Account> accounts) {
        try {

            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            for (Account acc : accounts) {
                bw.write(acc.toString());
                bw.newLine();
            }
            bw.close();
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<Account> readAcc() {
        List<Account> accountList = new ArrayList<>();
        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String str = br.readLine();
            while (str != null) {
                String[] arr = str.split(",");
                String acc = arr[0];
                String pass = arr[1];
                String permission = arr[2];
                accountList.add(new Account(acc, pass, permission));
                str = br.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return accountList;
    }

    public String inputAcc() {

        System.out.print("Enter account (...@gmail.com): ");
        Pattern pattern = Pattern.compile("^[A-Z][A-Za-z0-9]*(@gmail\\.com)$");
        Matcher matcher = pattern.matcher(scanner.nextLine());
        if (matcher.matches()) {
            return matcher.group();
        } else {
            System.out.println("Invalid account format. Please try again.");
             inputAcc();
        }
        return "";
    }

    public String inputPass() {
        System.out.print("Enter password (at least 3 characters): ");

        Pattern pattern = Pattern.compile("^[A-Za-z0-9]{3,}$");
        Matcher matcher = pattern.matcher(scanner.nextLine());
        if (matcher.matches()) {
            return matcher.group();
        } else {
            System.out.println("Invalid account format. Please try again.");
             inputAcc();
        }
        return "";
    }

    public String inputPermission() {
        System.out.print("Enter permission: ");
        return scanner.nextLine();
    }

    public void addAcc() {
        String acc = inputAcc();
        if (checkAcc(acc) == -1) {
            accounts.add(new Account(acc, inputPass(), inputPermission()));
            writeAcc(accounts);
        } else {
            System.out.printf("%100s \n", "ACCOUNT ALREADY EXISTS!");
        }
    }

    public int checkAcc(String acc) {

        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).getAcc().equals(acc)) {
                return i;
            }
        }
        return -1;
    }

    public void logIn() {
        String acc = inputAcc();
        int index = checkAcc(acc);
        if (index != -1){
            if (accounts.get(index).getPass().equals(inputPass())) {
                System.out.printf("%100s \n", "LOGGED IN SUCCESSFULLY!");
                authority(index);
            } else {
                System.out.printf("%100s\n", "LOGIN FAILED!");
            }
        }else {
            System.out.printf("%100s\n", "LOGIN FAILED!");
        }
    }

    public void authority(int index) {
        Main main = new Main();

        Pattern pattern = Pattern.compile("[Aa][Dd][Mm][Ii][Nn]");
        Matcher matcher = pattern.matcher(accounts.get(index).getPermission());

        if (matcher.matches()) {
            main.admin();
        } else {
            main.user(accounts.get(index).getAcc());
        }
    }


}
