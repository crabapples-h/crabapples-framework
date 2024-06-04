package cn.crabapples.system.sysRole;

import com.alibaba.fastjson2.JSONObject;
import lombok.Getter;
import lombok.Setter;

/**
 * TODO 用户基本信息DTO
 *
 * @author Mr.He
 * 2021/4/12 8:26
 * e-mail crabapples.cn@gmail.com
 * qq 294046317
 * pc-name mrhe
 */
@Getter
@Setter
public class SysRolesDTO{
    private String id;
    private String name;

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
