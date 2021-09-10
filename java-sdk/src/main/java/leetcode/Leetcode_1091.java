package leetcode;

import javafx.util.Pair;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author chenfh
 * 题目描述：
 * 给你一个 n x n 的二进制矩阵 grid 中，返回矩阵中最短 畅通路径 的长度。如果不存在这样的路径，返回 -1 。
 * 二进制矩阵中的 畅通路径 是一条从 左上角 单元格（即，(0, 0)）到 右下角 单元格（即，(n - 1, n - 1)）的路径，该路径同时满足下述要求：
 * 路径途经的所有单元格都的值都是 0 。
 * 路径中所有相邻的单元格应当在 8 个方向之一 上连通（即，相邻两单元之间彼此不同且共享一条边或者一个角）。
 * 畅通路径的长度 是该路径途经的单元格总数。
 * @date 2021-06-07 10:25
 */
public class Leetcode_1091 {
    public static void main(String[] args) {
        int[][] grid = new int[][]{{0, 0, 0}, {1, 1, 0}, {1, 1, 0}};
        System.out.println(count0(grid));
    }

    public static Integer count0(int[][] grid) {
        if (grid.length <= 0 || grid[0].length <= 0) {
            return -1;
        }
        int n = grid.length;
        int m = grid[0].length;

        int[][] direction = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}, {1, 1}, {-1, -1}, {-1, 1}, {1, -1}};
        int road = 0;
        Queue<Pair<Integer, Integer>> queue = new LinkedList<>();
        queue.add(new Pair<>(0, 0));

        while (queue.size() > 0) {
            int size = queue.size();
            while (size-- > 0) {
                System.out.println("当前元素数量：" + queue.size()+",road:"+ road);
                Pair<Integer, Integer> poll = queue.poll();
                if (grid[poll.getKey()][poll.getValue()] == 1) {
                    continue;
                }
                if (poll.getKey() >= n - 1 && poll.getValue() >= m - 1) {
                    return road;
                }
                grid[poll.getKey()][poll.getValue()] = 1;
                for (int[] item : direction) {
                    int x = item[0] + poll.getKey();
                    int y = item[1] + poll.getValue();

                    if (x >= n || x < 0 || y >= m || y < 0 || grid[x][y] == 1) {
                        continue;
                    }
                    queue.add(new Pair<>(x, y));
                    System.out.println("添加元素：" + x + "," + y);
                }
            }
            road++;
        }

        return -1;
    }
}
