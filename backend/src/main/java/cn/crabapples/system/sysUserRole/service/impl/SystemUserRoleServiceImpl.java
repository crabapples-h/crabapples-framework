package cn.crabapples.system.sysUserRole.service.impl;

import cn.crabapples.system.sysUserRole.dao.SysUserRoleDAO;
import cn.crabapples.system.sysUserRole.service.SystemUserRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
public class SystemUserRoleServiceImpl implements SystemUserRoleService {
    private final SysUserRoleDAO userRolesDAO;

    public SystemUserRoleServiceImpl(SysUserRoleDAO userRolesDAO) {
        this.userRolesDAO = userRolesDAO;
    }

    @Override
    public void saveUserRoles(String userId, List<String> rolesList) {
        userRolesDAO.saveUserRoles(userId, rolesList);
    }

}
