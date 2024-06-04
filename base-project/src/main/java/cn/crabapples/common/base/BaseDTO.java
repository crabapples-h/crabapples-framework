package cn.crabapples.common.base;

import com.alibaba.fastjson2.JSONObject;
import lombok.Getter;
import lombok.Setter;

/**
 * TODO 请求入参基本DTO
 *
 * @author Mr.He
 * 2019/9/21 17:47
 * e-mail crabapples.cn@gmail.com
 * qq 294046317
 * pc-name 29404
 */
@Getter
@Setter
public abstract class BaseDTO {
    private String id;

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
