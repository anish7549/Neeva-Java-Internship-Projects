import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class InventoryManagementSystem {

    private class Item {
        private String name;
        private int quantity;
        private double price;

        public Item(String name, int quantity, double price) {
            this.name = name;
            this.quantity = quantity;
            this.price = price;
        }

        public String getName() {
            return name;
        }

        public int getQuantity() {
            return quantity;
        }

        public double getPrice() {
            return price;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public void displayItemDetails() {
            System.out.println("Name: " + name);
            System.out.println("Quantity: " + quantity);
            System.out.println("Price: ₹" + price);
        }
    }

    private ArrayList<Item> inventory = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);
    private final String FILE_NAME = "inventory.txt";

    public static void main(String[] args) {
        InventoryManagementSystem system = new InventoryManagementSystem();
        system.loadInventory();
        system.run();
    }

    private void run() {
        boolean running = true;

        while (running) {
            System.out.println("\n===== Inventory Management System =====");
            System.out.println("1. Add Item");
            System.out.println("2. Update Item");
            System.out.println("3. View Inventory");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addItem();
                    break;
                case 2:
                    updateItem();
                    break;
                case 3:
                    viewInventory();
                    break;
                case 4:
                    saveInventory();
                    running = false;
                    System.out.println("Exiting system...");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void addItem() {
        System.out.print("Enter item name: ");
        String name = scanner.nextLine();
        System.out.print("Enter quantity: ");
        int quantity = scanner.nextInt();
        System.out.print("Enter price: ");
        double price = scanner.nextDouble();
        scanner.nextLine();

        Item newItem = new Item(name, quantity, price);
        inventory.add(newItem);
        System.out.println("Item added successfully!");
    }

    private void updateItem() {
        System.out.print("Enter name of the item to update: ");
        String name = scanner.nextLine();
        Item item = findItem(name);
        if (item != null) {
            System.out.print("Enter new quantity: ");
            int quantity = scanner.nextInt();
            scanner.nextLine();
            item.setQuantity(quantity);
            System.out.println("Item updated successfully!");
        } else {
            System.out.println("Item not found.");
        }
    }

    private void viewInventory() {
        System.out.println("\n===== Inventory =====");
        for (Item item : inventory) {
            item.displayItemDetails();
            System.out.println("---------------------");
        }
    }

    private Item findItem(String name) {
        for (Item item : inventory) {
            if (item.getName().equalsIgnoreCase(name)) {
                return item;
            }
        }
        return null;
    }

    private void loadInventory() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String name = parts[0];
                int quantity = Integer.parseInt(parts[1]);
                double price = Double.parseDouble(parts[2]);
                Item item = new Item(name, quantity, price);
                inventory.add(item);
            }
        } catch (IOException e) {
            System.out.println("Could not load inventory: " + e.getMessage());
        }
    }

    private void saveInventory() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Item item : inventory) {
                bw.write(item.getName() + "," + item.getQuantity() + "," + item.getPrice());
                bw.newLine();
            }
            System.out.println("Inventory saved successfully!");
        } catch (IOException e) {
            System.out.println("Could not save inventory: " + e.getMessage());
        }
    }
}
