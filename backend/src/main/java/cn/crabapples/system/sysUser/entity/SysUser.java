package cn.crabapples.system.sysUser.entity;

import cn.crabapples.common.base.BaseEntity;
import com.alibaba.fastjson2.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.List;

/**
 * 用户实体类
 */
@Getter
@Setter
@TableName("sys_user")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@Data(staticConstructor = "create")
public class SysUser extends BaseEntity<SysUser> {
    @TableId
    private String id;
    private String username;
    @JSONField(serialize = false)
    private String password;
    private String avatar;
    private String name;
    private Integer status;
    @TableField(exist = false)
    private List<String> roleList;
    @TableLogic
    private Boolean delFlag;
//    private byte isAdmin;
    private String createBy;
    private Date createTime;
    private Date updateTime;
}
