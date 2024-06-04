package cn.crabapples.system.sysUserRole.dao;

import cn.crabapples.system.sysUserRole.dao.mybatis.mapper.SysUserRoleMapper;
import cn.crabapples.system.sysUserRole.entity.SysUserRole;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SysUserRoleDAO extends ServiceImpl<SysUserRoleMapper, SysUserRole>  {
    private final SysUserRoleMapper mapper;

    public SysUserRoleDAO(SysUserRoleMapper mapper) {
        this.mapper = mapper;
    }

    public void saveUserRoles(String userId, List<String> roleList) {
        baseMapper.deleteUserRoles(userId);
        if (!roleList.isEmpty())
            roleList.forEach(e -> {
                baseMapper.saveUserRoles(userId, e);
            });
    }

}
