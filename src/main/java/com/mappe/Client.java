package com.mappe;

import java.util.List;
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
    Scanner scanner = new Scanner(System.in);
    boolean running = true;
    while (running) {
      
      System.out.println("\n---What would you like to do?---");
      System.out.println("1. Print all products");
      System.out.println("2. Print a product");
      System.out.println("3. Add a product");
      System.out.println("4. Delete a product");
      System.out.println("5. Increase item quantity");
      System.out.println("6. Decrease item quantity");
      System.out.println("7. Modify a product");
      System.out.println("8. Exit");
      System.out.println("\nEnter a number: ");

      int choice = -1;
      if (scanner.hasNextInt()) {
        choice = scanner.nextInt();
      }

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
          running = false;
          break;
        default:
          System.out.println("Invalid choice. Please try again.");
          break;
      }

      
    }

    scanner.close();
  }

  private void printAllProducts() {
    inventory.printAllProducts();
  }

  private void printProduct() {
    String id = findProduct();
    inventory.printSingleProduct(id);
  }

  private void addProduct() {

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
    String id = findProduct();
    inventory.deleteProduct(id);
  }

 
  private void increaseProductQuantity() {
    String id = findProduct();
    System.out.println("Enter the amount to increase the quantity by: ");

    Scanner scanner = new Scanner(System.in);
    int amount = scanner.nextInt();
    inventory.increaseProductQuantity(id, amount);
    scanner.close();
  }

  private void decreaseProductQuantity() {
    String id = findProduct();
    System.out.println("Enter the amount to decrease the quantity by: ");

    Scanner scanner = new Scanner(System.in);
    int amount = scanner.nextInt();
    inventory.decreaseProductQuantity(id, amount);
    scanner.close();
  }

  private void modifyProduct() {
  }

  private String findProduct() {
    System.out.println("---Search for a product---");
    System.out.println("Enter search term (--help for help): ");
    Scanner scanner = new Scanner(System.in);
    String searchTerm = scanner.nextLine();

    if (searchTerm.equals("--help")) {
      System.out.println("Search by id or description. "
            + "You don't have to enter the whole id or description, only part of it.\n"
            + "ID's are case sensitive, and the input must match from the start:\n"
            + "AB will match with ABC, but BC or aB will not.\n"
            + "Descriptions are not case sensitive, but the term must match whole words.\n"
            + "The description(s) that matches the search term the closest will be returned.\n");
                      
      System.out.println("Enter search term: ");
      searchTerm = scanner.nextLine();
    }
    
    List<Product> matches = inventory.findProducts(searchTerm);

    if (matches.size() == 0) {
      System.out.println("No matches found.");
      scanner.close();
      return null; //TODO(ingar): Throw exception instead?
    }

    for (int i = 0; i < matches.size(); i++) {
      System.out.println("Option " + (i + 1) + ":\n" 
                  + "\tID: " + matches.get(i).getId()
                  + "\n\tDescription: " + matches.get(i).getDescription());
    }

    boolean chosenProduct = false;
    String product = new String();
    while (!chosenProduct) {
      System.out.println("Enter option number (--exit to exit): ");
      int chosenOption = -1;
      if (scanner.hasNextInt()) {
        chosenOption = scanner.nextInt();
      } else {
        String exit = scanner.nextLine();
        if (exit.equals("--exit")) {
          scanner.close();
          return null;
        }
      }

      if (chosenOption > 0 && chosenOption <= matches.size()) {
        chosenProduct = true;
        product = matches.get(chosenOption - 1).getId();
      } else {
        System.out.println("Invalid option. Please try again.");
      }
    }

    scanner.close();
    return product;
  }

  public static void main(String[] args) {
    Client client = new Client(true);
    client.run();
  }
}
