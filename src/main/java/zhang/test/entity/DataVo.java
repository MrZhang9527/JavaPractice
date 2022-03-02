package zhang.test.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Descripthon: TODO
 * @author: MrZhang
 * @date: 2021/9/25 18:36
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataVo{
    private String inActuGetNo;
    private String outActuGetNo;

    @Override
    public boolean equals(Object o) {
        return true;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
