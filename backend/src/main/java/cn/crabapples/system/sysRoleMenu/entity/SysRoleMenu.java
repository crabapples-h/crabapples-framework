package cn.crabapples.system.sysRoleMenu.entity;

import cn.crabapples.common.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

@TableName(keepGlobalPrefix = true)
@Getter
@Setter
public class SysRoleMenu extends BaseEntity<SysRoleMenu> {
    private String roleId;
    private String menuId;
}
