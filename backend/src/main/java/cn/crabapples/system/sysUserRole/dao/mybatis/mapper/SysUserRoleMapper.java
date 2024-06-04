package cn.crabapples.system.sysUserRole.dao.mybatis.mapper;

import cn.crabapples.system.sysUserRole.entity.SysUserRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {

    void deleteUserRoles(@Param("userId") String userId);

    void saveUserRoles(@Param("userId") String userId, String roleId);
}
