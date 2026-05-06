import java.util.ArrayList;
import java.util.Scanner;

class Product {
    private int id;
    private String name;
    private int quantity;
    private double price;

    public Product(int id, String name, int quantity, double price) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public double getTotalValue() {
        return quantity * price;
    }

    public void updateProduct(String name, int quantity, double price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public boolean sellProduct(int qty) {
        if (qty <= quantity) {
            quantity -= qty;
            return true;
        }
        return false;
    }

    public void restockProduct(int qty) {
        quantity += qty;
    }

    public void display() {
        System.out.println("Product ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Quantity: " + quantity);
        System.out.println("Price: ₹" + price);
        System.out.println("Total Value: ₹" + getTotalValue());

        if (quantity < 5) {
            System.out.println("Warning: Low Stock!");
        }

        System.out.println("----------------------");
    }
}

class Inventory {
    private ArrayList<Product> products = new ArrayList<>();

    public void addProduct(Product product) {
        products.add(product);
        System.out.println("Product added successfully.");
    }

    public Product searchProduct(int id) {
        for (Product p : products) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    public void updateProduct(int id, String name, int quantity, double price) {
        Product product = searchProduct(id);

        if (product != null) {
            product.updateProduct(name, quantity, price);
            System.out.println("Product updated successfully.");
        } else {
            System.out.println("Product not found.");
        }
    }

    public void deleteProduct(int id) {
        Product product = searchProduct(id);

        if (product != null) {
            products.remove(product);
            System.out.println("Product deleted successfully.");
        } else {
            System.out.println("Product not found.");
        }
    }

    public void sellProduct(int id, int qty) {
        Product product = searchProduct(id);

        if (product == null) {
            System.out.println("Product not found.");
        } else if (product.sellProduct(qty)) {
            System.out.println("Product sold successfully.");
        } else {
            System.out.println("Not enough stock available.");
        }
    }

    public void restockProduct(int id, int qty) {
        Product product = searchProduct(id);

        if (product != null) {
            product.restockProduct(qty);
            System.out.println("Product restocked successfully.");
        } else {
            System.out.println("Product not found.");
        }
    }

    public void displayProducts() {
        if (products.isEmpty()) {
            System.out.println("Inventory is empty.");
            return;
        }

        double totalInventoryValue = 0;

        for (Product p : products) {
            p.display();
            totalInventoryValue += p.getTotalValue();
        }

        System.out.println("Total Inventory Value: ₹" + totalInventoryValue);
    }
}

public class invmng {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Inventory inventory = new Inventory();

        while (true) {
            System.out.println("\n--- Inventory Management System ---");
            System.out.println("1. Add Product");
            System.out.println("2. Search Product");
            System.out.println("3. Update Product");
            System.out.println("4. Delete Product");
            System.out.println("5. Sell Product");
            System.out.println("6. Restock Product");
            System.out.println("7. Display All Products");
            System.out.println("8. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter Product ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter Product Name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter Quantity: ");
                    int quantity = sc.nextInt();

                    System.out.print("Enter Price: ");
                    double price = sc.nextDouble();

                    inventory.addProduct(new Product(id, name, quantity, price));
                    break;

                case 2:
                    System.out.print("Enter Product ID to search: ");
                    int searchId = sc.nextInt();

                    Product product = inventory.searchProduct(searchId);

                    if (product != null) {
                        product.display();
                    } else {
                        System.out.println("Product not found.");
                    }
                    break;

                case 3:
                    System.out.print("Enter Product ID to update: ");
                    int updateId = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter New Name: ");
                    String newName = sc.nextLine();

                    System.out.print("Enter New Quantity: ");
                    int newQuantity = sc.nextInt();

                    System.out.print("Enter New Price: ");
                    double newPrice = sc.nextDouble();

                    inventory.updateProduct(updateId, newName, newQuantity, newPrice);
                    break;

                case 4:
                    System.out.print("Enter Product ID to delete: ");
                    int deleteId = sc.nextInt();

                    inventory.deleteProduct(deleteId);
                    break;

                case 5:
                    System.out.print("Enter Product ID to sell: ");
                    int sellId = sc.nextInt();

                    System.out.print("Enter Quantity to sell: ");
                    int sellQty = sc.nextInt();

                    inventory.sellProduct(sellId, sellQty);
                    break;

                case 6:
                    System.out.print("Enter Product ID to restock: ");
                    int restockId = sc.nextInt();

                    System.out.print("Enter Quantity to add: ");
                    int restockQty = sc.nextInt();

                    inventory.restockProduct(restockId, restockQty);
                    break;

                case 7:
                    inventory.displayProducts();
                    break;

                case 8:
                    System.out.println("Exiting Inventory System.");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
