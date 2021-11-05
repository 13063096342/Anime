package com.java.sdk.test;

import java.util.*;

/**
 * 每年，政府都会公布一万个最常见的婴儿名字和它们出现的频率，也就是同名婴儿的数量。
 * 有些名字有多种拼法，例如，John 和 Jon 本质上是相同的名字，但被当成了两个名字公布出来。给定两个列表，
 * 一个是名字及对应的频率，另一个是本质相同的名字对。设计一个算法打印出每个真实名字的实际频率。
 * 注意，如果 John 和 Jon 是相同的，并且 Jon 和 Johnny 相同，则 John 与 Johnny 也相同，即它们有传递和对称性。
 * 在结果列表中，选择 字典序最小 的名字作为真实名字。
 * <p>
 * names = ["John(15)","Jon(12)","Chris(13)","Kris(4)","Christopher(19)"],
 * synonyms = ["(Jon,John)","(John,Johnny)","(Chris,Kris)","(Chris,Christopher)"]
 * 输出：["John(27)","Chris(36)"]
 *
 * @author chenfh
 * @date 2021-09-28 15:59
 */
public class PingAnTest {
    public static void main(String[] args) {
        //拆分出名字和频率
        List<String> names = new ArrayList<>();
        names.add("John(15)");
        names.add("Jon(12)");
        names.add("Chris(13)");
        names.add("Kris(4)");
        names.add("Christopher(19)");

        //构建top名字映射表
        List<String> synonyms = new ArrayList<>();
        synonyms.add("(Jon,John)");
        synonyms.add("(John,Johnny)");
        synonyms.add("(Chris,Kris)");
        synonyms.add("(Chris,Christopher)");

        List<String> result = getResult(names, synonyms);
        for (int i = 0; i < result.size(); i++) {
            System.out.println(result.get(i));
        }
    }

    private static List<String> getResult(List<String> names, List<String> synonyms) {
        //构建top名字映射表
        List<List<String>> mapList = new ArrayList<>();
        for (String str : synonyms) {
            boolean done = false;
            str = str.substring(1, str.length() - 1);
            String[] split = str.split(",");
            String name1 = split[0];
            String name2 = split[1];

            for (List<String> set : mapList) {
                if (set.contains(name1)) {
                    set.add(name2);
                    done = true;
                    break;
                } else if (set.contains(name2)) {
                    set.add(name1);
                    done = true;
                    break;
                }
            }
            if (!done) {
                List<String> newSet = new ArrayList<>();
                newSet.add(name1);
                newSet.add(name2);
                mapList.add(newSet);
            }
        }

        LinkedHashMap<String, Integer> nameSynMap = new LinkedHashMap<>();

        names.forEach(x -> {
            Integer synStart = x.indexOf("(");
            Integer synEnd = x.indexOf(")");
            String name = x.substring(0, synStart);
            Integer syn = Integer.valueOf(x.substring(synStart + 1, synEnd));

            for (List<String> set : mapList) {
                if (set.contains(name)) {
                    //key 为set集合中的字典顺序较小值
                    String key = set.stream().sorted(Comparator.naturalOrder()).findFirst().get();
                    Integer integer = nameSynMap.get(key);
                    nameSynMap.put(key, integer == null ? syn : syn + integer);
                    break;
                }
            }
        });

        List<String> result = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : nameSynMap.entrySet()) {
            result.add(entry.getKey() + "(" + entry.getValue() + ")");
        }
        return result;
    }

}
