package zhang.algorithm;

import zhang.utils.Utils;
import java.util.Arrays;
import java.util.HashMap;

/**
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 * 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
 * 示例:
 *      给定 nums = [2, 7, 11, 15], target = 9
 *      因为 nums[0] + nums[1] = 2 + 7 = 9
 *      所以返回 [0, 1]
 * @Descripthon: 两数之和
 * @author: MrZhang
 * @date: 2021/4/19 14:54
 */
public class TwoSum {

    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 15};
        int target = 26;
        int[] ints = twoSum1(nums, target);
        Utils.print(Arrays.toString(ints));
        ints = twoSum2(nums, target);
        Utils.print(Arrays.toString(ints));
    }
    /**
     * 暴力破解
     */
    private static int[] twoSum1(int[] nums, int target) {
        int a;
        int b;
        int[] result = new int[2];
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            a = nums[i];
            for (int j = i+1; j < length; j++) {
                b = nums[j];
                if (a + b == target) {
                    result[0] = i;
                    result[1] = j;
                }
            }
        }
        return result;
    }

    /**
     * 使用 hashMap 降低时间复杂度
     * @param nums 目标数组
     * @param target 目标值
     * @return 两数之和的下标数组
     */
    private static int[] twoSum2(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{map.get(target - nums[i]), i};
            }
            map.put(nums[i], i);
        }
        throw new IllegalStateException("null");
    }
}
