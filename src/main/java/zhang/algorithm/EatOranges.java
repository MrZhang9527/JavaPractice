package zhang.algorithm;
import zhang.utils.Utils;

import java.util.HashMap;

/**
 * 题目描述：
 * 厨房里总共有 n 个橘子，你决定每一天选择如下方式之一吃这些橘子：
 *
 * 吃掉一个橘子。
 * 如果剩余橘子数 n 能被 2 整除，那么你可以吃掉 n/2 个橘子。
 * 如果剩余橘子数 n 能被 3 整除，那么你可以吃掉 2*(n/3) 个橘子。
 *
 * 每天你只能从以上 3 种方案中选择一种方案。
 * 请你返回吃掉所有 n 个橘子的最少天数。
 *
 * 示例：
 *      输入：n = 10
 *      输出：4
 *      解释：你总共有 10 个橘子。
 *      第 1 天：吃 1 个橘子，剩余橘子数 10 - 1 = 9。
 *      第 2 天：吃 6 个橘子，剩余橘子数 9 - 2*(9/3) = 9 - 6 = 3。（9 可以被 3 整除）
 *      第 3 天：吃 2 个橘子，剩余橘子数 3 - 2*(3/3) = 3 - 2 = 1。
 *      第 4 天：吃掉最后 1 个橘子，剩余橘子数 1 - 1 = 0。
 *      你需要至少 4 天吃掉 10 个橘子。
 * @Descripthon: 吃掉 N 个橘子的最少天数
 * @author: MrZhang
 * @date: 2021/4/20 9:57
 */
public class EatOranges {

    public static void main(String[] args) {
        int result = cal(56);
        Utils.print(String.valueOf(result));
    }

    static HashMap<Integer, Integer> map = new HashMap<>();

    private static int cal(int number){
        if (number < 3 ) {
            return number;
        }
        if (number == 3 ) {
            return 2;
        }
        if (map.containsKey(number)) {
            return map.get(number);
        }
        int min = Math.min(cal(number / 2) + number % 2, number % 3 + cal(number / 3)) + 1;
        map.put(number, min);
        return min;
    }
}
