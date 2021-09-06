package com.zys.leetcode.editor.cn.util;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zys
 * @date 2021/8/16
 * @description
 */
public class ArrayUtil {
    public static int[] oneDimension;
    public static int[][] twoDimension;

    public static int[] stringToOneDimenArray(String array){
        String sub = array.trim();
        sub = sub.substring(1,sub.length() - 1);
        oneDimension = tansferToArray(sub);
        return oneDimension;
    }

    public static int[][] stringToTwoDimenArray(String array){
        if (array.contains(",\n")) {
            String[] rows = array.split(",\n");
            twoDimension = new int[rows.length][];
            for (int i = 0; i < rows.length; i++) {
                twoDimension[i] = stringToOneDimenArray(rows[i]);
            }
        }else{
            List<int[]> list = new ArrayList<>();
            while (array.contains("[")) {
                int first = array.indexOf("[");
                int last = array.indexOf("]");
                list.add(ArrayUtil.stringToOneDimenArray(array.substring(first,last + 1)));
                array = array.substring(last + 1);
            }
            twoDimension = list.toArray(new int[list.size()][]);
        }
        return twoDimension;
    }

    public static int[] tansferToArray(String sub){
        String[] nums = sub.trim().split(",");
        int[] vales = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            if (nums[i].contains("\"")) {
                nums[i] = nums[i].substring(1,nums[i].length() - 1);
            }
            vales[i] = Integer.parseInt(nums[i]);
        }
        return vales;
    }

    public static char[] intArrayToCharArray(int[] array){
        char[] chars = new char[array.length];
        for (int i = 0; i < array.length; i++) {
            chars[i] = (char)(array[i] + '0');
        }
        return chars;
    }

    public static char[][] intArrayToCharArray(int[][] array){
        char[][] chars = new char[array.length][];
        for (int i = 0; i < array.length; i++) {
            chars[i] = intArrayToCharArray(array[i]);
        }
        return chars;
    }

    public static void printArray(int[] array){
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    public static void printArray(int[][] array){
        for (int i = 0; i < array.length; i++) {
            printArray(array[i]);
            System.out.println();
        }
    }
}
