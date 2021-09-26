package zhang.test.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Descripthon: TODO
 * @author: MrZhang
 * @date: 2021/9/25 18:35
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResultVo {

    private static final String SUCCESS = "200";

    @JSONField(name = "resp_code")
    private String respCode;
    @JSONField(name = "resp_desc")
    private String respDesc;
    // private DataVo data;
    private String inActuGetNo;
    private String outActuGetNo;

    public boolean isSuccess(){
        return SUCCESS.equals(this.respCode);
    }
}
