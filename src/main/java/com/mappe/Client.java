package com.mappe;

import java.util.Scanner;

public class Client {
  
  Inventory inventory;

  /**
   * The constructor initializes an Inventory object.
   */
  public Client() {
    inventory = new Inventory();
  }

  public void run() {
    Scanner scanner = new Scanner(System.in);
    while (true) {
      String nextString = scanner.nextLine();
      System.out.println("Print 1: " + scanner.nextLine());
      System.out.println("Print 2: " + scanner.nextLine());
      
    }
  }

  private void deleteProduct() {
  }

  private void addProduct() {
  }

  private void increaseProductQuantity() {
  }

  private void decreaseProductQuantity() {
  }

  private void printAllProducts() {
  }

  // TODO(note): The description says that you should be able to search
  // by both id and/or description, but this doesn't make much senese to me
  private void findProductById() {
  }

  private void findProductByDescription() {
  }
  

  public static void main(String[] args) {
    Client client = new Client();
    client.run();
  }
}
