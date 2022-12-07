package com.mappe;

public class Product {
    
  private String id;
    
  private String description;
  
  private int price;
  
  private String brand;
  
  private double weight;
  
  private double length;
  
  private double height;
  
  private String color;
  
  private int quantity;
  
  private int category;

  /**
   * The constructor takes in all the parameters and assigns them to the member fields.
   * @param id The id of the item.
   * @param description The description of the item.
   * @param price The price of the item. Must be greater than 0.
   * @param brand The brand of the item.
   * @param weight The weight of the item. Must be greater than 0.
   * @param length The length of the item. Must be greater than 0.
   * @param height The height of the item. Must be greater than 0.
   * @param color The color of the item.
   * @param quantity The quantity of the item. Must be greater than or equal to 0.
   * @param category The category of the item. Must be either 1, 2, 3, or 4.
   */
  public Product(String id, 
              String description,
              int price,
              String brand,
              double weight,
              double length,
              double height,
              String color,
              int quantity,
              int category) {
    this.id = id;
    this.description = description;
    this.setPrice(price);
    this.brand = brand;
    this.setWeight(weight);
    this.setLength(length);
    this.setHeight(height);
    this.color = color;
    this.setQuantity(quantity);
    this.setCategory(category);
  }

  /**
   * Copy constructor for the Item class.
   * @param product The item to be copied.
   */
  public Product(Product product) {
    this(product.getId(),
         product.getDescription(),
         product.getPrice(),
         product.getBrand(),
         product.getWeight(),
         product.getLength(),
         product.getHeight(),
         product.getColor(),
         product.getQuantity(),
         product.getCategory());
  }

  /**
   * Change the description of the item.
   * @param description The new description of the item.
   */
  public void setDesciption(String description) {
    this.description = description;
  }

  /**
   * Change the price of the item. The price must be greater than 0.
   * @param price The new price of the item.
   */
  public void setPrice(int price) {
    if (!assertPositiveNumber(price)) {
      throw new IllegalArgumentException("The price must be greater than 0.");
    }
    this.price = price;
  }

  /**
   * Change the brand of the item.
   * @param brand The new brand of the item.
   */
  public void setBrand(String brand) {
    this.brand = brand;
  }

  /**
   * Change the weight of the item. The weight must be greater than 0.
   * @param weight The new weight of the item.
   */
  public void setWeight(double weight) {
    if (!assertPositiveNumber(weight)) {
      throw new IllegalArgumentException("The weight must be greater than 0.");
    }
    this.weight = weight;
  }

  /**
   * Change the length of the item. The length must be greater than 0.
   * @param length The new length of the item.
   */
  public void setLength(double length) {
    if (!assertPositiveNumber(length)) {
      throw new IllegalArgumentException("The length must be greater than 0.");
    }
    this.length = length;
  }

  /**
   * Change the height of the item. The height must be greater than 0.
   * @param height The new height of the item.
   */
  public void setHeight(double height) {
    if (!assertPositiveNumber(height)) {
      throw new IllegalArgumentException("The height must be greater than 0.");
    }
    this.height = height;
  }

  /**
   * Change the color of the item.
   * @param color The new color of the item.
   */
  public void setColor(String color) {
    this.color = color;
  }

  /**
   * Change the quantity of the item. The quantity must be greater than or equal to 0.
   * @param quantity The new quantity of the item.
   */
  public void setQuantity(int quantity) {
    if (!assertPositiveNumber(quantity)) {
      throw new IllegalArgumentException("The quantity must be greater than or equal to 0.");
    }
    this.quantity = quantity;
  }

  /**
   * Change the category of the item. The category must be either 1, 2, 3, or 4.
   * @param category The new category of the item.
   */
  public void setCategory(int category) {
    if (category < 1 || category > 4) {
      throw new IllegalArgumentException("The category must be either 1, 2, 3, or 4.");
    }
    this.category = category;
  }

  public String getId() {
    return id;
  }

  public String getDescription() {
    return description;
  }

  public int getPrice() {
    return price;
  }

  public String getBrand() {
    return brand;
  }

  public double getWeight() {
    return weight;
  }

  public double getLength() {
    return length;
  }

  public double getHeight() {
    return height;
  }

  public String getColor() {
    return color;
  }

  public int getQuantity() {
    return quantity;
  }

  // TODO(ingar): xShould be turned into an enum at some point
  public int getCategory() {
    return category;
  }

  public void printFormatted() {
    System.out.println("ID:          " + id);
    System.out.println("Description: " + description);
    System.out.println("Price:       " + price);
    System.out.println("Brand:       " + brand);
    System.out.println("Weight:      " + weight);
    System.out.println("Length:      " + length);
    System.out.println("Height:      " + height);
    System.out.println("Color:       " + color);
    System.out.println("Quantity:    " + quantity);
    System.out.println("Category:    " + category);
    System.out.println('\n');
  }

  private boolean assertValidCategory(int category) {
    if (category < 1 || category > 4) {
      return false;
    }

    return true;
  }

  private void assertValidItemQuantity(int quantity) {
    if (quantity < 0) {
      throw new IllegalArgumentException("Item quantity must be greater than or equal to 0");
    }
  }

  private boolean assertPositiveNumber(double number) {
    if (number <= 0) {
      return true;
    }

    return false;
  }

  private boolean assertPositiveNumber(int number) {
    if (number <= 0) {
      return true;
    }

    return false;
  }

  @Override
  public String toString() {
    return "Item [id=" + id + ", description=" + description + ", price=" + price + ", brand=" 
      + brand + ", weight=" + weight + ", length=" + length + ", height=" + height + ", color=" 
      + color + ", quantity=" + quantity + ", category=" + category + "]";
  }

}
