package com.java.sdk.test;

import java.util.*;

/**
 * @author chenfh
 * @date 2021-08-03 09:25
 */

public class JUnitTest {

    public void test1() {
        assert (1 == 1);
    }

    public static void main(String[] args) {
        Integer target = 1000;
        //使用treeMap代替hashMap treeMap默认key升序
        //TreeMap<Integer, Integer> treeMap = new TreeMap<>((o1,o2)->o2-o1);

        HashMap<Integer, Integer> hashMap = new HashMap();
        hashMap.put(0, 10);
        hashMap.put(5, 20);
        hashMap.put(20, 15);
        hashMap.put(10, 30);

        Set<Integer> integers = hashMap.keySet();

        LinkedList<Integer> sortList = new LinkedList<>();
        for (Integer i : integers) {
            sortList.add(i);
        }
        //升序
        sortList.sort(Comparator.comparingInt(o -> o));

        System.out.println(cal(hashMap, sortList, target));
    }

    private static Double cal(HashMap<Integer, Integer> hashMap, LinkedList<Integer> sortList, Integer target) {
        Integer nowContain = 0;
        int size = sortList.size();
        int index = 0;

        while (nowContain < target && index < sortList.size() - 1) {
            //当前流速时间受下一时间影响
            int endTime = sortList.get(index + 1);
            int startTime = sortList.get(index);
            nowContain += (endTime - startTime) * hashMap.get(sortList.get(index));

            //1、结果可能超了  2、还未注满 3、刚好注满
            if(nowContain.equals(target)) {
                return (double) endTime;
            }
            if (nowContain > target) {
                return endTime - (1.0 * (nowContain - target) / hashMap.get(sortList.get(index)));
            }
            index++;
        }

        int endTime = sortList.get(size - 1);
        return 1.0*(target-nowContain)/hashMap.get(sortList.get(index)) + endTime;
    }
}
