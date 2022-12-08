package com.mappe;

public class TestClient {
  public static void main(String[] args) {

    // try {
    //   Product validProduct = new Product("123", "A product", 100, "A brand", 1.0, 1.0, 1.0, "red", 1, 1);
    //   System.out.println(validProduct);
    // } catch (Exception e) {
    //   e.printStackTrace();
    // }
    
    // try {
    //   Product invalidPrice = new Product("456", "A product", 0, "A brand", 1.0, 1.0, 1.0, "red", 1, 1);
    //   System.out.println(invalidPrice);
    // } catch (Exception e) {
    //   e.printStackTrace();
    // }
    
    // try {
    //   Product invalidWeight = new Product("789", "A product", 100, "A brand", 0.0, 1.0, 1.0, "red", 1, 1);
    //   System.out.println(invalidWeight);
    // } catch (Exception e) {
    //   e.printStackTrace();
    // }
    
    // try {
    //   Product invalidLength = new Product("101", "A product", 100, "A brand", 1.0, 0.0, 1.0, "red", 1, 1);
    //   System.out.println(invalidLength);
    // } catch (Exception e) {
    //   e.printStackTrace();
    // }

    // try {
    //   Product invalidHeight = new Product("101", "A product", 100, "A brand", 1.0, 1.0, 0.0, "red", 1, 1);  
    //   System.out.println(invalidHeight);
    // } catch (Exception e) {
    //   e.printStackTrace();
    // }
    
    // try {
    //   Product invalidCategory = new Product("101", "A product", 100, "A brand", 1.0, 1.0, 1.0, "red", 1, 5);
    //   System.out.println(invalidCategory);
    // } catch (Exception e) {
    //   e.printStackTrace();
    // }

    // try {
    //   Product invalidQuantity = new Product("101", "A product", 100, "A brand", 1.0, 1.0, 1.0, "red", -1, 1);
    //   System.out.println(invalidQuantity);
    // } catch (Exception e) {
    //   e.printStackTrace();
    // }

    // Product product = new Product("123", "A product", 100, "A brand", 1.0, 1.0, 1.0, "red", 1, 1);

    // try {
    //   product.setCategory(5);
    // } catch (Exception e) {
    //   System.out.println("Set invalid category: " + e.getMessage());
    // }

    // try {
    //   product.setPrice(0);
    // } catch (Exception e) {
    //   System.out.println("Set invalid price: " + e.getMessage());
    // }

    // try {
    //   product.setWeight(0.0);
    // } catch (Exception e) {
    //   System.out.println("Set invalid weight: " + e.getMessage());
    // }

    // try {
    //   product.setLength(0.0);
    // } catch (Exception e) {
    //   System.out.println("Set invalid length: " + e.getMessage());
    // }

    // try {
    //   product.setHeight(0.0);
    // } catch (Exception e) {
    //   System.out.println("Set invalid height: " + e.getMessage());
    // }

    // try {
    //   product.setQuantity(-1);
    // } catch (Exception e) {
    //   System.out.println("Set invalid quantity: " + e.getMessage());
    // }

    // product.printFormatted();
    // product.printFormatted();
    // product.printFormatted();

    // Inventory inventory = new Inventory();

    // for (int i = 0; i < 100000; i++) {
    //   if (i % 10000 == 0) {
    //     System.out.println("Adding product " + i);
    //   }

    //   inventory.addProduct(Integer.toString(i), "A product", 100, "A brand", 1.0, 1.0, 1.0, "red", 1, 1);
    // }
    
    // long startTime = System.nanoTime();
    // inventory.printSingleProduct(Integer.toString(99999));
    // long endTime = System.nanoTime();
    // long duration = (endTime - startTime);

    // System.out.println("Time: " + duration);

    String s = "       true  r    ";
    String[] arr = s.trim().split(" ");
    System.out.println(arr.length);
    for (String str : arr) {
      System.out.println(str);
    }
  }
}

