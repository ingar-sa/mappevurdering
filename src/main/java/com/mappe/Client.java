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
    scanner.useDelimiter(System.lineSeparator());
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
      try {
        choice = Integer.parseInt(scanner.next());
      } catch (NumberFormatException e) {
        System.out.println("Invalid choice. Please try again.");
        continue;
      } catch (Exception e) {
        System.out.println("Something went wrong. Please try again.");
        continue;
      }

      switch (choice) {
        case 1:
          printAllProducts();
          break;
        case 2:
          printProduct(scanner);
          break;
        case 3:
          addProduct();
          break;
        case 4:
          deleteProduct(scanner);
          break;
        case 5:
          increaseProductQuantity(scanner);
          break;
        case 6:
          decreaseProductQuantity(scanner);
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
      
      System.out.println("Press enter to continue...");
      scanner.next();
    }

    scanner.close();
  }

  private void printAllProducts() {
    inventory.printAllProducts();
  }

  private void printProduct(Scanner scanner) {
    String id = findProduct(scanner);
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


  private void deleteProduct(Scanner scanner) {
    String id = findProduct(scanner);
    inventory.deleteProduct(id);
  }

 
  private void increaseProductQuantity(Scanner scanner) {
    String id = findProduct(scanner);
    System.out.println("\nEnter the amount to increase the quantity by: ");

    int amount = Integer.parseInt(scanner.next());
    inventory.increaseProductQuantity(id, amount);
  }

  private void decreaseProductQuantity(Scanner scanner) {
    String id = findProduct(scanner);
    System.out.println("\nEnter the amount to decrease the quantity by: ");

    int amount = Integer.parseInt(scanner.next());
    inventory.decreaseProductQuantity(id, amount);
  }

  private void modifyProduct() {
  }

  private String findProduct(Scanner scanner) {
    System.out.println("---Search for a product---");
    System.out.println("Enter search term (--help for help): ");
    String searchTerm = scanner.next();

    if (searchTerm.equals("--help")) {
      System.out.println("\nSearch by id or description. "
            + "You don't have to enter the whole id or description, only part of it.\n"
            + "ID's are case sensitive, and the input must match from the start of the ID:\n"
            + "AB will match with ABC, but BC or aB will not.\n"
            + "Descriptions are not case sensitive, but the term must match whole words.\n"
            + "The description(s) that matches the search term the closest will be returned.\n");
                      
      System.out.println("Enter search term: ");
      searchTerm = scanner.next();
    }
    
    List<Product> matches = inventory.findProducts(searchTerm);

    if (matches.size() == 0) {
      System.out.println("No matches found.");
      return null; //TODO(ingar): Throw exception instead?
    }

    for (int i = 0; i < matches.size(); i++) {
      System.out.println("\nOption " + (i + 1) + ":\n" 
                  + "\tID: " + matches.get(i).getId()
                  + "\n\tDescription: " + matches.get(i).getDescription());
    }

    boolean chosenProduct = false;
    String product = "";
    while (!chosenProduct) {
      System.out.println("\nEnter option number (--exit to exit): ");

      int chosenOption = -1;
      if (scanner.hasNextInt()) {
        chosenOption = Integer.parseInt(scanner.next());
      } else {
        String exit = scanner.next();
        if (exit.equals("--exit")) {
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
    
    return product;
  }

  public static void main(String[] args) {
    Client client = new Client(true);
    client.run();
  }
}
