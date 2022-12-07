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
   * @param id The id of the product.
   * @param description The description of the product.
   * @param price The price of the product. Must be greater than 0.
   * @param brand The brand of the product.
   * @param weight The weight of the product. Must be greater than 0.
   * @param length The length of the product. Must be greater than 0.
   * @param height The height of the product. Must be greater than 0.
   * @param color The color of the product.
   * @param quantity The quantity of the product. Must be greater than or equal to 0.
   * @param category The category of the product. Must be either 1, 2, 3, or 4.
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
    this.setId(id);
    this.setDesciption(description);
    this.setPrice(price);
    this.setBrand(brand);
    this.setWeight(weight);
    this.setLength(length);
    this.setHeight(height);
    this.setColor(color);
    this.setQuantity(quantity);
    this.setCategory(category);
  }

  /**
   * Copy constructor for the Product class.
   * @param product The product to be copied.
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
   * Change the id of the product.
   * @param id The new id.
   */
  public void setId(String id) {
    this.id = id;
  }

  /**
   * Change the description of the product.
   * @param description The new description of the product.
   */
  public void setDesciption(String description) {
    this.description = description;
  }

  /**
   * Change the price of the product. The price must be greater than 0.
   * @param price The new price of the product.
   */
  public void setPrice(int price) {
    if (price <= 0) {
      throw new IllegalArgumentException("The price must be greater than 0.");
    }
    this.price = price;
  }

  /**
   * Change the brand of the product.
   * @param brand The new brand of the product.
   */
  public void setBrand(String brand) {
    this.brand = brand;
  }

  /**
   * Change the weight of the product. The weight must be greater than 0.
   * @param weight The new weight of the product.
   */
  public void setWeight(double weight) {
    if (weight <= 0) {
      throw new IllegalArgumentException("The weight must be greater than 0.");
    }
    this.weight = weight;
  }

  /**
   * Change the length of the product. The length must be greater than 0.
   * @param length The new length of the product.
   */
  public void setLength(double length) {
    if (length <= 0) {
      throw new IllegalArgumentException("The length must be greater than 0.");
    }
    this.length = length;
  }

  /**
   * Change the height of the product. The height must be greater than 0.
   * @param height The new height of the product.
   */
  public void setHeight(double height) {
    if (height <= 0) {
      throw new IllegalArgumentException("The height must be greater than 0.");
    }
    this.height = height;
  }

  /**
   * Change the color of the product.
   * @param color The new color.
   */
  public void setColor(String color) {
    this.color = color;
  }

  /**
   * Change the quantity of the product. The quantity must be greater than or equal to 0.
   * @param quantity The new quantity of the product.
   */
  public void setQuantity(int quantity) {
    if (quantity < 0) {
      throw new IllegalArgumentException("The quantity must be greater than or equal to 0.");
    }
    this.quantity = quantity;
  }

  /**
   * Change the category of the product. The category must be either 1, 2, 3, or 4.
   * @param category The new category of the product.
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

  /**
   * Prints a formatted presentation of the products information.
   */
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

  @Override
  public String toString() {
    return "Product [id=" + id + ", description=" + description + ", price=" + price + ", brand=" 
      + brand + ", weight=" + weight + ", length=" + length + ", height=" + height + ", color=" 
      + color + ", quantity=" + quantity + ", category=" + category + "]";
  }

}
