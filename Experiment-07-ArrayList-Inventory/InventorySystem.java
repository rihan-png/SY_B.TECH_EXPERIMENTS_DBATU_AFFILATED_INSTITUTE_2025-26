/*
 * EXPERIMENT 07: PRODUCT INVENTORY SYSTEM (Using ArrayList)
 * Goal: Build a store inventory system with ArrayList
 *       - Each product has: ID, Name, Price
 *       - Operations: Add, Update, Remove, Sort
 *       - Sort by price and name using custom comparator
 * 
 * Concepts Used:
 * - ArrayList
 * - Objects/Classes
 * - Comparator Interface
 * - Loops and Conditions
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

// ============ PRODUCT CLASS ============
class InventoryProduct {
    // ============ VARIABLES ============
    private int productID;
    private String productName;
    private double price;
    
    // ============ CONSTRUCTOR ============
    public InventoryProduct(int productID, String productName, double price) {
        this.productID = productID;
        this.productName = productName;
        this.price = price;
    }
    
    // ============ GETTER METHODS ============
    public int getProductID() {
        return productID;
    }
    
    public String getProductName() {
        return productName;
    }
    
    public double getPrice() {
        return price;
    }
    
    // ============ SETTER METHODS ============
    public void setPrice(double price) {
        this.price = price;
    }
    
    public void setProductName(String productName) {
        this.productName = productName;
    }
    
    // ============ TOSTRING METHOD ============
    @Override
    public String toString() {
        return String.format("ID: %d | Name: %-15s | Price: ₹%.2f", 
                           productID, productName, price);
    }
}

// ============ INVENTORY SYSTEM CLASS ============
public class InventorySystem {
    
    // ============ VARIABLE ============
    private ArrayList<InventoryProduct> products;
    
    // ============ CONSTRUCTOR ============
    public InventorySystem() {
        products = new ArrayList<>();
    }
    
    // ============ METHOD: ADD PRODUCT ============
    // Adds a new product to inventory
    public void addProduct(int id, String name, double price) {
        // Check if product with same ID already exists
        for (InventoryProduct product : products) {
            if (product.getProductID() == id) {
                System.out.println("✗ Error: Product with ID " + id + " already exists!");
                return;
            }
        }
        
        // Add product to ArrayList
        InventoryProduct newProduct = new InventoryProduct(id, name, price);
        products.add(newProduct);
        System.out.println("✓ Product Added: " + newProduct);
    }
    
    // ============ METHOD: UPDATE PRICE ============
    // Updates price of a product by ID
    public void updatePrice(int id, double newPrice) {
        for (InventoryProduct product : products) {
            if (product.getProductID() == id) {
                double oldPrice = product.getPrice();
                product.setPrice(newPrice);
                System.out.println("✓ Price Updated for " + product.getProductName() + 
                                 ": ₹" + oldPrice + " → ₹" + newPrice);
                return;
            }
        }
        System.out.println("✗ Error: Product with ID " + id + " not found!");
    }
    
    // ============ METHOD: REMOVE PRODUCT ============
    // Removes a product by ID
    public void removeProduct(int id) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getProductID() == id) {
                InventoryProduct removed = products.remove(i);
                System.out.println("✓ Product Removed: " + removed);
                return;
            }
        }
        System.out.println("✗ Error: Product with ID " + id + " not found!");
    }
    
    // ============ METHOD: SORT BY PRICE (ASCENDING) ============
    // Sorts products by price in ascending order
    public void sortByPriceAscending() {
        Collections.sort(products, new Comparator<InventoryProduct>() {
            @Override
            public int compare(InventoryProduct p1, InventoryProduct p2) {
                return Double.compare(p1.getPrice(), p2.getPrice());
            }
        });
        System.out.println("✓ Products sorted by price (Ascending)");
    }
    
    // ============ METHOD: SORT BY PRICE (DESCENDING) ============
    // Sorts products by price in descending order
    public void sortByPriceDescending() {
        Collections.sort(products, new Comparator<InventoryProduct>() {
            @Override
            public int compare(InventoryProduct p1, InventoryProduct p2) {
                return Double.compare(p2.getPrice(), p1.getPrice());
            }
        });
        System.out.println("✓ Products sorted by price (Descending)");
    }
    
    // ============ METHOD: SORT BY NAME (ALPHABETICALLY) ============
    // Sorts products by name in alphabetical order
    public void sortByNameAscending() {
        Collections.sort(products, new Comparator<InventoryProduct>() {
            @Override
            public int compare(InventoryProduct p1, InventoryProduct p2) {
                return p1.getProductName().compareTo(p2.getProductName());
            }
        });
        System.out.println("✓ Products sorted by name (A-Z)");
    }
    
    // ============ METHOD: SORT BY NAME (REVERSE) ============
    // Sorts products by name in reverse alphabetical order
    public void sortByNameDescending() {
        Collections.sort(products, new Comparator<InventoryProduct>() {
            @Override
            public int compare(InventoryProduct p1, InventoryProduct p2) {
                return p2.getProductName().compareTo(p1.getProductName());
            }
        });
        System.out.println("✓ Products sorted by name (Z-A)");
    }
    
    // ============ METHOD: SEARCH PRODUCT ============
    // Searches for a product by ID
    public void searchProduct(int id) {
        for (InventoryProduct product : products) {
            if (product.getProductID() == id) {
                System.out.println("Found: " + product);
                return;
            }
        }
        System.out.println("✗ Product with ID " + id + " not found!");
    }
    
    // ============ METHOD: DISPLAY ALL PRODUCTS ============
    // Displays all products in inventory
    public void displayAllProducts() {
        if (products.isEmpty()) {
            System.out.println("Inventory is empty!");
            return;
        }
        
        System.out.println("\n========== CURRENT INVENTORY ==========");
        for (int i = 0; i < products.size(); i++) {
            System.out.println((i + 1) + ". " + products.get(i));
        }
        System.out.println("Total Products: " + products.size());
    }
    
    // ============ METHOD: GET INVENTORY SIZE ============
    public int getSize() {
        return products.size();
    }
    
    // ============ MAIN METHOD ============
    public static void main(String[] args) {
        System.out.println("========== PRODUCT INVENTORY SYSTEM ==========");
        
        // Create inventory system
        InventorySystem inventory = new InventorySystem();
        
        // ============ ADD PRODUCTS ============
        System.out.println("--- Adding Products ---");
        inventory.addProduct(1, "Laptop", 60000);
        inventory.addProduct(2, "Mouse", 500);
        inventory.addProduct(3, "Keyboard", 2000);
        inventory.addProduct(4, "Monitor", 15000);
        inventory.addProduct(5, "Headphones", 1500);
        
        // Display all products
        inventory.displayAllProducts();
        
        // ============ UPDATE PRICE ============
        System.out.println("\n--- Updating Price ---");
        inventory.updatePrice(2, 600);
        inventory.updatePrice(5, 1800);
        
        // ============ SEARCH PRODUCT ============
        System.out.println("\n--- Searching Product ---");
        inventory.searchProduct(3);
        inventory.searchProduct(99);
        
        // ============ REMOVE PRODUCT ============
        System.out.println("\n--- Removing Product ---");
        inventory.removeProduct(4);
        
        // Display after removal
        inventory.displayAllProducts();
        
        // ============ SORT BY PRICE ============
        System.out.println("\n--- Sorting by Price ---");
        inventory.sortByPriceAscending();
        inventory.displayAllProducts();
        
        // ============ SORT BY NAME ============
        System.out.println("\n--- Sorting by Name ---");
        inventory.sortByNameAscending();
        inventory.displayAllProducts();
        
        System.out.println("\n========== END OF PROGRAM ==========");
    }
}