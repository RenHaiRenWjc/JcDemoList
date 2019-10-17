package com.example.javademo.arithmeticDemo;

/**
 * ClassName:com.example.javademo.arithmeticDemo
 * Description:
 * author:wjc on 2019/9/5 15:46
 */
public class BinarySearch {
    private static final int[] orderList = {1, 2, 3, 4, 5, 6, 7, 8, 9};

    public static void main(String[] args) {
//        System.out.println("binarySearch --:" + binarySearch(31));
        System.out.println("addTwoNumbers --:" + addTwoNumbers(null, null));
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

    // https://leetcode-cn.com/problems/add-two-numbers/comments/
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        l1 = new ListNode(3);

        l1.next = new ListNode(4);
        l1.next.next = new ListNode(4);

        l2 = new ListNode(4);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(1);

        ListNode result = new ListNode(0);
        ListNode nextResult = result;
        int temp = 0;
        while (l1 != null || l2 != null || temp != 0) {
            int l1Val = l1 != null ? l1.val : 0;
            int l2Val = l2 != null ? l2.val : 0;
            int val = (l1Val + l2Val + temp) % 10;
            temp = (l1Val + l2Val) / 10;
            ListNode node = new ListNode(val);
            nextResult.next = node; // result 的 next result.next = node
            nextResult = node;  // nextResult 重置
            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;

        }
        return result.next;
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
