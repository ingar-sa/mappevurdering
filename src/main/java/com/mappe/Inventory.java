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
      int category) {
    
    if (isExistingId(id)) {
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
    inventory.remove(findProductById(id));
  }

  public void increaseProductQuantity(String id, int quantity) {
    if (quantity <= 0) {
      throw new IllegalArgumentException("The quantity must be greater than 0.");
    }

    Product product = findProductById(id);
    product.setQuantity(product.getQuantity() + quantity);
  }

  public void decreaseProductQuantity(String id, int quantity) {
    if (quantity <= 0) {
      throw new IllegalArgumentException("The quantity must be greater than 0.");
    }

    Product product = findProductById(id);
    product.setQuantity(product.getQuantity() - quantity);
  }

  // TODO: This needs to be fixed so printing happens in client
  public String getProductFormattedString(String id) {
    return findProductById(id).getFormattedString();
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
      newProduct.setCategory(Integer.parseInt(category));
    }

    return newProduct;
  }

  public void replaceProduct(Product newProduct, String oldId) {
    // TODO: This can probably be done better
    inventory.set(inventory.indexOf(findProductById(oldId)), new Product(newProduct));
  }

  public Product getProductById(String id) {
    return new Product(findProductById(id));
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

  private Product findProductById(String searchId) {
    List<Product> products = new ArrayList<Product>();
    for (Product product : inventory) {
      if (product.getId().startsWith(searchId)) {
        products.add(product);
      }
    }

    if (products.isEmpty()) {
      throw new NoSuchElementException("The product with id " + searchId + " does not exist.");
    }

    return products.get(0);
  }

  private boolean isExistingId(String id) {
    for (Product product : inventory) {
      if (product.getId().equals(id)) {
        return true;
      }
    }

    return false;
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
