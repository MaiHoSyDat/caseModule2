package manager;

import product.Cart;
import product.Product;

import java.io.*;
import java.util.*;

public class ManagerProduct {
    static File fileProduct = new File("Product.obj");
    static File fileCart = new File("Cart.obj");
    static File fileBill = new File("Bill.obj");
    static List<Product> products = readProduct();
    static List<Cart> carts = readCart();
    static List<Product> bills = readBill();
    List<Product> productCarts;
    Scanner scanner = new Scanner(System.in);

    static {
        Product.count = readId();
    }


    public void productCarts(String acc) {
        for (Cart c : carts) {
            if (c.getNameAcc().equals(acc)) {
                productCarts = c.getCarts();
                break;
            }
        }
    }

    public void newCart(String acc) {
        boolean check = true;
        for (Cart c : carts) {
            if (c.getNameAcc().equals(acc)) {
                check = false;
                break;
            }
        }
        if (check) {
            carts.add(new Cart(acc));
            writeCart(carts);
        }

    }


    public static void writeId(int id) {
        try {
            FileWriter fw = new FileWriter("Id.txt");
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(String.valueOf(id));
            bw.close();
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int readId() {
        int count = 0;
        try {
            FileReader fr = new FileReader("Id.txt");
            BufferedReader br = new BufferedReader(fr);
            String str = br.readLine();
            while (str != null) {
                count = Integer.parseInt(str);
                str = br.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    public static void writeProduct(List<Product> products) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(fileProduct);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(products);
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<Product> readProduct() {
        List<Product> productList = new ArrayList<>();
        try {
            FileInputStream fileInputStream = new FileInputStream(fileProduct);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            productList = (List<Product>) objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
        } catch (Exception e) {
            System.out.printf("%120s\n","__________Products is empty!____________");
        }
        return productList;
    }

    public static void writeCart(List<Cart> carts) {

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(fileCart);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(carts);
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<Cart> readCart() {
        List<Cart> cartList = new ArrayList<>();
        try {
            FileInputStream fileInputStream = new FileInputStream(fileCart);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            cartList = (List<Cart>) objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
        } catch (Exception e) {
            System.out.printf("%120s\n","__________Cart is empty!____________");
        }
        return cartList;
    }

    public static void writeBill(List<Product> carts) {

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(fileBill);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(carts);
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<Product> readBill() {
        List<Product> billList = new ArrayList<>();
        try {
            FileInputStream fileInputStream = new FileInputStream(fileBill);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            billList = (List<Product>) objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return billList;
    }


    public int inputId() {
        while (true) {
            try {
                System.out.print("Enter ID: ");
                return Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.out.printf("%120s \n", "___?___Retype____");
            }
        }
    }

    public String inputName() {
        System.out.print("Enter Product Name: ");
        return scanner.nextLine();
    }

    public int inputPrice() {
        while (true) {
            try {
                System.out.print("Enter Product Price: ");
                return Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.out.printf("%120s\n", "___?___Retype____");
            }
        }
    }

    public int inputQuantity() {
        while (true) {
            try {
                System.out.print("Enter Product Quantity: ");
                return Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.out.printf("%120s \n", "___?___Retype____");
            }
        }
    }


    ////// Products


    public void showProduct() {
        products = readProduct();
        System.out.printf("%-15s %-5s %-15s %-7s %-10s\n", "----MENU----", "ID", "NAME PRODUCT", "PRICE", "QUANTITY");
        for (Product p : products) {
            p.printf();
        }
    }

    public void addProduct() {
        products.add(new Product(inputName(), inputPrice(), inputQuantity()));
        writeProduct(products);
        writeId(Product.count);
    }

    public int checkID(int id) {

        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }

    public void fixProduct() {
        int id = inputId();
        int index = checkID(id);
        if (index != -1) {
            products.get(index).setName(inputName());
            products.get(index).setPrice(inputPrice());
            products.get(index).setQuantity(inputQuantity());
            writeProduct(products);
        } else {
            System.out.printf("%120s \n", "__?___The product to be repaired is not on the list!_____");
        }
    }

    public void deleteProduct() {
        int id = inputId();
        int index = checkID(id);
        if (index != -1) {
            products.remove(index);
            writeProduct(products);
        } else {
            System.out.printf("%120s \n", "__?___The product to be removed is not in the list_____");
        }

    }

    public void revenue() {
        int total = 0;

        System.out.printf("%-15s %-5s %-15s %-7s %-10s  \n", "", "ID", "NAME", "PRICE", "QUANTITY");
        for (Product p : bills) {
            p.printf();
            total += p.getPrice() * p.getQuantity();
        }
        System.out.printf("%30s %20d \n\n", "TOTAL", total);

    }


    ///////////// Cart


    public int totalCart() {
        int total = 0;
        for (Product p : productCarts) {
            total += (p.getPrice() * p.getQuantity());
        }
        return total;
    }

    public int checkIdCart(int id) {
        for (int i = 0; i < productCarts.size(); i++) {
            if (productCarts.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }

    public int checkIdProductToCart() {
        int id = inputId();

        for (int i = 0; i < productCarts.size(); i++) {
            if (productCarts.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }


    public void showCart() {

        System.out.printf(" %-15s %-5s %-15s %-7s %-10s  \n", "", "ID", "NAME PRODUCT", "PRICE", "QUANTITY");
        for (Product p : productCarts) {
            p.printf();
        }
        int total = totalCart();
        System.out.printf("%30s %20d \n\n", "TOTAL", total);

    }
    public void  sortUpAscending(){
        products.sort(new Comparator<Product>() {
            @Override
            public int compare(Product o1, Product o2) {
                return o1.getPrice() - o2.getPrice();
            }
        });
    }
    public void  sortDescending(){
        products.sort(new Comparator<Product>() {
            @Override
            public int compare(Product o1, Product o2) {
                return o2.getPrice() - o1.getPrice();
            }
        });
    }




    public void addCart() {
        int id = inputId();
        int indexProduct = checkID(id);
        if (indexProduct != -1) {
            int index = checkIdCart(id);
            if (index != -1) {
                if (products.get(indexProduct).getQuantity()>productCarts.get(index).getQuantity()){
                    productCarts.get(index).setQuantity(productCarts.get(index).getQuantity() + 1);
                }else {
                    System.out.printf("%120s \n","_______Exceed the number of products!_______");
                }
            } else {
                int id1 = products.get(indexProduct).getId();
                String name = products.get(indexProduct).getName();
                int quantity = 1;
                int price = products.get(indexProduct).getPrice();
                productCarts.add(new Product(id1, name, price, quantity));
            }
            writeCart(carts);
        } else {
            System.out.printf("%120s \n", "___?___The ID you entered is not correct!___");
        }
    }


    public void fixQuantityToCart() {

        int index = checkIdProductToCart();
        if (index != -1) {
            productCarts.get(index).setQuantity(inputQuantity());
        } else {
            System.out.printf("%120s \n", "___?___The ID you entered is not correct!___");
        }
    }

    public void deleteCart() {
        int index = checkIdProductToCart();
        if (index != -1) {
            productCarts.remove(index);
        } else {
            System.out.printf("%120s \n", "___?___The ID you entered is not correct!___");
        }
    }

    public void payTheBill() {
        for (Product p : products) {
            for (int j = productCarts.size() - 1; j >= 0; j--) {
                if (p.getId() == productCarts.get(j).getId()) {
                    bills.add(productCarts.get(j));
                    p.setQuantity(p.getQuantity() - productCarts.get(j).getQuantity());
                    productCarts.remove(j);
                }
            }
        }
        writeBill(bills);
        writeProduct(products);
        writeCart(carts);
    }

}


