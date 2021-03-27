package product_project;

import java.io.Serializable;
import java.util.Scanner;

public class Order implements Serializable {

    public String productName;
    public double singleProductPrice;
    public int singleProductCount;
    public double totalPrice;
    
    

    public Order(String productName, double singleProductPrice, int singleProductCount) {
        this.productName = productName;
        this.singleProductPrice = singleProductPrice;
        this.singleProductCount = singleProductCount;
    }
    

    public String getProductName() {
        return productName;
    }

    public double getSingleProductPrice() {
        return singleProductPrice;
    }

    public int getSingleProductCount() {
        return singleProductCount;
    }
}
