package cn.crabapples.system.sysUserRole.entity;

import cn.crabapples.common.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

@TableName
@Getter
@Setter
public class SysUserRole extends BaseEntity<SysUserRole> {
    private String userId;
    private String roleId;
}
