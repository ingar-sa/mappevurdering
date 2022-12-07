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

  public Inventory(boolean debug) {
    inventory = new ArrayList<Product>();    
    if (debug) {
      addDefaultProducts();
    }
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
    
    if (!isUniqueID(id)) {
      throw new IllegalArgumentException("The id is not unique.");
    }

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
   * The client handles searching for the product and 
   * the user choosing which product to delete.
   * It then passes the id of the product to this method. 
   */
  public void deleteProduct(String id) {
    Product product = findProductsById(id).get(0);
    inventory.remove(product);
  }

  /*
   * The increase and decrease methods should get the id to a specific product
   * instead of using the search function here.
   * When the user wants to increase or decrease the quantity of a product,
   * the client handles searching for and selecting the specific product.
   * 
   */

  public void increaseProductQuantity(String id, int quantity) {
    Product product = findProductsById(id).get(0);
    product.setQuantity(product.getQuantity() + quantity);
  }

  public void decreaseProductQuantity(String id, int quantity) {
    Product product = findProductsById(id).get(0);
    product.setQuantity(product.getQuantity() - quantity);
  }

  public void printAllProducts() {
    inventory.stream().forEach(product -> product.printFormatted());
  }

  public void printSingleProduct(String id) {
    Product product = findProductsById(id).get(0);
    product.printFormatted();
  }

  public List<Product> findProducts(String searchTerm) {
    List<Product> products = new ArrayList<Product>();

    try {
      products = findProductsById(searchTerm);
    } catch (NoSuchElementException e) {
      try {
        products = findProductByDescription(searchTerm);
      } catch (NoSuchElementException e2) {
        throw new NoSuchElementException("No products matches the search term: " + searchTerm);
      }
    }

    return products;
  }

  private List<Product> findProductByDescription(String searchTerm) {
    List<Product> products = new ArrayList<Product>();
    String[] splitSearchTerm = searchTerm.split(" ");
    int maxWords = 0;

    for (Product product : inventory) {
      String[] splitDescription = product.getDescription().split(" ");
      int words = 0;
      for (String searchWord : splitSearchTerm) {
        for (String descriptionWord : splitDescription) {
          if (descriptionWord.equalsIgnoreCase(searchWord)) {
            words++;
          }
        }
      }

      if (words == 0) {
        continue;
      } else if (words > maxWords) {
        maxWords = words;
        products.clear();
        products.add(new Product(product));
      } else if (words == maxWords) {
        products.add(new Product(product));
      }
    }

    return products;
  }

  /*
   * The method should return a list of products that match the given id.
   */
  private List<Product> findProductsById(String searchId) {
    List<Product> products = new ArrayList<Product>();
    for (Product product : inventory) {
      if (product.getId().startsWith(searchId)) {
        products.add(new Product(product));
      }
    }
    
    if (!products.isEmpty()) {
      return products;
    }

    throw new NoSuchElementException("No product with id: " + searchId);
  }

  private boolean isUniqueID(String id) {
    for (Product product : inventory) {
      if (product.getId().equals(id)) {
        return false;
      }
    }

    return true;
  }

  private void addDefaultProducts() {
    addProduct(
        "A123", 
        "A description of the product", 
        1000, 
        "Brand", 
        1.0, 
        1.0, 
        1.0, 
        "Color", 
        1, 
        1
    );

    addProduct(
        "B123", 
        "a description of the product", 
        1000, 
        "Brand", 
        1.0, 
        1.0, 
        1.0, 
        "Color", 
        1, 
        1
    );

    addProduct(
        "C123", 
        "This is a product", 
        1000, 
        "Brand", 
        1.0, 
        1.0, 
        1.0, 
        "Color", 
        1, 
        1
    );

    addProduct(
        "C1A3", 
        "Another product", 
        1000, 
        "Brand", 
        1.0, 
        1.0, 
        1.0, 
        "Color", 
        1, 
        1
    );

    addProduct(
        "E123", 
        "A hammer", 
        1000, 
        "Brand", 
        1.0, 
        1.0, 
        1.0, 
        "Color", 
        1, 
        1
    );
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
