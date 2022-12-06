package com.mappe;

import java.util.Scanner;

public final class Client {
  
  Inventory inventory;

  /**
   * The constructor initializes an Inventory object.
   */
  public Client(boolean debug) {
    inventory = new Inventory(debug);
    //TODO(ingar): Add welcome message.
  }

  public void run() {
    while (true) {
      System.out.println("\n---What would you like to do?---");
      System.out.println("1. Print all products");
      System.out.println("2. Print a product");
      System.out.println("3. Add a product");
      System.out.println("4. Delete a product");
      System.out.println("5. Increase item quantity");
      System.out.println("6. Decrease item quantity");
      System.out.println("7. Modify a product");
      System.out.println("8. Exit");
      System.out.println("Enter a number: ");
      
      Scanner scanner = new Scanner(System.in);
      int choice = -1;
      if (scanner.hasNextInt()) {
        choice = scanner.nextInt();
      }
      scanner.close();

      switch (choice) {
        case 1:
          printAllProducts();
          break;
        case 2:
          printProduct();
          break;
        case 3:
          addProduct();
          break;
        case 4:
          deleteProduct();
          break;
        case 5:
          increaseProductQuantity();
          break;
        case 6:
          decreaseProductQuantity();
          break;
        case 7:
          modifyProduct();
          break;
        case 8:
          System.out.println("Goodbye!");
          System.exit(0);
          break;
        default:
          System.out.println("Invalid choice. Please try again.");
          break;
      }
    }
  }

  private void printAllProducts() {
    inventory.printAllProducts();
  }

  private void printProduct() {
    String id = findProduct();
    inventory.printSingleProduct(id);
  }

  private void addProduct() {
    Scanner scanner = new Scanner(System.in);

    System.out.println("Enter the id: ");
    final String id = scanner.nextLine();

    System.out.println("Enter the description: ");
    final String description = scanner.nextLine();

    System.out.println("Enter the price: ");
    final int price = scanner.nextInt();

    System.out.println("Enter the brand: ");
    final String brand = scanner.nextLine();

    System.out.println("Enter the weight: ");
    final double weight = scanner.nextDouble();

    System.out.println("Enter the length: ");
    final double length = scanner.nextDouble();

    System.out.println("Enter the height: ");
    final double height = scanner.nextDouble();

    System.out.println("Enter the color: ");
    final String color = scanner.nextLine();

    System.out.println("Enter the quantity: ");
    final int quantity = scanner.nextInt();

    System.out.println("Enter the category: ");
    final int category = scanner.nextInt();

    scanner.close();

    inventory.addProduct(
        id, 
        description, 
        price,
        brand,
        weight,
        length,
        height,
        color,
        quantity,
        category
    );
  }


  private void deleteProduct() {
  }

 
  private void increaseProductQuantity() {
  }

  private void decreaseProductQuantity() {
  }

  private void modifyProduct() {
  }

  private String findProduct() {
    return null;
  }

  

  public static void main(String[] args) {
    Client client = new Client(true);
    client.run();
  }
}
