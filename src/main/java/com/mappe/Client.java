package com.mappe;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Represents a user interface for a warehouse management system.
 * The client presents a menu to the user, and the user can choose what to do.
 * The user has options for printing all products, printing a specific product,
 * adding, deleting and editing a product and increasing and decreasing the quantity
 * of a product.
 * The class has extensive guard clauses to ensure that invalid input does not crash
 * the program.
 */
public final class Client {
  
  private final Inventory inventory;

  /**
   * The constructor initializes an Inventory object.
   */
  public Client() {
    inventory = new Inventory();
  }

  /**
   * Prints a menu, gets the user's choice using a scanner, 
   * and then calls the appropriate function.
   */
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
      System.out.println("8. Add default products");
      System.out.println("9. Exit");
      System.out.print("\nEnter a number: ");

      int choice = -1;
      try {
        choice = Integer.parseInt(scanner.next());

      } catch (Exception e) {
        System.out.println("Invalid choice. Please try again.");
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
          addDefaultProducts();
          break;
        case 9:
          System.out.println("Goodbye!");
          running = false;
          break;
        default:
          System.out.println("Invalid choice. Please try again.");
          break;
      }
      
      if (running) {
        System.out.println("\n---Press enter to continue---");
        scanner.next();
      }
    }

    scanner.close();
    System.exit(0);
  }

  /**
   * Prints all the products in the inventory using the 
   * formatted string the Product class provides.
   * 
   * @param scanner A scanner to get the user's input.
   */
  private void printAllProducts() {
    System.out.println("\n---All products---");
    for (Product product : inventory.getAllProducts()) {
      System.out.println(product.getFormattedString());
    }
  }

  /**
   * Prints a single product using in the inventory using
   * the formatted string the Product class provides.
   * Uses the findProduct method to get a product id.
   * Exits to the main menu if no prodcut is found.
   * 
   * @param scanner A scanner to get the user's input.
   */
  private void printProduct(Scanner scanner) {
    String id = findProduct(scanner);
    if (id == null) {
      return;
    }
    
    System.out.println("\n---The product is---");
    System.out.println(inventory.getProductFormattedString(id));
  }

  /**
   * Creates a new product from the user's input and adds it to the inventory.
   * Exits to the main menu if the the product id already exists,
   * or the user enters invalid values for the Product class parameters.
   * 
   * @param scanner A scanner to get the user's input.
   */
  private void addProduct(Scanner scanner) {
    try {
      System.out.println("\n---Add a product---");
      System.out.println("\nWrite --exit to return to the menu");
      System.out.print("Enter the id: ");

      String input = scanner.next();
      if (input.equals("--exit")) {
        return;
      }
      final String id = input;

      System.out.print("\nEnter the description: ");
      input = scanner.next();
      if (input.equals("--exit")) {
        return;
      }
      final String description = input;

      System.out.print("\nEnter the price: ");
      input = scanner.next();
      if (input.equals("--exit")) {
        return;
      }
      final int price = Integer.parseInt(input);

      System.out.print("\nEnter the brand: ");
      input = scanner.next();
      if (input.equals("--exit")) {
        return;
      }
      final String brand = input;

      System.out.print("\nEnter the weight: ");
      input = scanner.next();
      if (input.equals("--exit")) {
        return;
      }
      final double weight = Double.parseDouble(input);

      System.out.print("\nEnter the length: ");
      input = scanner.next();
      if (input.equals("--exit")) {
        return;
      }
      final double length = Double.parseDouble(input);

      System.out.print("\nEnter the height: ");
      input = scanner.next();
      if (input.equals("--exit")) {
        return;
      }
      final double height = Double.parseDouble(input);

      System.out.print("\nEnter the color: ");
      input = scanner.next();
      if (input.equals("--exit")) {
        return;
      }
      final String color = input;

      System.out.print("\nEnter the quantity: ");
      input = scanner.next();
      if (input.equals("--exit")) {
        return;
      }
      final int quantity = Integer.parseInt(input);

      System.out.print("\nEnter the category: ");
      input = scanner.next();
      if (id.equals("--exit")) {
        return;
      }
      final Category category = Category.parseCategory(input);

      Product newProduct = new Product(
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

      System.out.println("\n---The new product is---");
      System.out.println(newProduct.getFormattedString());

      System.out.print("\nAre you sure you want to add this product? (y/n): ");
      input = scanner.next();
      if (input.equals("y")) {
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

        System.out.println("\n---The product was added---");

      } else {
        System.out.println("\n---The product was not added---");
      }

    } catch (IllegalArgumentException e) {
      System.out.println("\nInvalid value for product: " + e.getMessage());

    } catch (Exception e) {
      System.out.println("\nInvalid input. Please try again.");
    }
  }

  /**
   * Deletes a product from the inventory. Uses the findProduct method
   * to get a product id. It prints the products information and waits
   * for the user to confirm the deletion. Exits to the main menu if no
   * product is found.
   * 
   * @param A scanner to get the user's input.
   */
  private void deleteProduct(Scanner scanner) {
    String id = findProduct(scanner);
    if (id == null) {
      return;
    }

    System.out.println("\n---The product is---");
    System.out.println(inventory.getProductFormattedString(id));
    System.out.print("\nAre you sure you want to delete this product? (y/n): ");

    String input = scanner.next();
    if (input.equals("y")) {
      System.out.println("---The product was deleted---");
      inventory.deleteProduct(id);

    } else {
      System.out.println("---The product was not deleted---");
    }
  }

  /**
   * Increases the quantity of a product. Uses the findProduct method
   * to get a product id. Gets the amount to increase the quantity from
   * the user. Exits to the main menu if no product is found, or if the user
   * enters a negative value or a non-number. 
   * 
   * @param A scanner to get the user's input.
   */
  private void increaseProductQuantity(Scanner scanner) {
    String id = findProduct(scanner);
    if (id == null) {
      return;
    }

    System.out.println("\n---The product is---");
    System.out.println(inventory.getProductFormattedString(id));
    
    System.out.print("\nEnter the amount to increase the quantity by: ");
    try {
      int amount = Integer.parseInt(scanner.next());
      inventory.increaseProductQuantity(id, amount);
      System.out.println("\n---The updated product is---");
      System.out.println(inventory.getProductFormattedString(id));

    } catch (IllegalArgumentException e) {
      System.out.println("Invalid value for product: " + e.getMessage());

    } catch (Exception e) {
      System.out.println("Invalid input. Please try again.");
    }
  }

  /**
   * Decreases the quantity of a product. Uses the findProduct method
   * to get a product id. Gets the amount to increase the quantity from
   * the user. Exits to the main menu if no product is found, if the user
   * enters a negative value or a non-number or if the new quantity is less
   * than 0.
   * 
   * @param A scanner to get the user's input.
   */
  private void decreaseProductQuantity(Scanner scanner) {
    String id = findProduct(scanner);
    if (id == null) {
      return;
    }

    try {
      System.out.println("\n---The product is---");
      System.out.println(inventory.getProductFormattedString(id));
      
      System.out.println("\nThe new quantity cannot be less than 0.");
      System.out.print("\nEnter the amount to decrease the quantity by: ");

      int amount = Integer.parseInt(scanner.next());
      inventory.decreaseProductQuantity(id, amount);
      System.out.println("\n---The updated product is---");
      System.out.println(inventory.getProductFormattedString(id));

    } catch (IllegalArgumentException e) {
      System.out.println("Invalid value for product: " + e.getMessage());

    } catch (Exception e) {
      System.out.println("Invalid input. Please try again.");
    }
  }
  
  /**
   * Edits a product in the inventory. Uses the findProduct method
   * to get a product id. The user can edit any field of the product.
   * This is to maximize user frinendliness, as they can edit any
   * mistake they made when adding a product without having to delete
   * the product and add it again. If an input is left blank, the existing
   * value for that field is kept. After a new version of the product is
   * successfully created, it waits for confirmation that the user wants to
   * replace the old product with the edited one. 
   * Exits to the main menu if no product is found, if the user enters 
   * an existing id or if the user enters invalid values for the fields.
   * 
   * @param A scanner to get the user's input.
   */
  private void editProduct(Scanner scanner) {
    String oldId = findProduct(scanner);
    if (oldId == null) {
      return;
    }

    try {
      System.out.println("\n---The product is---");
      System.out.println(inventory.getProductFormattedString(oldId));

      System.out.println("Write --exit to return to the menu");
      System.out.println("Press enter to keep a field the same\n");
      System.out.print("Enter the id: ");
      String input = scanner.next();
      if (input.equals("--exit")) {
        return;
      }
      final String newId = input;
      
      System.out.print("\nEnter the description: ");
      input = scanner.next();
      if (input.equals("--exit")) {
        return;
      }
      final String description = input;
      
      System.out.print("\nEnter the price (must be greater than 0): ");
      input = scanner.next();
      if (input.equals("--exit")) {
        return;
      }
      final String price = input;

      System.out.print("\nEnter the brand: ");
      input = scanner.next();
      if (input.equals("--exit")) {
        return;
      }
      final String brand = input;

      System.out.print("\nEnter the weight (must be greater than 0): ");
      input = scanner.next();
      if (input.equals("--exit")) {
        return;
      }
      final String weight = input;

      System.out.print("\nEnter the length (must be greater than 0): ");
      input = scanner.next();
      if (input.equals("--exit")) {
        return;
      }
      final String length = input;

      System.out.print("\nEnter the height (must be greater than 0): ");
      input = scanner.next();
      if (input.equals("--exit")) {
        return;
      }
      final String height = input;

      System.out.print("\nEnter the color: ");
      input = scanner.next();
      if (input.equals("--exit")) {
        return;
      }
      final String color = input;

      System.out.print("\nEnter the quantity (must be greater than or equal to 0): ");
      input = scanner.next();
      if (input.equals("--exit")) {
        return;
      }
      final String quantity = input;

      System.out.print("\nEnter the category (must be 1, 2, 3 or 4): ");
      input = scanner.next();
      if (input.equals("--exit")) {
        return;
      }
      final String category = input;

      Product editedProduct = inventory.getEditedProduct(
          oldId,
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

      System.out.println("\n---The edited product is---");
      System.out.println(editedProduct.getFormattedString());

      System.out.print("\nIs it okay to replace the old product with this? (y/n): ");
      input = scanner.next();
      if (input.equalsIgnoreCase("y")) {
        inventory.replaceProduct(oldId, editedProduct);
        System.out.println("\n---The product was replaced---");

      } else {
        System.out.println("\n---The product was not replaced---");
      }

    } catch (IllegalArgumentException e) {
      System.out.println("Invalid value for product: " + e.getMessage());

    } catch (Exception e) {
      System.out.println("Invalid input. Please try again.");
    }
  }

  /**
   * Adds two products from each category to the inventory.
   */
  private void addDefaultProducts() {
    try {
      inventory.addDefaultProducts();
      System.out.println("\n---Default products have been added---");

    } catch (Exception e) {
      System.out.println("\n---Products with matching ids already exist---");
    }
  }

  
  /**
   * Finds a product in the inventory from user input. The user can search
   * by id or description, or leave the input blank to retrieve all products.
   * The user does not need to enter the whole id or description. For the id search,
   * the user input must match from the start of the id, and the search is case sensitive
   * (A, AB and ABC will match with ABC, but BC or aB will not.) If multiple products
   * start with the user input, they are all returned. For the description search,
   * the words in the user input are simply matched with words in a product's 
   * description. The product(s) with the most matching words, regardless of order,
   * are returned. Exits to the main menu if at any point the user enters --exit,
   * or if no product is found. If any product is found, the user *must* either choose a product
   * or explicitly exit to the main menu. As long as the caller checks for a null return value
   * and exits if it gets it, this ensures caller is guaranteed to get a valid product id,
   * and they can safely call methods on the inventory.
   * 
   * @param A scanner to get the user's input.
   * @return The id of a product, or null if the user exits or no product is found.
   */
  private String findProduct(Scanner scanner) {
    System.out.println("\n---Search for a product by id or description---");
    System.out.print("Enter search term. Press enter for all products"
                      + " (--help for help, --exit to exit): ");

    String searchTerm = scanner.next();
    if (searchTerm.equals("--exit")) {
      return null;

    } else if (searchTerm.equals("--help")) {
      System.out.println("\nSearch by id or description. "
            + "You don't have to enter the whole id or description, only part of it.\n"
            + "ID's are case sensitive, and the input must match from the start of the ID:\n"
            + "AB and ABC will match with ABC, but BC or aB will not.\n"
            + "Descriptions are case-insensitive,"
            + "and words in the search must match whole words in the descriptions.\n"
            + "The description(s) with the most matching words will be shown.\n");
      System.out.print("\nEnter search term (--exit to exit): ");

      searchTerm = scanner.next();
    }

    if (searchTerm.equals("--exit")) {
      return null;
    }

    List<Product> matches = new ArrayList<>();
    if (searchTerm.equals("")) {
      matches = inventory.getAllProducts();

    } else {
      matches = inventory.getProductsById(searchTerm);
      if (matches.size() == 0) {
        matches = inventory.getProductsByDescription(searchTerm);
      }
    }
    
    if (matches.size() == 0) {
      System.out.println("\n---No matches found---");
      return null;
    }

    System.out.println("\n---Matching products---");
    for (int i = 0; i < matches.size(); i++) {
      System.out.println("\nOption " + (i + 1) + ":\n"
                  + "\tID: " + matches.get(i).getId()
                  + "\n\tDescription: " + matches.get(i).getDescription());
    }

    boolean chosenProduct = false;
    String productId = "";
    while (!chosenProduct) {
      System.out.print("\nEnter option number (--exit to exit): ");

      int chosenOption = -1;
      String input = scanner.next();
      if (input.equals("--exit")) {
        return null;
      }

      try {
        chosenOption = Integer.parseInt(input);

      } catch (Exception e) {
        System.out.println("Invalid option. Please try again.");
        continue;
      }

      if (chosenOption > 0 && chosenOption <= matches.size()) {
        chosenProduct = true;
        productId = matches.get(chosenOption - 1).getId();

      } else {
        System.out.println("Invalid option. Please try again.");
        continue;
      }
    }
    
    return productId;
  }

  public static void main(String[] args) {
    Client client = new Client();
    client.run();
  }
}
