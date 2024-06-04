package cn.crabapples.system.sysMenu.dao.mybatis.mapper;

import cn.crabapples.system.sysMenu.entity.SysMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysMenuMapper extends BaseMapper<SysMenu> {


    List<SysMenu> getUserMenus(@Param("userId") String id);

    List<SysMenu> findMenusTree(@Param("id") String id);

    IPage<SysMenu> findMenusTreePage(Page<SysMenu> page, @Param("id") String id);

}
