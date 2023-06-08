package product;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Cart implements Serializable {

    private String nameAcc;
    private List<Product> cartList;

    public Cart(String nameAcc) {
        cartList = new ArrayList<>();
        this.nameAcc = nameAcc;
    }

    public List<Product> getCarts() {
        return cartList;
    }

    public void setCarts(List<Product> cartList) {
        this.cartList = cartList;
    }

    public String getNameAcc() {
        return nameAcc;
    }

    public void setNameAcc(String nameAcc) {
        this.nameAcc = nameAcc;
    }

    @Override
    public String toString() {
        return nameAcc + "," + cartList.toString();
    }

}
