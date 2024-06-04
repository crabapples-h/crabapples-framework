package cn.crabapples.system.sysRole.dao.mybatis.mapper;

import cn.crabapples.system.sysRole.entity.SysRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysRoleMapper extends BaseMapper<SysRole> {
    List<SysRole> getUserRoles(@Param("userId")String id);
}
