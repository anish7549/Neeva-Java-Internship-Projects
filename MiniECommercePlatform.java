import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Product {
    private String name;
    private double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return name + ", Price: ₹" + price;
    }
}

class Cart {
    private List<Product> products;

    public Cart() {
        products = new ArrayList<>();
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void displayCart() {
        System.out.println("===== Cart Contents =====");
        for (Product product : products) {
            System.out.println(product);
        }
        System.out.println("=========================");
    }

    public double calculateTotal() {
        double total = 0;
        for (Product product : products) {
            total += product.getPrice();
        }
        return total;
    }
}

public class MiniECommercePlatform {
    private static final String FILE_NAME = "products.txt";
    private List<Product> products;
    private Cart cart;
    private Scanner scanner;

    public MiniECommercePlatform() {
        products = new ArrayList<>();
        cart = new Cart();
        scanner = new Scanner(System.in);
        loadProducts();
    }

    private void loadProducts() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String name = parts[0].trim();
                double price = Double.parseDouble(parts[1].trim());
                products.add(new Product(name, price));
            }
        } catch (IOException e) {
            System.out.println("Could not load products: " + e.getMessage());
        }
    }

    private void viewProducts() {
        System.out.println("===== Available Products =====");
        for (Product product : products) {
            System.out.println(product);
        }
        System.out.println("==============================");
    }

    private Product findProduct(String productName) {
        for (Product product : products) {
            if (product.getName().equalsIgnoreCase(productName)) {
                return product;
            }
        }
        return null;
    }

    private void addProductToCart() {
        System.out.print("Enter product name to add to cart: ");
        String productName = scanner.nextLine();
        Product product = findProduct(productName);
        if (product != null) {
            cart.addProduct(product);
            System.out.println(productName + " added to cart.");
        } else {
            System.out.println("Product not found. Please check the product name.");
        }
    }

    private void placeOrder() {
        System.out.println("===== Order Summary =====");
        cart.displayCart();
        double total = cart.calculateTotal();
        System.out.println("Total Amount: ₹" + total);
        System.out.println("Order placed successfully!");
    }

    public void start() {
        while (true) {
            System.out.println("===== Mini E-Commerce Platform =====");
            System.out.println("1. View Products");
            System.out.println("2. Add Product to Cart");
            System.out.println("3. View Cart");
            System.out.println("4. Place Order");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Clear the buffer

            switch (choice) {
                case 1:
                    viewProducts();
                    break;
                case 2:
                    addProductToCart();
                    break;
                case 3:
                    cart.displayCart();
                    break;
                case 4:
                    placeOrder();
                    break;
                case 5:
                    System.out.println("Exiting platform...");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    public static void main(String[] args) {
        MiniECommercePlatform platform = new MiniECommercePlatform();
        platform.start();
    }
}
