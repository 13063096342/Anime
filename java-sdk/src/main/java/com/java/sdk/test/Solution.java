package com.java.sdk.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author chenfh
 * @date 2021-07-21 11:16
 */
public class Solution {
    public static void main(String[] args) {
        combinationSum2(new int[]{10, 1, 2, 7, 6, 1, 5}, 8);
    }

    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(candidates);
        backtrace(candidates, res, new ArrayList<>(), 0, target);
        return res;
    }


    public static void backtrace(int[] candidates, List<List<Integer>> res, List<Integer> list, int start, int remain) {
        if (remain < 0) return;
        if (remain == 0) {
            res.add(new ArrayList<>(list));
            list.forEach(x -> System.out.print(x + " "));
            System.out.println("\n");
        }
        for (int i = start; i < candidates.length && remain >= candidates[i]; i++) {
            if (i > start && candidates[i] == candidates[i - 1]) continue;
            list.add(candidates[i]);
            backtrace(candidates, res, list, i + 1, remain - candidates[i]);
            list.remove(list.size() - 1);
        }
    }
}
