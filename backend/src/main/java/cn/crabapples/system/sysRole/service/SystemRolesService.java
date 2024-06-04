package cn.crabapples.system.sysRole.service;

import cn.crabapples.common.base.BaseService;
import cn.crabapples.system.sysRole.SysRolesDTO;
import cn.crabapples.system.sysRole.SysRole;
import cn.crabapples.system.sysRole.RolesForm;
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
public interface SystemRolesService extends BaseService {

    List<SysRole> getUserRoles();

    List<SysRolesDTO> getUserRolesDTO();

    List<SysRolesDTO> getUserRolesById(String id);

    List<SysRole> getByIds(List<String> ids);

    List<SysRole> getRolesList(RolesForm form);

    IPage<SysRole> getRolesPage(Integer pageIndex, Integer pageSize, RolesForm form);


    boolean saveRoles(RolesForm form);

    boolean removeRoles(String id);

}
