package cn.crabapples.system.sysRole;

import cn.crabapples.system.sysRole.SysRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RolesMapper extends BaseMapper<SysRole> {

    List<SysRole> getUserRoles(@Param("userId")String id);
}
