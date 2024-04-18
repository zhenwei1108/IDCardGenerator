package com.github.wegoo.id.generator;

import com.github.wegoo.id.generator.GsonWrapper;
import com.github.wegoo.id.generator.JsonWrapper;
import com.github.wegoo.id.generator.util.RandomGenerator;
import java.io.BufferedReader;
import java.io.FileReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Set;

public class Main {

  final static int BaseAge = 1949;
  final static int old = 65;

  public static String getRandomPrefix(String data, JsonWrapper wrapper) throws Exception {
    HashMap<Integer, String> hashMap = wrapper.parseObjectFromString(data, HashMap.class);
    Set<Integer> nums = hashMap.keySet();
    Object[] array = nums.toArray();
    int i = RandomGenerator.getRandomFromRange(hashMap.size());
    return array[i].toString();
  }

  public static String getRandomBirth() {
    String ageResult = RandomGenerator.getRandomFromRange(old) + BaseAge+"";
    int month = RandomGenerator.getRandomFromRange(12) + 1;
    String monthResult = month < 10 ? "0" + month : month+"";
    int day = RandomGenerator.getRandomFromRange(30) + 1;
    String dayResult = day < 10 ? "0" + day : day+"";
    return ageResult + monthResult + dayResult;
  }


  public static String getBirthOrder(){
    int randomFromRange = RandomGenerator.getRandomFromRange(1500);
    int length = Integer.toString(randomFromRange).length();
    int zeroSize = 4-length;
    StringBuffer sb = new StringBuffer();
    for (int i = 0; i < zeroSize; i++) {
      sb.append("0");
    }
    return sb.append(randomFromRange).toString();
  }


  public static void main(String[] args) throws Exception {
    URL resource = new Main().getClass().getClassLoader().getResource("GB2260.json");
    FileReader fileReader = new FileReader(resource.getFile());
    BufferedReader reader = new BufferedReader(fileReader);
    StringBuffer sb = new StringBuffer();
    String data = null;
    while ((data = reader.readLine()) != null) {
      sb.append(data);
    }
    GsonWrapper gsonWrapper = new GsonWrapper();


    for (int i = 0; i < 20; i++) {
      String randomPrefix = getRandomPrefix(sb.toString(), gsonWrapper);
      String randomBirth = getRandomBirth();
      String birthOrder = getBirthOrder();
      System.out.println(randomPrefix+randomBirth+birthOrder);

    }
  }


}
