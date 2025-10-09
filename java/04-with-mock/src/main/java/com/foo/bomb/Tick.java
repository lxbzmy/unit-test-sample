package com.foo.bomb;

public class Tick {

  Bomb bomb = new Bomb();

  private int sum;

  public Tick(int sum) {
    this.sum = sum;
  }

  /**
   * 计时器减1
   * 
   * @return true：还有时间，false：时间到了
   */
  public boolean tick() {
    sum--;
    if (sum <= 0) {
      bomb.fire();
      return false;
    }
    return true;
  }
}
