package com.mappe;

public class Item {
    
  private String id;
    
  private String description;
  
  private double price;
  
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
   * @param price The price of the item.
   * @param brand The brand of the item.
   * @param weight The weight of the item.
   * @param length The length of the item.
   * @param height The height of the item.
   * @param color The color of the item.
   * @param quantity The quantity of the item.
   * @param category The category of the item.
   */
  public Item(String id, 
              String description,
              double price,
              String brand,
              double weight,
              double length,
              double height,
              String color,
              int quantity,
              int category) {

    
    
    this.id = id;
    this.description = description;
    this.price = price;
    this.brand = brand;
    this.weight = weight;
    this.length = length;
    this.height = height;
    this.color = color;
    this.quantity = quantity;
    this.category = category;

    assertValidCategory(category);
    assertPositiveItemQuantity(quantity);
  }

  private void assertValidCategory(int category) {
    if (category < 1 || category > 4) {
      throw new IllegalArgumentException("Category must be 1, 2, 3 or 4");
    }
  }

  private void assertPositiveItemQuantity(int quantity) {
    if (quantity < 0) {
      throw new IllegalArgumentException("Item quantity must be positive");
    }
  }

  @Override
  public String toString() {
    return "Item [id=" + id + ", description=" + description + ", price=" + price + ", brand=" 
      + brand + ", weight=" + weight + ", length=" + length + ", height=" + height + ", color=" 
      + color + ", quantity=" + quantity + ", category=" + category + "]";
  }

  public static void main(String[] args) {
    Item item = new Item("1", "description", 1.0, "brand", 1.0, 1.0, 1.0, "color", 1, 1);
    System.out.println(item);
  }

}
