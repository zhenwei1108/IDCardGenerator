package com.github.wegoo.id.generator.util;

import java.util.Random;

public class RandomGenerator {

  public static int getRandomFromRange(int range){
    Random random = new Random();
    return  Math.abs(random.nextInt(range));
  }

}
