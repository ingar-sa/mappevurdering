package com.mappe;

import java.util.List;
import java.util.Scanner;

public final class Client {
  
  private Inventory inventory;

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
          addProduct(scanner);
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
          editProduct(scanner);
          break;
        case 8:
          System.out.println("Goodbye!");
          running = false;
          break;
        default:
          System.out.println("Invalid choice. Please try again.");
          break;
      }
      
      if (running) {
        System.out.println("Press enter to continue...");
        scanner.next();
      }
    }

    scanner.close();
  }

  private void printAllProducts() {
    inventory.printAllProducts();
  }

  private void printProduct(Scanner scanner) {
    String id = findProduct(scanner);
    if (id == null) {
      return;
    }

    inventory.printSingleProduct(id);
  }

  private void addProduct(Scanner scanner) {
    try {
      System.out.println("Write --exit to return to the menu\n");
      System.out.println("Enter the id: ");
      String input = scanner.next();
      if (input.equals("--exit")) {
        return;
      }
      final String id = input;

      System.out.println("Enter the description: ");
      input = scanner.next();
      if (input.equals("--exit")) {
        return;
      }
      final String description = input;

      System.out.println("Enter the price: ");
      input = scanner.next();
      if (input.equals("--exit")) {
        return;
      }
      final int price = Integer.parseInt(input);

      System.out.println("Enter the brand: ");
      input = scanner.next();
      if (input.equals("--exit")) {
        return;
      }
      final String brand = input;

      System.out.println("Enter the weight: ");
      input = scanner.next();
      if (input.equals("--exit")) {
        return;
      }
      final double weight = Double.parseDouble(input);

      System.out.println("Enter the length: ");
      input = scanner.next();
      if (input.equals("--exit")) {
        return;
      }
      final double length = Double.parseDouble(input);

      System.out.println("Enter the height: ");
      input = scanner.next();
      if (input.equals("--exit")) {
        return;
      }
      final double height = Double.parseDouble(input);

      System.out.println("Enter the color: ");
      input = scanner.next();
      if (input.equals("--exit")) {
        return;
      }
      final String color = input;

      System.out.println("Enter the quantity: ");
      input = scanner.next();
      if (input.equals("--exit")) {
        return;
      }
      final int quantity = Integer.parseInt(input);

      System.out.println("Enter the category: ");
      input = scanner.next();
      if (id.equals("--exit")) {
        return;
      }
      final int category = Integer.parseInt(input);

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
    } catch (NumberFormatException e) {
      System.out.println("Invalid input. Please try again.");
    } catch (IllegalArgumentException e) {
      System.out.println(e.getMessage());
    } catch (Exception e) {
      System.out.println("Invalid input. Please try again.");
    }
  }

  private void deleteProduct(Scanner scanner) {
    String id = findProduct(scanner);
    if (id == null) {
      return;
    }

    inventory.deleteProduct(id);
  }

  private void increaseProductQuantity(Scanner scanner) {
    String id = findProduct(scanner);
    if (id == null) {
      return;
    }

    System.out.println("\nEnter the amount to increase the quantity by: ");
    try {
      int amount = Integer.parseInt(scanner.next());
      inventory.increaseProductQuantity(id, amount);
    } catch (NumberFormatException e) {
      System.out.println("Invalid input. Please try again.");
    } catch (Exception e) {
      System.out.println("Something went wrong. Please try again.");
    }
  }

  private void decreaseProductQuantity(Scanner scanner) {
    String id = findProduct(scanner);
    if (id == null) {
      return;
    }

    System.out.println("\nEnter the amount to decrease the quantity by: ");
    try {
      int amount = Integer.parseInt(scanner.next());
      inventory.decreaseProductQuantity(id, amount);
    } catch (NumberFormatException e) {
      System.out.println("Invalid input. Please try again.");
    } catch (IllegalArgumentException e) {
      System.out.println(e.getMessage());
    } catch (Exception e) {
      System.out.println("Something went wrong. Please try again.");
    }
  }
  
  /*
   * Essentially the same as addProduct, but empty input
   * will be treated as no change to the given field.
   * The user can exit any time using "--exit".
   */
  private void editProduct(Scanner scanner) {
    String id = findProduct(scanner);
    if (id == null) {
      return;
    }

    try {
      System.out.println("Write --exit to return to the menu\n");
      System.out.println("Enter the id: ");
      String input = scanner.next();
      if (input.equals("--exit")) {
        return;
      }
      final String newId = (input.equals("")) ? null : input;

      System.out.println("Enter the description: ");
      input = scanner.next();
      if (input.equals("--exit")) {
        return;
      }
      final String description = (input.equals("")) ? null : input;

      System.out.println("Enter the price: ");
      input = scanner.next();
      if (input.equals("--exit")) {
        return;
      }
      final int price = (input.equals("")) ? -1 : Integer.parseInt(input);

      System.out.println("Enter the brand: ");
      input = scanner.next();
      if (input.equals("--exit")) {
        return;
      }
      final String brand = (input.equals("")) ? null : input;

      System.out.println("Enter the weight: ");
      input = scanner.next();
      if (input.equals("--exit")) {
        return;
      }
      final double weight = (input.equals("")) ? -1 : Double.parseDouble(input);

      System.out.println("Enter the length: ");
      input = scanner.next();
      if (input.equals("--exit")) {
        return;
      }
      final double length = (input.equals("")) ? -1 : Double.parseDouble(input);

      System.out.println("Enter the height: ");
      input = scanner.next();
      if (input.equals("--exit")) {
        return;
      }
      final double height = (input.equals("")) ? -1 : Double.parseDouble(input);

      System.out.println("Enter the color: ");
      input = scanner.next();
      if (input.equals("--exit")) {
        return;
      }
      final String color = (input.equals("")) ? null : input;

      System.out.println("Enter the quantity: ");
      input = scanner.next();
      if (input.equals("--exit")) {
        return;
      }
      final int quantity = (input.equals("")) ? -1 : Integer.parseInt(input);

      System.out.println("Enter the category: ");
      input = scanner.next();
      if (id.equals("--exit")) {
        return;
      }
      final int category = (input.equals("")) ? -1 : Integer.parseInt(input);

      inventory.editProduct(
          id,
          newId, 
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
      
    } catch (NumberFormatException e) {
      System.out.println("Invalid input. Please try again.");
    } catch (IllegalArgumentException e) {
      System.out.println(e.getMessage());
    } catch (Exception e) {
      System.out.println("Invalid input. Please try again.");
    }
  }
   
  private String findProduct(Scanner scanner) {
    System.out.println("---Search for a product by id or description---");
    System.out.println("Enter search term (--help for help, --exit to exit): ");

    String searchTerm = scanner.next();
    if (searchTerm.equals("--exit")) {
      return null;
    } else if (searchTerm.equals("--help")) {
      System.out.println("\nSearch by id or description. "
            + "You don't have to enter the whole id or description, only part of it.\n"
            + "ID's are case sensitive, and the input must match from the start of the ID:\n"
            + "AB will match with ABC, but BC or aB will not.\n"
            + "Descriptions are not case sensitive, but the term must match whole words.\n"
            + "The description(s) with the most matching words will be returned.\n"
            + "\nEnter search term (--exit to exit): ");

      searchTerm = scanner.next();
    }

    if (searchTerm.equals("--exit")) {
      return null;
    }
    
    List<Product> matches = inventory.findProducts(searchTerm);
    if (matches.size() == 0) {
      System.out.println("No matches found.");
      return null;
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
      String input = scanner.next();
      if (input.equals("--exit")) {
        return null;
      }

      try {
        chosenOption = Integer.parseInt(input);
      } catch (NumberFormatException e) {
        System.out.println("Invalid option. Please try again.");
        continue;
      }

      if (chosenOption > 0 && chosenOption <= matches.size()) {
        chosenProduct = true;
        product = matches.get(chosenOption - 1).getId();
      } else {
        System.out.println("Invalid option. Please try again.");
        continue;
      }
    }
    
    return product;
  }

  public static void main(String[] args) {
    Client client = new Client(true);
    client.run();
  }
}
