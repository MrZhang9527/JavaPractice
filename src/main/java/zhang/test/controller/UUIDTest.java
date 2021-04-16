package zhang.test.controller;


import java.util.UUID;

/**
 * @Descripthon: UUID
 * @author: MrZhang
 * @date: 2021/3/9 14:45
 */
public class UUIDTest {
    public static String[] getuuid(int num){
        if (num < 1){
            return new String[1];
        }
        String[] results = new String[num];
        for (int i = 0; i < num; i++) {
            results[i] = getuuid();
        }
        return results;
    }

    public static String getuuid(){
        String uuid = UUID.randomUUID().toString();
        //去掉“-”符号
        return uuid.replace("-", "");
    }

    public static void main(String[] args) {
        System.out.println(getuuid());
    }
}
