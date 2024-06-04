package cn.crabapples.system.sysUserRole;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
public class SystemUserRoleServiceImpl implements SystemUserRoleService {
    private final UserRolesDAO userRolesDAO;

    public SystemUserRoleServiceImpl(UserRolesDAO userRolesDAO) {
        this.userRolesDAO = userRolesDAO;
    }

    @Override
    public void saveUserRoles(String userId, List<String> rolesList) {
        userRolesDAO.saveUserRoles(userId, rolesList);
    }
}
