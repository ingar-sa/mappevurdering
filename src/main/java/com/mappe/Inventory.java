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

  public void deleteProduct(String id) {
    Product product = findProductsById(id).get(0);
    inventory.remove(product);
  }

  public void increaseProductQuantity(String id, int quantity) {
    Product product = findProductsById(id).get(0);
    product.setQuantity(product.getQuantity() + quantity);
    product.printFormatted();
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
    List<Product> products = findProductsById(searchTerm);
    if (products.isEmpty()) {
      products = findProductByDescription(searchTerm);
    }

    if (products.isEmpty()) {
      return products;
    }

    List<Product> deepCopiedProducts = new ArrayList<Product>();
    for (Product product : products) {
      deepCopiedProducts.add(new Product(product));
    }

    return deepCopiedProducts;
  }

  public void editProduct(
      String id,
      String newId,
      String description,
      int price,
      String brand,
      double weight,
      double length,
      double height,
      String color,
      int quantity,
      int category) {

    Product product = findProductsById(id).get(0);
    if (!isUniqueID(newId)) {
      throw new IllegalArgumentException("The id is not unique.");
    }

    if (newId != null) {
      product.setId(newId);
    }

    if (description != null) {
      product.setDescription(description);
    }

    if (price != -1) {
      product.setPrice(price);
    }
    
    if (brand != null) {
      product.setBrand(brand);
    }

    if (weight != -1) {
      product.setWeight(weight);
    }

    if (length != -1) {
      product.setLength(length);
    }

    if (height != -1) {
      product.setHeight(height);
    }

    if (color != null) {
      product.setColor(color);
    }

    if (quantity != -1) {
      product.setQuantity(quantity);
    }

    if (category != -1) {
      product.setCategory(category);
    }
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
        products.add(product);
      } else if (words == maxWords) {
        products.add(product);
      }
    }

    return products;
  }

  private List<Product> findProductsById(String searchId) {
    List<Product> products = new ArrayList<Product>();
    for (Product product : inventory) {
      if (product.getId().startsWith(searchId)) {
        products.add(product);
      }
    }

    return products;
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
