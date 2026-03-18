/*
 * EXPERIMENT 08: EMPLOYEE RECORDS MANAGEMENT (Using HashMap)
 * Goal: Manage employee records using HashMap
 *       - Use Employee ID as Key
 *       - Use Employee Name as Value
 *       - Operations: Add, Update, Delete, Search
 *       - Display employees in alphabetical order
 * 
 * Concepts Used:
 * - HashMap
 * - Key-Value Pairs
 * - Iterator
 * - Sorting
 * - Collections
 */

import java.util.HashMap;
import java.util.TreeMap;
import java.util.Set;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Collections;

public class EmployeeManager {
    
    // ============ VARIABLE ============
    // HashMap with Employee ID as Key and Employee Name as Value
    private HashMap<Integer, String> employees;
    
    // ============ CONSTRUCTOR ============
    public EmployeeManager() {
        employees = new HashMap<>();
    }
    
    // ============ METHOD: ADD EMPLOYEE ============
    // Adds a new employee to the HashMap
    public void addEmployee(int employeeID, String employeeName) {
        if (employees.containsKey(employeeID)) {
            System.out.println("✗ Error: Employee with ID " + employeeID + " already exists!");
            return;
        }
        
        if (employeeName == null || employeeName.isEmpty()) {
            System.out.println("✗ Error: Employee name cannot be empty!");
            return;
        }
        
        employees.put(employeeID, employeeName);
        System.out.println("✓ Employee Added: ID=" + employeeID + ", Name=" + employeeName);
    }
    
    // ============ METHOD: UPDATE EMPLOYEE ============
    // Updates an employee's name by ID
    public void updateEmployee(int employeeID, String newName) {
        if (!employees.containsKey(employeeID)) {
            System.out.println("✗ Error: Employee with ID " + employeeID + " not found!");
            return;
        }
        
        String oldName = employees.get(employeeID);
        employees.put(employeeID, newName);
        System.out.println("✓ Employee Updated: ID=" + employeeID + 
                         ", " + oldName + " → " + newName);
    }
    
    // ============ METHOD: DELETE EMPLOYEE ============
    // Deletes an employee by ID
    public void deleteEmployee(int employeeID) {
        if (!employees.containsKey(employeeID)) {
            System.out.println("✗ Error: Employee with ID " + employeeID + " not found!");
            return;
        }
        
        String name = employees.remove(employeeID);
        System.out.println("✓ Employee Deleted: ID=" + employeeID + ", Name=" + name);
    }
    
    // ============ METHOD: SEARCH EMPLOYEE ============
    // Searches for an employee by ID
    public void searchEmployee(int employeeID) {
        if (employees.containsKey(employeeID)) {
            System.out.println("✓ Found: ID=" + employeeID + 
                             ", Name=" + employees.get(employeeID));
        } else {
            System.out.println("✗ Error: Employee with ID " + employeeID + " not found!");
        }
    }
    
    // ============ METHOD: SEARCH BY NAME ============
    // Searches for employee by name (can return multiple matches)
    public void searchByName(String name) {
        boolean found = false;
        for (int id : employees.keySet()) {
            if (employees.get(id).equalsIgnoreCase(name)) {
                System.out.println("✓ Found: ID=" + id + ", Name=" + employees.get(id));
                found = true;
            }
        }
        if (!found) {
            System.out.println("✗ No employee found with name: " + name);
        }
    }
    
    // ============ METHOD: DISPLAY ALL EMPLOYEES ============
    // Displays all employees in HashMap format
    public void displayAllEmployees() {
        if (employees.isEmpty()) {
            System.out.println("Employee database is empty!");
            return;
        }
        
        System.out.println("\n========== ALL EMPLOYEES ==========");
        Set<Integer> keys = employees.keySet();
        for (int id : keys) {
            System.out.println("ID: " + id + " | Name: " + employees.get(id));
        }
    }
    
    // ============ METHOD: DISPLAY IN ALPHABETICAL ORDER ============
    // Displays employees sorted by name in alphabetical order
    public void displayAlphabetically() {
        if (employees.isEmpty()) {
            System.out.println("Employee database is empty!");
            return;
        }
        
        // Create a list of names and sort alphabetically
        ArrayList<String> names = new ArrayList<>(employees.values());
        Collections.sort(names);
        
        System.out.println("\n========== EMPLOYEES (ALPHABETICAL ORDER) ==========");
        for (int i = 0; i < names.size(); i++) {
            System.out.println((i + 1) + ". " + names.get(i));
        }
    }
    
    // ============ METHOD: DISPLAY BY EMPLOYEE ID ORDER ============
    // Displays employees sorted by Employee ID
    public void displayByIDOrder() {
        if (employees.isEmpty()) {
            System.out.println("Employee database is empty!");
            return;
        }
        
        // Create a TreeMap to sort by ID
        TreeMap<Integer, String> sortedMap = new TreeMap<>(employees);
        
        System.out.println("\n========== EMPLOYEES (BY ID ORDER) ==========");
        for (int id : sortedMap.keySet()) {
            System.out.println("ID: " + id + " | Name: " + sortedMap.get(id));
        }
    }
    
    // ============ METHOD: GET TOTAL EMPLOYEES ============
    public int getTotalEmployees() {
        return employees.size();
    }
    
    // ============ METHOD: CHECK IF EMPLOYEE EXISTS ============
    public boolean employeeExists(int employeeID) {
        return employees.containsKey(employeeID);
    }
    
    // ============ METHOD: GET EMPLOYEE NAME ============
    public String getEmployeeName(int employeeID) {
        return employees.get(employeeID);
    }
    
    // ============ MAIN METHOD ============
    public static void main(String[] args) {
        System.out.println("========== EMPLOYEE RECORD MANAGEMENT SYSTEM ==========");
        
        // Create Employee Manager
        EmployeeManager manager = new EmployeeManager();
        
        // ============ ADD EMPLOYEES ============
        System.out.println("--- Adding Employees ---");
        manager.addEmployee(101, "Rihan");
        manager.addEmployee(102, "Alice");
        manager.addEmployee(103, "Bob");
        manager.addEmployee(104, "Charlie");
        manager.addEmployee(105, "Diana");
        manager.addEmployee(106, "Eve");
        
        // Display all employees
        manager.displayAllEmployees();
        
        // ============ UPDATE EMPLOYEE ============
        System.out.println("\n--- Updating Employee ---");
        manager.updateEmployee(102, "Alicia");
        
        // ============ SEARCH EMPLOYEE ============
        System.out.println("\n--- Searching Employee ---");
        manager.searchEmployee(103);
        manager.searchEmployee(999);
        
        // ============ SEARCH BY NAME ============
        System.out.println("\n--- Searching by Name ---");
        manager.searchByName("Rihan");
        manager.searchByName("Unknown");
        
        // ============ DELETE EMPLOYEE ============
        System.out.println("\n--- Deleting Employee ---");
        manager.deleteEmployee(104);
        
        // Display after deletion
        manager.displayAllEmployees();
        
        // ============ DISPLAY IN ALPHABETICAL ORDER ============
        manager.displayAlphabetically();
        
        // ============ DISPLAY BY ID ORDER ============
        manager.displayByIDOrder();
        
        // ============ DISPLAY STATISTICS ============
        System.out.println("\n========== STATISTICS ==========");
        System.out.println("Total Employees: " + manager.getTotalEmployees());
        System.out.println("Employee 101 Exists: " + manager.employeeExists(101));
        System.out.println("Employee 104 Exists: " + manager.employeeExists(104));
        
        System.out.println("\n========== END OF PROGRAM ==========");
    }
}