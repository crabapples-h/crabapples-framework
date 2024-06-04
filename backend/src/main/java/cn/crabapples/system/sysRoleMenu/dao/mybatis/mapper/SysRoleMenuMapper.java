package cn.crabapples.system.sysRoleMenu.dao.mybatis.mapper;

import cn.crabapples.system.sysMenu.entity.SysMenu;
import cn.crabapples.system.sysRoleMenu.entity.SysRoleMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysRoleMenuMapper extends BaseMapper<SysRoleMenu> {

    List<SysMenu> getRoleMenusList(@Param("roleId") String id);

    List<SysMenu> getRoleListMenusList(@Param("roleIds") List<String> ids);

    void saveRoleMenus(@Param("roleId") String id, @Param("menuId") String menuId);

    void deleteRoleMenus(@Param("roleId") String id);

    void delByMenuId( @Param("menuId") String menuId);
}
