package com.mappe;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Represents the inventory of a warehouse, and contains a list 
 * of products and methods for manipulating the list and the products 
 * in the list. The management of the products is based on an id system,
 * with each product having a unique id. The user must provide the id 
 * of a product to the methods that manipulate them. To this end, 
 * the class provides methods for searching for products based on 
 * part of or the entire id, as well as being able to search based on 
 * the description of the product. The user can then use the id to
 * call methods that can change or retrieve the values of the product.
 * The class also provides methods for adding, deleting and editing
 * products.
 */
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
   * The caller must ensure the id is unique, and all arguments must 
   * be valid as described in the Product class constructor.
   * 
   * @param id The id of the product.
   * @param description The description of the product.
   * @param price The price of the product.
   * @param brand The brand of the product.
   * @param weight The weight of the product.
   * @param length The length of the product.
   * @param height The height of the product.
   * @param color The color of the product.
   * @param quantity The number of items in stock.
   * @param category The category of the product.
   * 
   * @throws IllegalArgumentException If the new id is not unique, or if any of the arguments are \
   *                                  invalid acccording to the Product class constructor.
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
   * The caller must ensure that the product exists.
   * 
   * @param id The id of the product.
   * @throws NoSuchElementException If the product doesn't exist.
   */
  public void deleteProduct(String id) {
    Product product = findProductWithId(id);
    inventory.remove(product);
  }

  /**
  * Increase the quantity of a product.
  * The caller must ensure that the product exists.
  *
  * @param id The id of the product.
  * @param quantity The amount to increase the quantity by. Must be greater than 0.
  * @throws IllegalArgumentException If the quantity is less than or equal to 0.
  * @throws NoSuchElementException If the product doesn't exist.
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
   * The caller must ensure that the product exists.
   * 
   * @param id The id of the product.
   * @param quantity The amount to decrease the quantity by. Must be greater than 0.
   * @throws IllegalArgumentException If the quantity is less than or equal to 0.
   * @throws NoSuchElementException If the product doesn't exist.
   */
  public void decreaseProductQuantity(String id, int quantity) {
    if (quantity <= 0) {
      throw new IllegalArgumentException("The quantity must be greater than 0.");
    }

    Product product = findProductWithId(id);
    product.setQuantity(product.getQuantity() - quantity);
  }

  /**
   * Get the formatted string from a product.
   * The caller must ensure that the product exists.
   * 
   * @param id The id of the product.
   * @return A formatted string of the product's information.
   * @throws NoSuchElementException If the product doesn't exist.
   */
  public String getProductFormattedString(String id) {
    Product product = findProductWithId(id);
    return product.getFormattedString();
  }

  /**
   * Returns an edited version of the old product with the updated information.
   * It takes strings for all the parameters in the Product constructor,
   * and if they're not empty, changes the value. Otherwise, it keeps the old value.
   * All new values must be valid as described in the Product class constructor.
   * The caller must ensure that the id of the existing product is valid,
   * and that the new id is unique. If the user has entered a new id,
   * and it is the same as the old id, the method will not throw an exception.
   * 
   * @param oldId Id of the product to be edited.
   * @param newId Empty string, or the new id of the product.
   * @param description Empty string, or the new description of the product. 
   * @param price Empty string, or the new price of the product.
   * @param brand Empty string, or the new brand of the product.
   * @param weight Empty string, or the new weight of the product.
   * @param length Empty string, or the new length of the product.
   * @param height Empty string, or the new height of the product.
   * @param color Empty string, or the new color of the product.
   * @param quantity Empty string, or the new quantity of the product.
   * @param category Empty string, or the new category of the product.
   * 
   * @return A new product with the updated information.
   * @throws IllegalArgumentException If the new id is not unique, or if any of the arguments are \
   *                                  invalid acccording to the Product class constructor.
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
      if (isExistingId(newId) && !newId.equals(oldId)) {
        throw new IllegalArgumentException("The new id must be unique.");
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
      newProduct.setCategory(Category.parseCategory(category));
    }

    return newProduct;
  }

  /**
   * Replace the product with the old id with the new product.
   * The caller must ensure that the product exists.
   * 
   * @param oldId The id of the product you want to replace.
   * @param newProduct The product to replace it with.
   * @throws IllegalArgumentException If the product doesn't exist.
   */
  public void replaceProduct(String oldId, Product newProduct) {
    Product product = findProductWithId(oldId);
    inventory.set(inventory.indexOf(product), new Product(newProduct));
  }

  /**
   * Returns a deep-copy of the product with the given id.
   * The caller must ensure that the product exists
   * 
   * @param id The id of the product to be retrieved.
   * @return A deep-copy of the product.
   * @throws IllegalArgumentException If the product doesn't exist.
   */
  public Product getProductById(String id) {
    Product product = findProductWithId(id);
    return new Product(product);
  }

  /**
   * Returns deep-copies of all the products in the inventory.
   * Returns an empty list if the inventory is empty.
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
   * Returns deep-copies the product(s) with ids that
   * match either part of or the entire search term.
   * The search is case-sensitive, and the search term must match
   * from the beginning of an id. If multiple ids match, they are all returned.
   * Returns an empty list if no products match.
   * 
   * @param searchTerm String to match with ids.
   * @return A list of deep-copies of the matching products.
   */
  public List<Product> getProductsById(String searchTerm) {
    List<Product> products = new ArrayList<Product>();
    for (Product product : inventory) {
      if (product.getId().startsWith(searchTerm)) {
        products.add(new Product(product));
      }
    }

    return products;
  }

  /**
   * Returns deep-copies of the product(s) that match the most words
   * in the search term. The search is canse-insensitive, and the words
   * in the search term must match whole words in the description.
   * If multiple descriptions contain the same amount of words
   * they are all returned. Returns an empty list if no products match.
   * 
   * @param searchTerm A string of words to match with the descriptions.
   * @return A list of deep-copies of the matching products.
  */
  public List<Product> getProductsByDescription(String searchTerm) {
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
        products.add(new Product(product));

      } else if (matchingWords == maxWords) {
        products.add(new Product(product));
      }
    }

    return products;
  }

  /**
   * Checks if a product with the id exists in the inventory.
   * Should be used before passing an id to other methods in
   * the class, unless the validity of the id is otherwise guaranteed.
   * 
   * @param id The id to check.
   * @return True if the id exists, false otherwise.
   */
  public boolean isExistingId(String id) {
    for (Product product : inventory) {
      if (product.getId().equals(id)) {
        return true;
      }
    }

    return false;
  }

  /**
   * Adds two default products from each category to the inventory.
   * The caller must ensure that there are no products with matching
   * ids in the inventory.
   * 
   * @throws IllegalArgumentException If there are products with matching ids.
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
        Category.parseCategory("1")
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
        Category.parseCategory("1")
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
        Category.parseCategory("2")
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
        Category.parseCategory("2")
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
        Category.parseCategory("3")
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
        Category.parseCategory("3")
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
        Category.parseCategory("4")
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
        Category.parseCategory("4")
    );
  }
  
  /**
   * Returns the product with the given id.
   * Used internally to manipulate data on a product.
   * 
   * @param id The id of the product.
   * @return The product.
   * @throws NoSuchElementException If the product does not exist.
   */
  private Product findProductWithId(String id) {
    for (Product product : inventory) {
      if (product.getId().equals(id)) {
        return product;
      }
    }

    throw new NoSuchElementException("The product does not exist.");
  }
}
