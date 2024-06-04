package cn.crabapples.system.sysRoleMenu;

import cn.crabapples.common.base.BaseService;
import cn.crabapples.system.sysMenu.SysMenu;

import java.util.List;


public interface SystemRoleMenusService extends BaseService {

    List<SysMenu> getRoleMenusList(String id);

    List<SysMenu> getRoleMenusList(List<String> ids);

    void saveRoleMenus(String id, List<String> menusList);

    void delByMenuId(String pid);
}
