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

  public Product editProduct(
      String oldId,
      String newId,
      String description,
      String price,
      String brand,
      String weight,
      String length,
      String height,
      String color,
      String quantity,
      String category) {

    Product oldProduct = findProductsById(oldId).get(0);
    Product newProduct = new Product(oldProduct);

    if (!isUniqueID(newId)) {
      throw new IllegalArgumentException("The id is not unique.");
    }

    if (!newId.equals("")) {
      newProduct.setId(newId);
    }

    if (!description.equals("")) {
      newProduct.setDescription(description);
    }

    if (!price.equals("")) {
      newProduct.setPrice(Integer.parseInt(price));
    }
    
    if (!brand.equals("")) {
      newProduct.setBrand(brand);
    }

    if (!weight.equals("")) {
      newProduct.setWeight(Double.parseDouble(weight));
    }

    if (!length.equals("")) {
      newProduct.setLength(Double.parseDouble(length));
    }

    if (!height.equals("")) {
      newProduct.setHeight(Double.parseDouble(height));
    }

    if (!color.equals("")) {
      newProduct.setColor(color);
    }

    if (!quantity.equals("")) {
      newProduct.setQuantity(Integer.parseInt(quantity));
    }

    if (!category.equals("")) {
      newProduct.setCategory(Integer.parseInt(category));
    }

    return newProduct;
  }

  public void replaceProduct(Product newProduct, String oldId) {
    Product oldProduct = findProductsById(oldId).get(0);
    inventory.set(inventory.indexOf(oldProduct), new Product(newProduct));
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

}
