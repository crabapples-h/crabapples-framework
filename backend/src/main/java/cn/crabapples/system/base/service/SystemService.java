package cn.crabapples.system.base.service;

import cn.crabapples.common.base.BaseService;
import cn.crabapples.system.sysUser.form.SysUserForm;

import java.util.List;

/**
 * TODO 系统相关服务
 *
 * @author Mr.He
 * 2020/1/28 23:22
 * e-mail crabapples.cn@gmail.com
 * qq 294046317
 * pc-name 29404
 */
public interface SystemService  {

    String login(SysUserForm form);

    List<String> getUserPermissions();

    boolean checkUsername(String username);

}
