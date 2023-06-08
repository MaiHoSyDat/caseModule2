package main;

import manager.ManagerAccount;
import manager.ManagerProduct;

import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice = -1;
        ManagerAccount account = new ManagerAccount();
        while (true) {

            try {
                System.out.println("1. Register!");
                System.out.println("2. Log In!");
                System.out.print("Enter choice: ");
                choice = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.out.println("__?__Retype!____");
            }
            switch (choice) {
                case 1:
                    account.addAcc();
                    break;
                case 2:
                    account.logIn();
                    break;

            }
        }

    }

    Scanner scanner = new Scanner(System.in);
    ManagerProduct managerProduct = new ManagerProduct();

    public void user(String acc) {

        managerProduct.newCart(acc);
        managerProduct.productCarts(acc);
        int choice = 0;
        while (true) {
            System.out.println("||||||||||||||||||||||||||||||||||||||||||||||||||||");
            System.out.printf("%-3s %-46s %1s\n","|", "1. Product Menu." , "|");
            System.out.printf("%-3s %-46s %1s\n","|", "2. Sort Up Ascending", "|");
            System.out.printf("%-3s %-46s %1s\n","|", "3. Sort Descending.", "|");
            System.out.printf("%-3s %-46s %1s\n","|", "4. Add Product To Cart.", "|");
            System.out.printf("%-3s %-46s %1s\n","|", "5. Fix Product To Cart.", "|");
            System.out.printf("%-3s %-46s %1s\n","|", "6. Delete Product To Cart.", "|");
            System.out.printf("%-3s %-46s %1s\n","|", "7. Cart.", "|");
            System.out.printf("%-3s %-46s %1s\n","|", "8. Pay The Bill!", "|");
            System.out.printf("%-3s %-46s %1s\n","|", "9. Exit!!!", "|");
            System.out.println("||||||||||||||||||||||||||||||||||||||||||||||||||||");
            try {
                System.out.print("Enter choice: ");
                choice = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.out.printf("%100s \n", "ERROR");
            }
            switch (choice) {
                case 1:
                    managerProduct.showProduct();
                    break;
                case 2:
                    managerProduct.sortUpAscending();
                    break;
                case 3:
                    managerProduct.sortDescending();
                    break;
                case 4:
                    managerProduct.addCart();
                    break;
                case 5:
                    managerProduct.fixQuantityToCart();
                    break;
                case 6:
                    managerProduct.deleteCart();
                    break;
                case 7:
                    managerProduct.showCart();
                    break;
                case 8:
                    managerProduct.payTheBill();
                    break;
                case 9:
                    System.exit(0);
            }
        }
    }

    public void admin() {
        int choice = 0;
        while (true) {
            System.out.println("||||||||||||||||||||||||||||||||||||||||||||||||||||");
            System.out.printf("%-3s %-46s %1s\n","|", "1. Product Menu.","|");
            System.out.printf("%-3s %-46s %1s\n","|", "2. Add product to menu.","|");
            System.out.printf("%-3s %-46s %1s\n","|", "3. Fix product to menu.","|");
            System.out.printf("%-3s %-46s %1s\n","|", "4. Delete product to menu.","|");
            System.out.printf("%-3s %-46s %1s\n","|", "5: Revenue.","|");
            System.out.printf("%-3s %-46s %1s\n","|", "6. Exit!!!","|");
            System.out.println("||||||||||||||||||||||||||||||||||||||||||||||||||||");
            try {
                System.out.print("Enter choice: ");
                choice = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.out.printf("%100s \n", "ERROR");
            }
            switch (choice) {
                case 1:
                    managerProduct.showProduct();
                    break;
                case 2:
                    managerProduct.addProduct();
                    break;
                case 3:
                    managerProduct.fixProduct();
                    break;
                case 4:
                    managerProduct.deleteProduct();
                    break;
                case 5:
                    managerProduct.revenue();
                    break;
                case 6:
                    System.exit(0);
            }
        }
    }

}
