package cn.crabapples.system.sysUser.form;

import cn.crabapples.common.Groups;
import cn.crabapples.common.base.BaseForm;
import cn.crabapples.system.sysUser.entity.SysUser;
import com.alibaba.fastjson2.JSONObject;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * TODO 用户信息提交Form
 *
 * @author Mr.He
 * 2020/1/27 11:26
 * e-mail crabapples.cn@gmail.com
 * qq 294046317
 * pc-name 29404
 */
@EqualsAndHashCode(callSuper = true)
@Data
//@ApiModel("登录")
public class SysUserForm extends BaseForm<SysUser> {
    @Getter
    @Setter
    public static class ResetPassword {
        @NotBlank(message = "id不能为空", groups = {Groups.IsNotNull.class})
        private String id;

        @Length(max = 16, message = "长度错误", groups = {Groups.IsCheckLength.class})
        @NotBlank(message = "新密码不能为空", groups = {Groups.IsNotNull.class})
//        @ApiModelProperty(example = "12345678")
        private String newPassword;

        @Length(max = 16, message = "长度错误", groups = {Groups.IsCheckLength.class})
        @NotBlank(message = "重复密码不能为空", groups = {Groups.IsNotNull.class})
//        @ApiModelProperty(example = "12345678")
        private String againPassword;
    }

    @Getter
    @Setter
    public static class UpdatePassword extends ResetPassword{
        @Length(max = 16, message = "长度错误", groups = {Groups.IsCheckLength.class})
        @NotBlank(message = "密码不能为空", groups = {Groups.IsNotNull.class})
//        @ApiModelProperty(example = "12345678")
        private String oldPassword;
    }

    @NotBlank(message = "id不能为空", groups = {Groups.IsNotNull.class, Groups.IsEdit.class})
    private String id;

    @Length(max = 16, message = "长度错误", groups = {Groups.IsCheckLength.class, Groups.IsLogin.class})
    @NotBlank(message = "用户名不能为空", groups = {Groups.IsNotNull.class, Groups.IsLogin.class})
//    @ApiModelProperty(example = "admin")
    private String username;

    @Length(max = 16, message = "密码长度错误", groups = {Groups.IsCheckLength.class, Groups.IsLogin.class, Groups.IsUpdatePassword.class})
    @NotBlank(message = "密码不能为空", groups = {Groups.IsNotNull.class, Groups.IsLogin.class, Groups.IsUpdatePassword.class})
//    @ApiModelProperty(example = "12345678")
    private String password;

    @Length(max = 16, message = "原密码长度错误", groups = {Groups.IsCheckLength.class, Groups.IsUpdatePassword.class})
    @NotBlank(message = "原密码不能为空", groups = {Groups.IsNotNull.class, Groups.IsUpdatePassword.class})
//    @ApiModelProperty(example = "12345678")
    private String oldPassword;

    @Length(max = 16, message = "新密码长度错误", groups = {Groups.IsCheckLength.class, Groups.IsUpdatePassword.class})
    @NotBlank(message = "新密码不能为空", groups = {Groups.IsNotNull.class, Groups.IsUpdatePassword.class})
//    @ApiModelProperty(example = "12345678")
    private String newPassword;

    @Length(max = 16, message = "重复密码长度错误", groups = {Groups.IsCheckLength.class, Groups.IsUpdatePassword.class})
    @NotBlank(message = "重复密码不能为空", groups = {Groups.IsNotNull.class, Groups.IsUpdatePassword.class})
//    @ApiModelProperty(example = "12345678")
    private String againPassword;


    @Length(max = 16)
    @NotBlank(message = "姓名不能为空", groups = {Groups.IsNotNull.class, Groups.IsAdd.class, Groups.IsEdit.class})
    private String name;

    @Length(max = 32)
    @NotBlank(message = "邮箱不能为空", groups = {Groups.IsNotNull.class, Groups.IsAdd.class, Groups.IsEdit.class})
    private String mail;

    @NotBlank(message = "电话不能为空", groups = {Groups.IsNotNull.class, Groups.IsAdd.class, Groups.IsEdit.class})
    private String phone;

    @Length(max = 3)
    @NotNull(message = "年龄不能为空", groups = {Groups.IsAdd.class, Groups.IsEdit.class})
    private Integer age;

    private List<String> roleList;

    @Override
    public SysUser toEntity() {
        SysUser entity = SysUser.create();
        BeanUtils.copyProperties(this, entity);
//        if (null != rolesList)
//            entity.setRolesList(String.join(",", rolesList));
        return entity;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
