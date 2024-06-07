package cn.crabapples.system.sysUser.entity;

import cn.crabapples.common.base.BaseEntity;
import cn.crabapples.common.dic.Dict;
import cn.crabapples.generator.CrabapplesController;
import com.alibaba.fastjson2.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 用户实体类
 */
@Getter
@Setter
@TableName(keepGlobalPrefix = true)
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@Data(staticConstructor = "create")
@CrabapplesController
public class SysUser extends BaseEntity<SysUser> {
    @TableId
    private String id;
    private Integer age;
    private String name;
    @JSONField(serialize = false)
    private String password;
    private Integer status;
    private String avatar;
    private String username;
    private String mail;
    private String phone;
    @Dict(dictCode = "gender")
    private Integer gender;
    @TableField(exist = false)
    private List<String> roleList;
    //    private byte isAdmin;
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    @TableField(fill = FieldFill.INSERT)
    private String createBy;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updateBy;
    @TableLogic
    private Boolean delFlag;
}
