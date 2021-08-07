package com.mz.stu.web.controller;

import java.util.*;

/**
 * @title: Test
 * @Author mfc
 * @Date: 2021-05-23
 */
public class Test {

    public static void main(String[] args) {

/*
        String[] arrayA = {"130010000011", "13001001690", "13001003313", "13001003608", "13001007747", "13001009107", "13001013879"};

        String[] arrayB = {"130010000111", "13001001690", "13001003313", "13001003618", "13001007747", "13001009107", "13001013889", "13001011888"};

        //A、B并集
        HashSet hashSet = new HashSet();
        for (int i = 0; i < arrayA.length; i++) {
            hashSet.add(arrayA[i]);
        }
        for (int j = 0; j < arrayB.length; j++) {
            hashSet.add(arrayB[j]);
        }


        //交集
        //如果数据量大的话我会把它放到数据库执行
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(hashSet);


        //A有B无 、B有A无
        List<String> listA = Arrays.asList(arrayA);
        List<String> listB = Arrays.asList(arrayB);

        Map<String, Integer> map = new HashMap<String, Integer>();
        //给listA的号码赋值1
        for (String str1 : listA) {
            map.put(str1, 1);
        }
        //上面赋值为1的号码赋值为2
        for (String str2 : listB) {
            if (map.get(str2) != null) {
                map.put(str2, 2);
            }
        }
        //集合中有值为1的，就是listB集合中不存在的
        List<String> listResult = new ArrayList<String>();
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 1){
                listResult.add(entry.getKey());
            }
        }*/


//        int[][] array = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};

        Test test = new Test();
//        test.printMatrix(array);

//        String data = "10045";
//        test.StrToInt(data);

//        int[] array = {2,3,4,1};
//        test.minNumberInRotateArray(array);


        int[] arr = {23,12,62,51,72};
        test.InversePairs(arr);
    }


    private long cnt = 0;
    private int[] tmp;  // 在这里声明辅助数组，而不是在 merge() 递归函数中声明

    public int InversePairs(int[] nums) {
        tmp = new int[nums.length];
        mergeSort(nums, 0, nums.length - 1);
        return (int) (cnt % 1000000007);
    }

    private void mergeSort(int[] nums, int l, int h) {
        if (h - l < 1)
            return;
        int m = l + (h - l) / 2;
        mergeSort(nums, l, m);
        mergeSort(nums, m + 1, h);
        merge(nums, l, m, h);
    }

    private void merge(int[] nums, int l, int m, int h) {
        int i = l, j = m + 1, k = l;
        while (i <= m || j <= h) {
            if (i > m)
                tmp[k] = nums[j++];
            else if (j > h)
                tmp[k] = nums[i++];
            else if (nums[i] <= nums[j])
                tmp[k] = nums[i++];
            else {
                tmp[k] = nums[j++];
                this.cnt += m - i + 1;  // nums[i] > nums[j]，说明 nums[i...mid] 都大于 nums[j]
            }
            k++;
        }
        for (k = l; k <= h; k++)
            nums[k] = tmp[k];
    }






    //非递减数组的第一个元素一定小于等于最后一个元素 ,通过修改二分查找算法进行求解（l 代表 low，m 代表 mid，h 代表 high）
    //当 nums[m] <= nums[h] 时，表示 [m, h] 区间内的数组是非递减数组，[l, m] 区间内的数组是旋转数组，此时令 h = m
    //否则 [m + 1, h] 区间内的数组是旋转数组，令 l = m + 1


    public int minNumberInRotateArray(int[] nums) {
        if (nums.length == 0)
            return 0;
        int l = 0, h = nums.length - 1;
        while (l < h) {
            int m = l + (h - l) / 2;
            if (nums[m] <= nums[h])
                h = m;
            else
                l = m + 1;
        }
        System.out.println(nums[l]);
        return nums[l];
    }

    //如果数组元素允许重复，会出现一个特殊的情况：nums[l] == nums[m] == nums[h]，此时无法确定解在哪个区间，
    // 需要切换到顺序查找。例如对于数组 {1,1,1,0,1}，l、m 和 h 指向的数都为 1，此时无法知道最小数字 0 在哪个区间。
    public int yminNumberInRotateArray(int[] nums) {
        if (nums.length == 0)
            return 0;
        int l = 0, h = nums.length - 1;
        while (l < h) {
            int m = l + (h - l) / 2;
            if (nums[l] == nums[m] && nums[m] == nums[h])
                return minNumber(nums, l, h);
            else if (nums[m] <= nums[h])
                h = m;
            else
                l = m + 1;
        }
        return nums[l];
    }

    private int minNumber(int[] nums, int l, int h) {
        for (int i = l; i < h; i++)
            if (nums[i] > nums[i + 1])
                return nums[i + 1];
        return nums[l];
    }


    public int StrToInt(String str) {
        if (str == null || str.length() == 0)
            return 0;
        boolean isNegative = str.charAt(0) == '-';
        int ret = 0;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (i == 0 && (c == '+' || c == '-'))  /* 符号判定 */
                continue;
            if (c < '0' || c > '9')                /* 非法输入 */
                return 0;
            ret = ret * 10 + (c - '0');
        }
        System.out.println(ret);
        return isNegative ? -ret : ret;
    }


    public int firstChar(String str) {
        //用HashMap保存每一个字符的出现次数，因为HashMap读取复杂度为 O(1)
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < str.length(); i++) {
            Character character = str.charAt(i);
            Integer cout = map.get(character);
            if (cout != null) {
                map.put(character, cout + 1);
            } else {
                map.put(character, 1);
            }
        }
        //为了找出第一个出现次数为1的字符，遍历原字符串
        for (int i = 0; i < str.length(); i++) {
            Character character = str.charAt(i);
            if (map.get(character) == 1) {
                System.out.println(i);
                return i;
            }
        }

        return -1;
    }


    public ArrayList<Integer> printMatrix(int[][] matrix) {
        ArrayList<Integer> ret = new ArrayList<>();
        int r1 = 0, r2 = matrix.length - 1, c1 = 0, c2 = matrix[0].length - 1;
        while (r1 <= r2 && c1 <= c2) {
            //上
            for (int i = c1; i <= r2; i++) {
                ret.add(matrix[r1][i]);
            }
            //右
            for (int i = r1 + 1; i <= r2; i++) {
                ret.add(matrix[i][c2]);
            }
            //下
            if (r1 != r2) {
                for (int i = c2 - 1; i >= c1; i--) {
                    ret.add(matrix[r2][i]);
                }
            }
            //左
            if (c1 != c2) {
                for (int i = r2 - 1; i > r1; i--) {
                    ret.add(matrix[i][c1]);
                }
            }

            r1++;
            r2--;
            c1++;
            c2--;

        }


        return ret;
    }


    public boolean Find(int target, int[][] matrix) {

        if (matrix.length == 0 || matrix == null || matrix[0].length == 0) {
            return false;
        }

        int rows = matrix.length, cols = matrix[0].length;
        int r = 0, c = cols - 1;
        while (r <= rows - 1 && c >= 0) {
            if (target == matrix[r][c]) {
//                System.out.println(matrix[r][c]);
                return true;
            } else if (target > matrix[r][c]) {
//                System.out.println(matrix[r][c]);
                r++;
            } else {
//                System.out.println(matrix[r][c]);
                c--;
            }
        }

        return false;
    }


    /**
     * {2, 3, 1, 0, 2, 5}
     * <p>
     * 132025\102325\103225\2
     */
    public int duplicate(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] != i) {
                if (nums[i] == nums[nums[i]]) {
//                    System.out.println(nums[i]);
                    return nums[i];
                }
                swap(nums, i, nums[i]);
            }
            swap(nums, i, nums[i]);
        }
        return -1;
    }

    private void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }


    public String repleace(StringBuffer str) {

        int p1 = str.length() - 1;

        for (int i = 0; i < p1; i++) {
            if (str.charAt(i) == ' ') {
                str.append("  ");
            }
        }

        int p2 = str.length() - 1;
        while (p1 >= 0 && p2 > p1) {
            char c = str.charAt(p1--);
            if (c == ' ') {
                str.setCharAt(p2--, '0');
                str.setCharAt(p2--, '2');
                str.setCharAt(p2--, '%');
            } else {
                str.setCharAt(p2--, c);
            }

        }
        return str.toString();
    }

}