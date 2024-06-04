package cn.crabapples.system.sysUserRole.service;

import java.util.List;


public interface SystemUserRoleService  {
    void saveUserRoles(String userId, List<String> rolesList);
}
