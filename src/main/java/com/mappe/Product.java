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
  
  private Category category;

  /**
   * The constructor takes in all the parameters, checks if they are valid and sets them.
   * @param id The id of the product. Cannot be empty or null.
   * @param description The description of the product.
   * @param price The price of the product. Must be greater than 0.
   * @param brand The brand of the product. Cannot be empty or null.
   * @param weight The weight of the product. Must be greater than 0.
   * @param length The length of the product. Must be greater than 0.
   * @param height The height of the product. Must be greater than 0.
   * @param color The color of the product. Cannot be empty or null.
   * @param quantity The quantity of the product. Must be greater than or equal to 0.
   * @param category The category of the product. Must be a valid category.
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
              Category category) {
    this.setId(id);
    this.setDescription(description);
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
    this(product.id,
        product.description,
        product.price,
        product.brand,
        product.weight,
        product.length,
        product.height,
        product.color,
        product.quantity,
        product.category);
  }

  /**
   * Changes the id of the product. Cannot be null or empty.
   * @param id The new id.
   */
  public void setId(String id) {
    if (id == null || id.isBlank()) {
      throw new IllegalArgumentException("The id cannot be null or empty.");
    }

    this.id = id.toUpperCase();
  }

  /**
   * Changes the description of the product. Cannot be null or empty.
   * @param description The new description of the product.
   */
  public void setDescription(String description) {
    if (description == null || description.isBlank()) {
      throw new IllegalArgumentException("The description cannot be null or empty.");
    }

    this.description = description;
  }

  /**
   * Changes the price of the product. Must be greater than 0.
   * @param price The new price of the product.
   */
  public void setPrice(int price) {
    if (price <= 0) {
      throw new IllegalArgumentException("The price must be greater than 0.");
    }
    this.price = price;
  }

  /**
   * Changes the brand of the product. Cannot be null or empty.
   * @param brand The new brand of the product.
   */
  public void setBrand(String brand) {
    if (brand == null || brand.isBlank()) {
      throw new IllegalArgumentException("The brand cannot be null or empty.");
    }
    this.brand = brand;
  }

  /**
   * Changes the weight of the product. Must be greater than 0.
   * @param weight The new weight of the product.
   */
  public void setWeight(double weight) {
    if (weight <= 0) {
      throw new IllegalArgumentException("The weight must be greater than 0.");
    }
    this.weight = weight;
  }

  /**
   * Changes the length of the product. Must be greater than 0.
   * @param length The new length of the product.
   */
  public void setLength(double length) {
    if (length <= 0) {
      throw new IllegalArgumentException("The length must be greater than 0.");
    }
    this.length = length;
  }

  /**
   * Changes the height of the product. Must be greater than 0.
   * @param height The new height of the product.
   */
  public void setHeight(double height) {
    if (height <= 0) {
      throw new IllegalArgumentException("The height must be greater than 0.");
    }
    this.height = height;
  }

  /**
   * Changes the color of the product. Cannot be null or empty.
   * @param color The new color.
   */
  public void setColor(String color) {
    if (color == null || color.isBlank()) {
      throw new IllegalArgumentException("The color cannot be null or empty.");
    }
    this.color = color;
  }

  /**
   * Changes the quantity of the product. Must be greater than or equal to 0.
   * @param quantity The new quantity of the product.
   */
  public void setQuantity(int quantity) {
    if (quantity < 0) {
      throw new IllegalArgumentException("The quantity must be greater than or equal to 0.");
    }
    this.quantity = quantity;
  }

  
  /**
   * Changes the category of the product. Cannot be null.
   * @param category The new category of the product.
   */
  public void setCategory(Category category) {
    if (category == null) {
      throw new IllegalArgumentException("The category cannot be null.");
    }
    this.category = category;
  }

  /**
   * @return The id of the product.
   */
  public String getId() {
    return id;
  }

  /**
   * @return The description of the product.
   */
  public String getDescription() {
    return description;
  }

  /**
   * @return The price of the product.
   */
  public int getPrice() {
    return price;
  }

  /**
   * @return The brand of the product.
   */
  public String getBrand() {
    return brand;
  }

  /**
   * @return The weight of the product.
   */
  public double getWeight() {
    return weight;
  }

  /**
   * @return The length of the product.
   */
  public double getLength() {
    return length;
  }

  /**
   * @return The height of the product.
   */
  public double getHeight() {
    return height;
  }

  /**
   * @return The color of the product.
   */
  public String getColor() {
    return color;
  }

  /**
   * @return The quantity of the product.
   */
  public int getQuantity() {
    return quantity;
  }

  /**
   * @return The category of the product.
   */
  public Category getCategory() {
    return category;
  }

  /**
   * Prints a formatted presentation of the products information.
   */
  public String getFormattedString() {
    return """
        ID:          %s
        Description: %s
        Price:       %d
        Brand:       %s
        Weight:      %f
        Length:      %f
        Height:      %f
        Color:       %s
        Quantity:    %d
        Category:    %s
        
        """
        .formatted(
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
  }

  @Override
  public String toString() {
    return "Product [id=" + id + ", description=" + description + ", price=" + price + ", brand=" 
      + brand + ", weight=" + weight + ", length=" + length + ", height=" + height + ", color=" 
      + color + ", quantity=" + quantity + ", category=" + category + "]";
  }
}
