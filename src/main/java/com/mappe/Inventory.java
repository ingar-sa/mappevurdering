package com.mappe;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class Inventory {

  private List<Product> inventory;

  /**
   * The constructor initializes an ArrayList for the products.
   */
  public Inventory() {
    inventory = new ArrayList<Product>();    
  }

  public void deleteProduct() {
  }

  public void addProduct(
      String id, 
      String description,
      int price,
      String brand,
      double weight,
      double length,
      double height,
      String color,
      int quantity,
      int category) {

    inventory.add(
        new Product(
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
      )
    );
  }

  /*
   * The increase and decrease methods should get the id to a specific product
   * instead of using the search function here.
   * When the user wants to increase or decrease the quantity of a product,
   * the client handles searching for and selecting the specific product.
   * 
   */

  public void increaseProductQuantity(String id, int quantity) {
    Product product = findProductById(id);
    product.setQuantity(product.getQuantity() + quantity);
  }

  public void decreaseProductQuantity(String id, int quantity) {
    Product product = findProductById(id);
    product.setQuantity(product.getQuantity() - quantity);
  }

  public void printAllProducts() {
    inventory.stream().forEach(product -> product.printFormatted());
  }

  public List<Product> findProduct(String stringToMatch) {
    List<Product> products = new ArrayList<Product>();

    // Case sensitive search. ID's allways have uppercase letters
    for (Product product : inventory) {
      if (product.getId().startsWith(stringToMatch)) {
        products.add(new Product(product));
        break;
      }

      // Case insensitive search. 
      String[] splitDescription = product.getDescription().split(" ");
      for (String word : splitDescription) {
        if (word.toLowerCase().equals(stringToMatch.toLowerCase())) {
          products.add(new Product(product));
          break;
        }
      }
    }

    //TODO(ingar): Should I do handling for an empty list here?
    return products;
  }

  /*
   * The method should return a list of products that match the given id.
   */
  private Product findProductById(String searchId) {
    for (Product product : inventory) {
      if (product.getId().startsWith(searchId)) {
        return product;
      }
    }

    throw new NoSuchElementException("No product with id: " + searchId);
  }

  // public List<Product> findProductByDescription(String searchDescription) {
  //   ArrayList<Product> products = new ArrayList<Product>();
  //   for (Product product : inventory) {
  //     String[] splitDescription = product.getDescription().split(" ");
  //     for (String word : splitDescription) {
  //       if (word.equals(searchDescription)) {
  //         products.add(new Product(product));
  //         break;
  //       }
  //     }
  //   }
  //   //TODO(ingar): Should I do handling for an empty list here?
  //   return products;
  // }
  
}
