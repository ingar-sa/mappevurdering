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
      String category) {
    
    if (isExistingId(id)) {
      throw new IllegalArgumentException("The id is not unique.");
    }

    try {
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
          getCategoryFromString(category)
      );
      inventory.add(product);
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException(category + " is not a valid category.");
    }
  }

  public void deleteProduct(String id) {
    Product product = findProductWithId(id);
    if (product == null) {
      throw new NoSuchElementException("The product does not exist.");
    }
    
    inventory.remove(product);
  }

  public void increaseProductQuantity(String id, int quantity) {
    if (quantity <= 0) {
      throw new IllegalArgumentException("The quantity must be greater than 0.");
    }

    Product product = findProductWithId(id);
    if (product == null) {
      throw new NoSuchElementException("The product does not exist.");
    }
    
    product.setQuantity(product.getQuantity() + quantity);
  }

  public void decreaseProductQuantity(String id, int quantity) {
    if (quantity <= 0) {
      throw new IllegalArgumentException("The quantity must be greater than 0.");
    }

    Product product = findProductWithId(id);
    if (product == null) {
      throw new NoSuchElementException("The product does not exist.");
    }
    
    product.setQuantity(product.getQuantity() - quantity);
  }

  public String getProductFormattedString(String id) {
    Product product = findProductWithId(id);
    if (product == null) {
      throw new NoSuchElementException("The product does not exist.");
    }
    return product.getFormattedString();
  }

  /**
   * It takes a bunch of strings, and if they're not empty, it sets the corresponding fields of the
   * product
   * 
   * @param oldId The id of the product to be edited.
   * @param newId The new id of the product.
   * @param description The description of the product.
   * @param price The price of the product.
   * @param brand The brand of the product.
   * @param weight The weight of the product in kilograms.
   * @param length The length of the product.
   * @param height The height of the product.
   * @param color The color of the product.
   * @param quantity The quantity of the product.
   * @param category The category of the product.
   * @return A new product with the updated information.
   * 
   * @throws IllegalArgumentException If the id is not unique, or the category is invalid.
   */
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

    Product oldProduct = findProductWithId(oldId);
    if (oldProduct == null) {
      throw new NoSuchElementException("The product does not exist.");
    }

    Product newProduct = new Product(oldProduct);  

    if (!newId.equals("")) {
      if (!isExistingId(newId)) {
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
      newProduct.setCategory(getCategoryFromString(category));
    }

    return newProduct;
  }

  public void replaceProduct(Product newProduct, String oldId) {
    Product product = findProductWithId(oldId);
    if (product == null) {
      throw new NoSuchElementException("The product does not exist.");
    }

    inventory.set(inventory.indexOf(product), new Product(newProduct));
  }

  public Product getProductById(String id) {
    Product product = findProductWithId(id);
    if (product == null) {
      throw new NoSuchElementException("The product does not exist.");
    }

    return new Product(product);
  }

  public List<Product> getAllProducts() {
    List<Product> deepCopiedProducts = new ArrayList<Product>();
    for (Product product : inventory) {
      deepCopiedProducts.add(new Product(product));
    }

    return deepCopiedProducts;
  }

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

  private Product findProductWithId(String id) {
    for (Product product : inventory) {
      if (product.getId().equals(id)) {
        return product;
      }
    }

    return null;
  }

  private boolean isExistingId(String id) {
    for (Product product : inventory) {
      if (product.getId().equals(id)) {
        return true;
      }
    }

    return false;
  }

  private Category getCategoryFromString(String category) {
    switch (category) {
      case "1":
        return Category.FLOOR_LAMINATES;
      case "2":
        return Category.WINDOWS;
      case "3":
        return Category.DOORS;
      case "4":
        return Category.LUMBER;
      default:
        throw new IllegalArgumentException(category + " is not a valid category.");
    }
  }

  public void addDefaultProducts() {
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
        Category.FLOOR_LAMINATES
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
        Category.WINDOWS
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
        Category.DOORS
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
        Category.LUMBER
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
        Category.FLOOR_LAMINATES
    );

    addProduct(
        "F123", 
        "  A     description of the product     ", 
        1000, 
        "Brand", 
        1.0, 
        1.0, 
        1.0, 
        "Color", 
        1, 
        Category.WINDOWS
    );

    addProduct(
        "G123", 
        "         t   ",
        1000, 
        "Brand", 
        1.0, 
        1.0, 
        1.0, 
        "Color", 
        1, 
        Category.DOORS
    );
  }

}
