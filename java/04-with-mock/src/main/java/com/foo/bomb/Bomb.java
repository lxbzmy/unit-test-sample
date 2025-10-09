package com.foo.bomb;

public class Bomb {

  private double effect;

  static {
    System.out.println("You Will Have A Bomb!");
  }

  public Bomb() {
    System.out.println("A bomb loaded.");
    if (Math.random() > 0.20) {
      throw new RuntimeException("Bomb Exploed.");
    }
  }

  public void fire() {
    throw new RuntimeException("Bomb Fired.");
  }
}
