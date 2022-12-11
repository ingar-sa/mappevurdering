package com.mappe;

public class TestClient {
  
  public static void main(String[] args) {

    
    Product product = new Product("A123", 
                              "A product", 
                              100, 
                              "A brand", 
                              1.0, 
                              1.0, 
                              1.0, 
                              "red", 
                              1, 
                              Category.FLOOR_LAMINATES);
    System.out.println(product);

    // TEST INVALID VALUES
    try {
      product.setId("");
      System.out.println("(Test 1 failed) Set empty id: " + product.getId());
    } catch (Exception e) {
      System.out.println("(Test 1 success) Set empty id: " + e.getMessage());
    }

    try {
      product.setId(null);
      System.out.println("(Test 2 failed) Set id to null: " + product.getId());
    } catch (Exception e) {
      System.out.println("(Test 2 success) Set id to null: " + e.getMessage());
    }

    try {
      product.setDescription("");
      System.out.println("(Test 3 failed) Set empty description: " + product.getDescription());
    } catch (Exception e) {
      System.out.println("(Test 3 success) Set empty description: " + e.getMessage());
    }

    try {
      product.setDescription(null);
      System.out.println("(Test 4 failed) Set description to null: " + product.getDescription());
    } catch (Exception e) {
      System.out.println("(Test 4 success) Set description to null: " + e.getMessage());
    }

    try {
      product.setPrice(0);
      System.out.println("(Test 5 failed) Set invalid price: " + product.getPrice());
    } catch (Exception e) {
      System.out.println("(Test 5 success) Set invalid price: " + e.getMessage());
    }

    try {
      product.setBrand("");
      System.out.println("(Test 6 failed) Set empty brand: " + product.getBrand());
    } catch (Exception e) {
      System.out.println("(Test 6 success) Set empty brand: " + e.getMessage());
    }

    try {
      product.setBrand(null);
      System.out.println("(Test 7 failed) Set brand to null: " + product.getBrand());
    } catch (Exception e) {
      System.out.println("(Test 7 success) Set brand to null: " + e.getMessage());
    }

    try {
      product.setWeight(0.0);
      System.out.println("(Test 8 failed) Set invalid weight: " + product.getWeight());
    } catch (Exception e) {
      System.out.println("(Test 8 success) Set invalid weight: " + e.getMessage());
    }

    try {
      product.setLength(0.0);
      System.out.println("(Test 9 failed) Set invalid length: " + product.getLength());
    } catch (Exception e) {
      System.out.println("(Test 9 success) Set invalid length: " + e.getMessage());
    }

    try {
      product.setHeight(0.0);
      System.out.println("(Test 10 failed) Set invalid height: " + product.getHeight());
    } catch (Exception e) {
      System.out.println("(Test 10 success) Set invalid height: " + e.getMessage());
    }

    try {
      product.setColor("");
      System.out.println("(Test 11 failed) Set empty color: " + product.getColor());
    } catch (Exception e) {
      System.out.println("(Test 11 success) Set empty color: " + e.getMessage());
    }

    try {
      product.setColor(null);
      System.out.println("(Test 12 failed) Set color to null: " + product.getColor());
    } catch (Exception e) {
      System.out.println("(Test 12 success) Set color to null: " + e.getMessage());
    }

    try {
      product.setQuantity(-1);
      System.out.println("(Test 13 failed) Set invalid quantity: " + product.getQuantity());
    } catch (Exception e) {
      System.out.println("(Test 13 success) Set invalid quantity: " + e.getMessage());
    }

    try {
      product.setCategory(null);
      System.out.println("(Test 14 failed) Set category to null: " + product.getCategory());
    } catch (Exception e) {
      System.out.println("(Test 14 success) Set category to null: " + e.getMessage());
    }

    try {
      product.setCategory(Category.parseCategory("5"));
      System.out.println(("(Test 15 failed) Set invalid category: " + product.getCategory()));
    } catch (Exception e) {
      System.out.println("(Test 15 success) Set invalid category: " + e.getMessage());
    }

    // TEST VALID VALUES
    try {
      product.setId("A1B2C3");
      System.out.println("(Test 16 success) Set valid id: " + product.getId());
    } catch (Exception e) {
      System.out.println("(Test 16 failed) Failed to set valid id: " + e.getMessage());
    }

    try {
      product.setDescription("A valid description");
      System.out.println("(Test 17 success) Set valid description: " + product.getDescription());
    } catch (Exception e) {
      System.out.println("(Test 17 failed) Failed to set valid description: " + e.getMessage());
    }

    try {
      product.setPrice(100);
      System.out.println("(Test 18 success) Set valid price: " + product.getPrice());
    } catch (Exception e) {
      System.out.println("(Test 18 failed) Failed to set valid price: " + e.getMessage());
    }

    try {
      product.setBrand("Brand");
      System.out.println("(Test 19 success) Set valid brand: " + product.getBrand());
    } catch (Exception e) {
      System.out.println("(Test 19 failed) Failed to set valid brand: " + e.getMessage());
    }

    try {
      product.setWeight(1.0);
      System.out.println("(Test 20 success) Set valid weight: " + product.getWeight());
    } catch (Exception e) {
      System.out.println("(Test 20 failed) Failed to set valid weight: " + e.getMessage());
    }

    try {
      product.setLength(1.0);
      System.out.println("(Test 21 success) Set valid length: " + product.getLength());
    } catch (Exception e) {
      System.out.println("(Test 21 failed) Failed to set valid length: " + e.getMessage());
    }

    try {
      product.setHeight(1.0);
      System.out.println("(Test 22 success) Set valid height: " + product.getHeight());
    } catch (Exception e) {
      System.out.println("(Test 22 failed) Failed to set valid height: " + e.getMessage());
    }

    try {
      product.setColor("Blue");
      System.out.println("(Test 23 success) Set valid color: " + product.getColor());
    } catch (Exception e) {
      System.out.println("(Test 23 failed) Failed to set valid color: " + e.getMessage());
    }

    try {
      product.setQuantity(1);
      System.out.println("(Test 24 success) Set valid quantity: " + product.getQuantity());
    } catch (Exception e) {
      System.out.println("(Test 24 failed) Failed to set valid quantity: " + e.getMessage());
    }

    try {
      product.setCategory(Category.FLOOR_LAMINATES);
      System.out.println("(Test 25 success) Set valid category: " + product.getCategory());
    } catch (Exception e) {
      System.out.println("(Test 25 failed) Failed to set valid category: " + e.getMessage());
    }
  }
}

