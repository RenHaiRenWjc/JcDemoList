package com.example.javademo.arithmeticDemo;

/**
 * ClassName:com.example.javademo.arithmeticDemo
 * Description:
 * author:wjc on 2019/9/5 15:46
 */
public class BinarySearch {
    private static final int[] orderList = {1, 2, 3, 4, 5, 6, 7, 8, 9};

    public static void main(String[] args) {
        System.out.println("binarySearch --:" + binarySearch(31));
    }

    /**
     * 必须是有序数据
     * 二分查找，查找值所在的位置
     */
    private static int binarySearch(int target) {
        int low = 0, high = orderList.length - 1, mid = -1;
        while (low <= high) {
            System.out.println("----");
            mid = (low + high) / 2;
            if (orderList[mid] == target) {
                return mid;
            } else if (orderList[mid] < target) {// 过小
                low = mid + 1;
            } else if (orderList[mid] > target) { // 过大
                high = mid - 1;
            }
        }
        return -1;
    }
}
