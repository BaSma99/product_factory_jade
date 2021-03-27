package product_project;

public class Product {

    public String productName;
    public double productPrice;
    public   int productCount;

    public Product() {
    }

    public Product(String productName, double productPrice, int productCount) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.productCount = productCount;
    }
}
