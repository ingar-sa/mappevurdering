package com.mappe;

public enum Category {
  FLOOR_LAMINATES,
  WINDOWS,
  DOORS,
  LUMBER;

  /**
   * Given a string, return the corresponding category.
   * 
   * @param category The "index" of the category. Must be "1", "2", "3", or "4".
   * @return A Category enum.
   * @throws IllegalArgumentException If the category is not a valid category.
   */
  public static Category getCategoryFromString(String category) {
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
  
}

