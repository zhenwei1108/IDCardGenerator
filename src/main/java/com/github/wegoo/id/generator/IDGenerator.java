package com.github.wegoo.id.generator;

import com.github.wegoo.id.generator.util.RandomGenerator;
import java.io.BufferedReader;
import java.io.FileReader;
import java.net.URL;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Set;

public class IDGenerator {

  //生成身份中号中,最大年龄限制
  int old = 65;

  //起始时间
  int BaseAge = 1949;

  final static char[] PARITYBIT = {'1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2'};

  final static int[] POWER_LIST = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};


  private JsonWrapper wrapper;

  /**
   * @author wegoo
   * @description IDGenerator
   * @param [wrapper, maxAge]
   *       json转换实现, 身份证中最大年龄
   * @date 2024/4/19  17:26
   * @since: 1.0.0
   */
  public IDGenerator(JsonWrapper wrapper, int maxAge) {
    this.wrapper = wrapper;
    this.old = LocalDate.now().getYear() - maxAge;
  }


  public IDGenerator(JsonWrapper wrapper) {
    this.wrapper = wrapper;
  }

//  private IDGenerator() {}

  public String getRandomPrefix(JsonWrapper wrapper) throws Exception {
    URL resource = this.getClass().getClassLoader().getResource("GB2260.json");
    FileReader fileReader = new FileReader(resource.getFile());
    BufferedReader reader = new BufferedReader(fileReader);
    StringBuffer sb = new StringBuffer();
    String data;
    while ((data = reader.readLine()) != null) {
      sb.append(data);
    }
    HashMap<Integer, String> hashMap = wrapper.parseObjectFromString(sb.toString(), HashMap.class);
    Set<Integer> nums = hashMap.keySet();
    Object[] array = nums.toArray();
    int i = RandomGenerator.getRandomFromRange(hashMap.size());
    return array[i].toString();
  }

  public String getRandomBirth() {
    String ageResult = RandomGenerator.getRandomFromRange(old) + BaseAge + "";
    int month = RandomGenerator.getRandomFromRange(12) + 1;
    String monthResult = month < 10 ? "0" + month : month + "";
    int day = RandomGenerator.getRandomFromRange(29) + 1;
    String dayResult = day < 10 ? "0" + day : day + "";
    return ageResult + monthResult + dayResult;
  }


  public String getBirthOrder() {
    int randomFromRange = RandomGenerator.getRandomFromRange(150);
    int length = Integer.toString(randomFromRange).length();
    int zeroSize = 3 - length;
    StringBuffer sb = new StringBuffer();
    for (int i = 0; i < zeroSize; i++) {
      sb.append("0");
    }
    return sb.append(randomFromRange).toString();
  }


  public String getSuffix() {
    //派出所数量?
    int randomFromRange = RandomGenerator.getRandomFromRange(90);
    String random = randomFromRange < 10 ? "0" + randomFromRange : "" + randomFromRange;
    return random + RandomGenerator.getRandomFromRange(2);
  }


  public static String getID(String id) {
    String[] split = id.split("");
    int result = 0;
    for (int i = 0; i < POWER_LIST.length; i++) {
      result += Integer.parseInt(split[i]) * POWER_LIST[i];
    }
    return id + PARITYBIT[result % 11];
  }


}
