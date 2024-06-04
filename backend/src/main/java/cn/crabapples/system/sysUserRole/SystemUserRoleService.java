package cn.crabapples.system.sysUserRole;

import cn.crabapples.common.base.BaseService;

import java.util.List;


public interface SystemUserRoleService extends BaseService<SysUserRole> {
    void saveUserRoles(String userId, List<String> rolesList);
}
