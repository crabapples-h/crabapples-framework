package cn.crabapples.system.sysUser.form;

import cn.crabapples.common.Groups;
import com.alibaba.fastjson2.JSONObject;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;

/**
 * 用户信息提交Form
 */
@Getter
@Setter
public class SysUserForm {
    @NotBlank(message = "id不能为空", groups = Groups.IsEdit.class)
    @NotBlank(message = "id不能为空", groups = Groups.IsStatusChange.class)
    @Null(message = "id必须为空", groups = Groups.IsAdd.class)
    private String id;

    @Length(max = 32, message = "长度错误", groups = {Groups.IsCheckLength.class, Groups.IsLogin.class})
    @NotBlank(message = "用户名不能为空", groups = {Groups.IsNotNull.class, Groups.IsLogin.class})
    private String username;

    @Length(max = 32, message = "长度错误", groups = {Groups.IsCheckLength.class, Groups.IsLogin.class})
    @NotBlank(message = "密码不能为空", groups = {Groups.IsNotNull.class, Groups.IsLogin.class})
    private String password;

    private String content;


    @Length(max = 32)
    @NotBlank(message = "姓名不能为空", groups = {Groups.IsAdd.class, Groups.IsEdit.class})
    private String name;

    private String avatar;

    private Integer role;

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
