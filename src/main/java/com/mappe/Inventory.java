package com.mappe;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class Inventory {

  private final List<Product> inventory;

  /**
   * The constructor initializes an ArrayList for the products.
   */
  public Inventory() {
    inventory = new ArrayList<Product>();    
  }

  /**
   * Adds a product to the inventory.
   * The id must be unique, and all arguments must 
   * be valid as described in the Product class constructor.
   * 
   * @param id The id of the product. 
   * @param description A string that describes the product.
   * @param price The price of the product.
   * @param brand The brand of the product.
   * @param weight The weight of the product.
   * @param length The length of the product.
   * @param height The height of the product.
   * @param color The color of the product.
   * @param quantity The number of items in stock.
   * @param category The category of the product.
   */
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
      Category category) {
    
    if (isExistingId(id)) {
      throw new IllegalArgumentException("The id is not unique.");
    }

    Product product = new Product(
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
    inventory.add(product);
    
  }

  /**
   * Deletes a product from the inventory.
   * The caller must ensure that the id is valid.
   * 
   * @param id The id of the product.
   */
  public void deleteProduct(String id) {
    Product product = findProductWithId(id);
    inventory.remove(product);
  }

  /**
  * Increase the quantity of a product.
  * The caller must ensure that the id is valid.
  *
  * @param id The id of the product.
  * @param quantity The amount to increase the quantity by.
  */
  public void increaseProductQuantity(String id, int quantity) {
    if (quantity <= 0) {
      throw new IllegalArgumentException("The quantity must be greater than 0.");
    }

    Product product = findProductWithId(id);
    product.setQuantity(product.getQuantity() + quantity);
  }

  /**
   * Decrease the quantity of a product.
   * The caller must ensure that the id is valid.
   * 
   * @param id The id of the product.
   * @param quantity The amount to decrease the quantity by.
   */
  public void decreaseProductQuantity(String id, int quantity) {
    if (quantity <= 0) {
      throw new IllegalArgumentException("The quantity must be greater than 0.");
    }

    Product product = findProductWithId(id);
    product.setQuantity(product.getQuantity() - quantity);
  }

  /**
   * Get the formatted string for a product.
   * The caller must ensure that the id is valid.
   * 
   * @param id The id of the product.
   * @return A string that contains the product's information.
   */
  public String getProductFormattedString(String id) {
    Product product = findProductWithId(id);
    return product.getFormattedString();
  }

  /**
   * Returns an edited version of the old product with the new information.
   * It takes in strings for all the parameters in the Product constructor,
   * and if they're not empty, changes the value, otherwise, it keeps the old value.
   * All new values must be valid as described in the Product class constructor.
   * The caller must ensure that the id of the existing product is valid.
   * 
   * @param oldId The id of the product to be edited.
   * @param newId The new id of the product.
   * @param description The new description of the product. 
   * @param price The new price of the product.
   * @param brand The new brand of the product.
   * @param weight The new weight of the product.
   * @param length The new length of the product.
   * @param height The new height of the product.
   * @param color The new color of the product.
   * @param quantity The new quantity of the product.
   * @param category The new category of the product.
   * 
   * @return A new product with the updated information.
   */
  public Product getEditedProduct(
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

    Product oldProduct = findProductWithId(oldId);
    Product newProduct = new Product(oldProduct);  

    if (!newId.equals("")) {
      if (isExistingId(newId)) {
        throw new IllegalArgumentException("The id is not unique.");
      }
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
      newProduct.setCategory(Category.getCategoryFromString(category));
    }

    return newProduct;
  }

  /**
   * Replace the product with the old id with the new product.
   * The caller must ensure that the id is valid.
   * 
   * @param oldId The id of the product you want to replace.
   * @param newProduct The new product to replace the 
   */
  public void replaceProduct(String oldId, Product newProduct) {
    Product product = findProductWithId(oldId);
    inventory.set(inventory.indexOf(product), new Product(newProduct));
  }

  /**
   * Returns a deep-copy of the product with the given id.
   * The caller must ensure that the id is valid.
   * 
   * @param id The id of the product to be retrieved.
   * @return A deep-copy of the product.
   */
  public Product getProductById(String id) {
    Product product = findProductWithId(id);
    return new Product(product);
  }

  /**
   * Returns a list of deep-copies of all the products in the inventory.
   * 
   * @return A list of deep-copies.
   */
  public List<Product> getAllProducts() {
    List<Product> deepCopiedProducts = new ArrayList<Product>();
    for (Product product : inventory) {
      deepCopiedProducts.add(new Product(product));
    }

    return deepCopiedProducts;
  }

  /**
   * Find products that match either the id or the description.
   * If the search term is empty, return all products, otherwise return products 
   * that match the search term by ID, or by description, 
   * or return an empty list if no products match.
   * 
   * @param searchTerm The search term to use to find products.
   * @return A list of deep-copies of the matching products.
   */
  public List<Product> findProducts(String searchTerm) {
    if (searchTerm.equals("")) {
      return getAllProducts();
    }

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

  
  private List<Product> findProductByDescription(String searchTerm) {
    List<Product> products = new ArrayList<Product>();
    // Remove all non-alphanumeric characters and split the search term into words.
    String[] splitSearchTerm = searchTerm.replaceAll("[^a-zA-Z0-9 ]", "")
                                        .toLowerCase().split("\\s+");
    int maxWords = 0;

    for (Product product : inventory) {
      // Remove all non-alphanumeric characters and split the description into words.
      String[] splitDescription = product.getDescription()
                                        .replaceAll("[^a-zA-Z0-9 ]", "")
                                        .toLowerCase().split("\\s+");
      int matchingWords = 0;
      for (String searchWord : splitSearchTerm) {
        for (String descriptionWord : splitDescription) {
          if (descriptionWord.equalsIgnoreCase(searchWord)) {
            matchingWords++;
          }
        }
      }
      
      if (matchingWords == 0) {
        continue;

      } else if (matchingWords > maxWords) {
        maxWords = matchingWords;
        products.clear();
        products.add(product);

      } else if (matchingWords == maxWords) {
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

  /**
   * Adds two default products from each category to the inventory.
   */
  public void addDefaultProducts() {
    addProduct(
        "A1AA", 
        "Grey floor laminate. 20mX5m",
        100, 
        "Floors 'r us", 
        5.0, 
        20.0, 
        5.0, 
        "Mixed grey",
        352, 
        Category.getCategoryFromString("1")
    );

    addProduct(
        "A1AB", 
        "Black floor laminate. 20mX5m",
        100, 
        "Floors 'r us", 
        50.0,
        20.0, 
        5.0, 
        "Black",
        278,
        Category.getCategoryFromString("1")
    );

    addProduct(
        "B1AA",
        "Double pane window. 1.5mX2m",
        1000,
        "Windows 'r us",
        7.0,
        1.5, 
        2.0, 
        "Clear", 
        72, 
        Category.getCategoryFromString("2")
    );

    addProduct(
        "B1AB",
        "Double pane window. 2.5mX1m",
        1500,
        "Windows 'r us",
        10.0,
        2.5, 
        1.0, 
        "Clear", 
        56, 
        Category.getCategoryFromString("2")
    );

    addProduct(
        "C1AA", 
        "White door. 2mX1m", 
        2000,
        "Doors 'r us",
        15.0,
        1.0, 
        2.0, 
        "White", 
        17,
        Category.getCategoryFromString("3")
    );

    addProduct(
        "C1AB", 
        "Brown door. 2mX1m", 
        2000,
        "Doors 'r us",
        15.0,
        1.0, 
        2.0, 
        "Brown",
        31,
        Category.getCategoryFromString("3")
    );

    addProduct(
        "D1AA",
        "Plywood. 1mX1m",
        25,
        "Bobs wood", 
        2.5, 
        1.0, 
        1.0, 
        "Beige", 
        522, 
        Category.getCategoryFromString("4")
    );

    addProduct(
        "D1AB",
        "Plywood. 3mX1m",
        40,
        "Bobs wood", 
        6.2, 
        3.0, 
        1.0, 
        "Beige", 
        407, 
        Category.getCategoryFromString("4")
    );
  }

  private Product findProductWithId(String id) {
    for (Product product : inventory) {
      if (product.getId().equals(id)) {
        return product;
      }
    }

    throw new NoSuchElementException("The product does not exist.");
  }

  private boolean isExistingId(String id) {
    for (Product product : inventory) {
      if (product.getId().equals(id)) {
        return true;
      }
    }

    return false;
  }

}
