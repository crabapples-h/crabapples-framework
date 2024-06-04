package cn.crabapples.system.sysUser.form;

import cn.crabapples.common.Groups;
import com.alibaba.fastjson2.JSONObject;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * 用户信息提交Form
 */
@Getter
@Setter
public class ResetPasswordForm {
    @NotBlank(message = "id不能为空", groups = Groups.IsNotNull.class)
    private String id;

    private String oldPassword;

    @NotBlank(message = "新密码不能为空", groups = {Groups.IsNotNull.class, Groups.IsLogin.class})
    private String newPassword;

    @NotBlank(message = "重复密码不能为空", groups = {Groups.IsNotNull.class, Groups.IsLogin.class})
    private String againPassword;

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
