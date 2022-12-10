package com.mappe;

public enum Category {
  FLOOR_LAMINATES(1),
  WINDOWS(2),
  DOORS(3),
  LUMBER(4);

  private final int value;

  private Category(int value) {
    this.value = value;
  }

  public int getValue() {
    return value;
  }
}
