package zhang.test.entity;

import lombok.Data;

/**
 * @Descripthon: 测试实体类
 * @author: MrZhang
 * @date: 2021/2/22 11:00
 */
@Data
public class DicVo {
    private static final long serialVersionUID = -648480347381032550L;
    private String value;
    private String text;
    private boolean selected;
    private String group;
    private Integer sort;
}
