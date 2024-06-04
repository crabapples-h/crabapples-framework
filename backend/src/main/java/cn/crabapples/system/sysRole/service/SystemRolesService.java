package cn.crabapples.system.sysRole.service;

import cn.crabapples.common.base.BaseService;
import cn.crabapples.system.sysRole.dto.SysRolesDTO;
import cn.crabapples.system.sysRole.entity.SysRole;
import cn.crabapples.system.sysRole.form.RolesForm;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;


/**
 * TODO 系统相关服务[角色]
 *
 * @author Mr.He
 * 2021/4/25 0:34
 * e-mail crabapples.cn@gmail.com
 * qq 294046317
 * pc-name mrhe
 */
public interface SystemRolesService {

    List<SysRole> getUserRoles();

    List<SysRolesDTO> getUserRolesDTO();

    List<SysRolesDTO> getUserRolesById(String id);

    List<SysRole> getByIds(List<String> ids);

    List<SysRole> getRolesList(RolesForm form);

    IPage<SysRole> getRolesPage(Integer pageIndex, Integer pageSize, RolesForm form);


    boolean saveRoles(RolesForm form);

    boolean removeRoles(String id);

}
